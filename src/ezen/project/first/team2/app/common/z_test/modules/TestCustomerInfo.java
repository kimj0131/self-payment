package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.CustomerInfo;

public class TestCustomerInfo {
	public static void main(String[] args) {
		for (var idx : CustomerInfo.DummyDataIndex.values()) {
			System.out.println("----------");
			CustomerInfo mi = CustomerInfo.getDummyData(idx);
			System.out.println(mi);
		}
	}
}
