package ezen.project.first.team2.app.common.z_test.modules.base;

import ezen.project.first.team2.app.common.modules.database.DBConnector;
import ezen.project.first.team2.app.common.modules.database.DBConnectorListener;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class TestListManagerDb {
	public static void main(String[] args) {
		try {
			var dbConn = DBConnector.getInstance();
			dbConn.loadJdbcDriver();
			dbConn.setActionListener(new DBConnectorListener() {

				@Override
				public void onConnecting(DBConnector sender) {
					System.out.printf("[%s] 연결 중.. \n", TimeUtils.currTimeStr());
				}

				@Override
				public void onConnected(DBConnector sender) {
					System.out.printf("[%s] 연결됨 \n", TimeUtils.currTimeStr());

					EmployeesManager mngr = EmployeesManager.getInstance();
					try {
						mngr.doSelectQuery((item, idx) -> true,
								"max(employee_id) as employee_id", null, null);
					} catch (Exception e) {
						e.printStackTrace();
					}

					mngr.iterate((item, idx) -> {
						System.out.println(item);
						return true;
					});
				}

				@Override
				public void onConnectionFailure(DBConnector sender, String reason) {
					System.out.printf("[%s] 연결 실패 => %s \n", TimeUtils.currTimeStr(), reason);
				}

				@Override
				public void onDisconnected(DBConnector sender) {
					System.out.printf("[%s] 연결 해제됨 \n", TimeUtils.currTimeStr());
				}

			});

			dbConn.connect("localhost", DBConnector.DEFAULT_PORT_NUM, "hr", "1234");

			SystemUtils.sleep(15 * 1000);

			dbConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
