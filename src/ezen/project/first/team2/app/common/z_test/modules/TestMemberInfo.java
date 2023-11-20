package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.MemberInfo;
import ezen.project.first.team2.app.common.modules.MemberInfo.DummyDataIndex;

public class TestMemberInfo {
	public static void main(String[] args) {
		for (DummyDataIndex idx : MemberInfo.DummyDataIndex.values()) {
			System.out.println("----------");
			MemberInfo mi = MemberInfo.getDummyData(idx);
			System.out.println(mi);
		}
	}
}
