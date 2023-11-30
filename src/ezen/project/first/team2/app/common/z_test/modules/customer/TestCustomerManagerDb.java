package ezen.project.first.team2.app.common.z_test.modules.customer;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.database.DBConnector;
import ezen.project.first.team2.app.common.modules.database.DBConnectorListener;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class TestCustomerManagerDb {
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
					final boolean DO_UPDATE_QUERY = true;
					final boolean DO_DELETE_QUERY = true;

					var mngr = CustomerManager.getInstance();

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

							int rCnt = mngr.doSelectQuery();
							System.out.printf("rCnt: %d \n", rCnt);

							mngr.iterate();

							System.out.println();
						}

						// 레코드 추가
						if (DO_INSERT_QUERY) {
							printTitle("레코드 추가");
							int id = mngr.getNextIdFromDb("cust_id");

							LocalDate birth = LocalDate.of(1993, 01, 31);
							var item = new CustomerItem(id, LocalDate.now(), "김준형", birth, "010-0000-4355", 0, "");
							mngr.doInsertQuery(item);

							mngr.doSelectQuery();
							mngr.iterate();

							System.out.println();
						}

						// 레코드 수정
						if (DO_UPDATE_QUERY) {
							printTitle("레코드 수정");

							var item = mngr.getFirstItem();
							var fieldset = new String[] { "name" };
							var _where = String.format("cust_id=%d", item.getId());

							item.setName(item.getName() + "2");
							int rows = mngr.doUpdateQuery(item, fieldset, _where);
							System.out.printf("rows: %d \n", rows);

							mngr.doSelectQuery();
							mngr.iterate();

							System.out.println();
						}

						// 레코드 삭제
						if (DO_DELETE_QUERY) {
							printTitle("레코드 삭제");

							String _where = "point=0";
							int rows = mngr.doDeleteQuery(_where);
							System.out.printf("rows: %d \n", rows);

							mngr.doSelectQuery();
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
