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

		final CustomerInfo sglee = CustomerInfo.getDummyData(CustomerInfo.DummyDataIndex._0_SiGwanLEE);
		final CustomerInfo gygil = CustomerInfo.getDummyData(CustomerInfo.DummyDataIndex._1_GeunYoungGil);
		final CustomerInfo hwjo = CustomerInfo.getDummyData(CustomerInfo.DummyDataIndex._2_HyunWooJo);
		final CustomerInfo jhkim = CustomerInfo.getDummyData(CustomerInfo.DummyDataIndex._3_JunHyungKim);
		final CustomerInfo cjpark = CustomerInfo.getDummyData(CustomerInfo.DummyDataIndex._4_CheolJinPark);
		final CustomerInfo bseo = CustomerInfo.getDummyData(CustomerInfo.DummyDataIndex._5_BinSeo);

		try {
			custMngr.add(sglee);
			System.out.print("  " + sglee.getName());
			custMngr.add(gygil);
			System.out.print(", " + gygil.getName());
			custMngr.add(hwjo);
			System.out.print(", " + hwjo.getName());
			custMngr.add(jhkim);
			System.out.print(", " + jhkim.getName());
			custMngr.add(cjpark);
			System.out.print(", " + cjpark.getName());
			custMngr.add(bseo);
			System.out.println(", " + bseo.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			CustomerInfo ci = new CustomerInfo();
			ci.setValues(custMngr.getNextID(), LocalDate.now(), "BLUECNT",
					LocalDate.of(1983, 1, 1), "010-0000-8086", 10000, "");

			custMngr.add(ci);
			System.out.println(", " + bseo.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(custMngr);

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("# 고객 수정");

		try {
			sglee.setName("SiGwan LEE");
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
			custMngr.iterate(info -> {
				System.out.println("  " + info.toString());

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
