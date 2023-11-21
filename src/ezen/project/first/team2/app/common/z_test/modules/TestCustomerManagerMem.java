package ezen.project.first.team2.app.common.z_test.modules;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerInfo;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;

public class TestCustomerManagerMem {
	public static void main(String[] args) {
		CustomerManagerMem custMngr = CustomerManagerMem.getInstance();

		try {
			custMngr.init();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("# 고객 추가");

		try {
			for (var ci : CustomerInfo.getPredefinedData()) {
				custMngr.add(ci);
				System.out.println(" -> " + ci.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			CustomerInfo ci = new CustomerInfo();
			// ID 값에 -1을 넣거나 매니저를 통해 다음 ID 값을 얻을 수 있다다
			ci.setValues(custMngr.getNextID(), LocalDate.now(), "BLUECNT",
					LocalDate.of(1983, 1, 1), "010-0000-8086", 10000, "");

			custMngr.add(ci);
			System.out.println(" -> " + ci.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(custMngr);

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("# 고객 수정");

		CustomerInfo sglee = new CustomerInfo();
		try {
			sglee = custMngr.find((ci, idx) -> ci.getName().equals("이시관"));
			// sglee = custMngr.find(ci -> ci.getPhoneNumber().equals("010-0000-8086"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			sglee.setName("시관폰");
			custMngr.updateById(sglee.getId(), sglee);
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(custMngr);

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("# 고객 삭제");

		try {
			custMngr.deleteById(sglee.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(custMngr);

		// -------------------------------------------------

		try {
			custMngr.deinit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void printList(ListManager<CustomerInfo> custMngr) {
		System.out.println("-".repeat(40));
		System.out.println("- 고객 리스트");

		try {
			custMngr.iterate((info, idx) -> {
				System.out.println("  " + info.toString());

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
