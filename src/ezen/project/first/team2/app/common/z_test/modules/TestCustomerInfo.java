package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.customer.CustomerInfo;

public class TestCustomerInfo {
	public static void main(String[] args) {
		for (var ci : CustomerInfo.getPredefinedData()) {
			System.out.println("----------");
			System.out.println(ci);
		}
	}
}
