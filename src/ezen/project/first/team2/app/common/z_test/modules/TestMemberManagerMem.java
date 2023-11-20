package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.MemberInfo;
import ezen.project.first.team2.app.common.modules.MemberManager;
import ezen.project.first.team2.app.common.modules.MemberManagerMem;

public class TestMemberManagerMem {
	public static void main(String[] args) {
		MemberManagerMem memMngr = MemberManagerMem.getInstance();

		try {
			memMngr.init();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("회원 추가");

		final MemberInfo sglee = MemberInfo.getDummyData(MemberInfo.DummyDataIndex._0_SiGwanLEE);
		final MemberInfo gygil = MemberInfo.getDummyData(MemberInfo.DummyDataIndex._1_GeunYoungGil);
		final MemberInfo hwjo = MemberInfo.getDummyData(MemberInfo.DummyDataIndex._2_HyunWooJo);
		final MemberInfo jhkim = MemberInfo.getDummyData(MemberInfo.DummyDataIndex._3_JunHyungKim);
		final MemberInfo cjpark = MemberInfo.getDummyData(MemberInfo.DummyDataIndex._4_CheolJinPark);
		final MemberInfo bseo = MemberInfo.getDummyData(MemberInfo.DummyDataIndex._5_BinSeo);

		try {
			memMngr.add(sglee);
			System.out.println("  -> " + sglee.getName());
			memMngr.add(gygil);
			System.out.println("  -> " + gygil.getName());
			memMngr.add(hwjo);
			System.out.println("  -> " + hwjo.getName());
			memMngr.add(jhkim);
			System.out.println("  -> " + jhkim.getName());
			memMngr.add(cjpark);
			System.out.println("  -> " + cjpark.getName());
			memMngr.add(bseo);
			System.out.println("  -> " + bseo.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(memMngr);

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("회원 수정");

		try {
			sglee.setName("SiGwan LEE");
			memMngr.updateById(sglee.getId(), sglee);
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(memMngr);

		// -------------------------------------------------

		System.out.println("-".repeat(40));
		System.out.println("회원 삭제");

		try {
			memMngr.deleteById(sglee.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		printList(memMngr);

		// -------------------------------------------------

		try {
			memMngr.deinit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void printList(MemberManager memMngr) {
		System.out.println("-".repeat(40));
		System.out.println("회원 리스트");

		try {
			memMngr.iterate(info -> {
				System.out.println(info);

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
