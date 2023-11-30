package ezen.project.first.team2.app.common.z_test.modules.base;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.database.DBConnector;
import ezen.project.first.team2.app.common.modules.database.DBConnectorListener;
import ezen.project.first.team2.app.common.utils.MathUtils;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class TestListManagerDb {
	static void printTitle(String text) {
		System.out.println("-".repeat(60));
		System.out.printf("[%s] # %s \n", TimeUtils.currTimeStr(), text);
		System.out.println("-".repeat(60));
	}

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

					final boolean CREATE_TABLE = !true;
					final boolean DROP_TABLE = !true;
					final boolean TRUNCATE_TABLE = !true;

					final boolean DO_SELECT_QUERY = true;
					final boolean DO_INSERT_QUERY = true;
					final boolean DO_UPDATE_QUERY = !true;
					final boolean DO_DELETE_QUERY = !true;

					EmployeesManager mngr = EmployeesManager.getInstance();

					try {
						// 테이블 생성
						if (CREATE_TABLE) {
							printTitle("테이블 생성");

							mngr.createTable();

							System.out.println();
						}

						// 테이블 삭제
						if (DROP_TABLE) {
							printTitle("테이블 삭제");

							mngr.dropTable();

							System.out.println();
						}
						// 테이블 초기화
						if (TRUNCATE_TABLE) {
							printTitle("테이블 초기화");

							mngr.truncateTable();

							System.out.println();
						}

						// 레코드셋 리스팅
						if (DO_SELECT_QUERY) {
							printTitle("레코드셋 리스팅");

							String fieldset = null;// "max(employee_id) as employee_id";
							String where = null;// "employee_id > 203";
							String orderBy = "employee_id desc";
							int rCnt = mngr.doSelectQuery((item, idx) -> true, fieldset, where, orderBy);
							System.out.printf("rCnt: %d \n", rCnt);
							// System.out.println(mngr.getFirstItem().getId());

							mngr.iterate((item, idx) -> {
								System.out.println(item);
								return true;
							});

							System.out.println();
						}

						// 레코드 추가
						if (DO_INSERT_QUERY) {
							printTitle("레코드 추가");
							var rnd = String.format("%04d", MathUtils.getRandomNumber(1, 4096));
							int id = mngr.getNextIdFromDb("employee_id");
							var item = new EmployeeItem(id, "sigwan", "lee", "aa@bb.com" + rnd, "010-0000-0000",
									LocalDate.of(1983, 5, 9), "IT_PROG", 100000, 0.2, 101, 110);
							mngr.doInsertQuery(item);

							// mngr.doSelectQuery();
							String where = null;// "employee_id > 200";
							String orderBy = "employee_id desc";
							mngr.doSelectQuery((_item, _idx) -> true, null, where, orderBy);
							mngr.iterate();

							System.out.println();
						}

						// 레코드 수정
						if (DO_UPDATE_QUERY) {
							printTitle("레코드 수정");

							var item = mngr.getFirstItem();
							var fieldset = new String[] { "salary" };
							var _where = String.format("employee_id=%d", item.getId());

							item.setSalary(9999999);
							int rows = mngr.doUpdateQuery(item, fieldset, _where);
							System.out.printf("rows: %d \n", rows);

							// mngr.doSelectQuery();
							String where = null;// "employee_id > 200";
							String orderBy = "employee_id desc";
							mngr.doSelectQuery((_item, _idx) -> true, null, where, orderBy);
							mngr.iterate();

							System.out.println();
						}

						// 레코드 삭제
						if (DO_DELETE_QUERY) {
							printTitle("레코드 삭제");

							// String _where = "first_name='sigwan'";
							String _where = "salary=999999";
							int rows = mngr.doDeleteQuery(_where);
							System.out.printf("rows: %d \n", rows);

							// mngr.doSelectQuery();
							String where = null;// "employee_id > 200";
							String orderBy = "employee_id desc";
							mngr.doSelectQuery((_item, _idx) -> true, null, where, orderBy);
							mngr.iterate();

							System.out.println();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

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

			dbConn.connect("192.168.0.64", DBConnector.DEFAULT_PORT_NUM, "hr", "1234");

			SystemUtils.sleep(3 * 1000);

			dbConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
