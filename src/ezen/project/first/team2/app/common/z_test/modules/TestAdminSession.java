package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.admin.AdminSession;

public class TestAdminSession {
	public static void main(String[] args) {
		// ID, PASSWD 모두 맞는 경우
		final String ID = "admin";
		final String PASSWD = "qwer1234!@#$";

		// ID, PASSWD 모두 틀린 경우
		// final String ID = "aa";
		// final String PASSWD = "1234";

		// PASSWD가 틀린 경우
		// final String ID = "admin";
		// final String PASSWD = "1234";

		AdminSession as = AdminSession.getInstance();
		AdminSession.LoginResult res = as.login(ID, PASSWD);
		System.out.printf("login test: id:%s, pw:%s -> result:%s \n",
				ID, PASSWD, res);

		as.logout();
		System.out.println("logout.");

		System.out.printf("isLoginOk() -> %s \n", as.isLoginOk());
	}
}
