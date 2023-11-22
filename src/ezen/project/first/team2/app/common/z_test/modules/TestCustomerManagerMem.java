package ezen.project.first.team2.app.common.z_test.modules;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;

public class TestCustomerManagerMem {

	static void printTitle(String text) {
		System.out.println("=".repeat(70));
		System.out.println("= " + text);
		System.out.println("=".repeat(70));
		System.out.println();
	}

	static void printSection(String text) {
		System.out.println("-".repeat(60));
		System.out.println("- " + text);
		System.out.println("-".repeat(60));
		System.out.println();
	}

	static void printList(ListManager<CustomerItem> custMngr) {
		printSection("고객 리스트");

		try {
			custMngr.iterate((info, idx) -> {
				System.out.println("  " + info.toString());

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public static void main(String[] args) {
		CustomerManagerMem custMngr = CustomerManagerMem.getInstance();

		try {
			//
			custMngr.init();
			//

			// 고객 추가
			{
				printTitle("고객 추가");

				for (var ci : CustomerItem.getPredefinedData()) {
					custMngr.add(ci);
					System.out.println(" -> " + ci.getName());
				}

				var ci = new CustomerItem();
				// ID 값에 -1을 넣거나 매니저를 통해 다음 ID 값을 얻을 수 있다
				ci.setValues(custMngr.getNextID(), LocalDate.now(), "BLUECNT",
						LocalDate.of(1983, 5, 9), "010-0000-8087", 10000, "");

				custMngr.add(ci);
				System.out.println(" -> " + ci.getName());

				printList(custMngr);
			}

			// 고객 수정
			{
				printTitle("고객 수정");

				var ci = custMngr.findById(0);
				ci.setName("NEW시관");

				custMngr.updateById(ci.getId(), ci);

				printList(custMngr);
			}

			// 고객 삭제
			{
				printTitle("고객 삭제제");

				var ci = custMngr.findByName("NEW시관");

				custMngr.deleteById(ci.getId());

				printList(custMngr);
			}

			// 고객 조회
			{
				printTitle("고객 조회");

				custMngr.add(CustomerItem.getPredefinedData()[0]);
				printList(custMngr);

				CustomerItem ci = null;
				List<CustomerItem> ciList = null;

				System.out.println("- findById()");
				ci = custMngr.findById(6);
				System.out.println(ci);

				System.out.println("- findByName()");
				ci = custMngr.findByName("BLUECNT");
				System.out.println(ci);

				System.out.println("- findByBirthday()");
				ciList = custMngr.findByBirthday(LocalDate.of(1983, 5, 9));
				for (var ci1 : ciList) {
					System.out.println("  - " + ci1);
				}

				System.out.println("- findByPhoneNumber()");
				ci = custMngr.findByPhoneNumber("010-0000-8086");
				System.out.println(ci);

				System.out.println();
			}

			//
			custMngr.deinit();
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
