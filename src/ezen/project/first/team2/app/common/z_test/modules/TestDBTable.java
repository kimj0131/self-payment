package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.database.DBTable;
import ezen.project.first.team2.app.common.modules.database.DBTableActionListener;
import ezen.project.first.team2.app.common.utils.SystemUtils;

public class TestDBTable {
	public static void main(String[] args) {
		System.out.println("Test DBTable class");

		try {
			DBTable tbl = new DBTable();

			tbl.setActionListener(new DBTableActionListener() {

				@Override
				public void onConnecting(DBTable table) {
					System.out.println("DB 연결 중..");
				}

				@Override
				public void onConnected(DBTable table) {
					System.out.println("DB 연결됨");
				}

				@Override
				public void onConnectionFailure(DBTable table, String reason) {
					System.out.println("DB 연결 실패 => " + reason);
				}

				@Override
				public void onDisconnected() {
					System.out.println("DB 연결 해제");
				}

			});
			tbl.connect("localhost", DBTable.DEFAULT_PORT_NUM, "hr", "1234");

			// 10분 대기
			SystemUtils.sleep(10 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
