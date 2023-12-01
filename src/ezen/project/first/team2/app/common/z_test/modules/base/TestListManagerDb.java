package ezen.project.first.team2.app.common.z_test.modules.base;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.database.DBConnector;
import ezen.project.first.team2.app.common.utils.MathUtils;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class TestListManagerDb {
	static void printTitle(String text) {
		System.out.println("-".repeat(60));
		System.out.printf("[%s] # %s \n", TimeUtils.currTimeStr(), text);
		System.out.println("-".repeat(60));
	}

	static void testCreateTable() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("테이블 생성");

		mngr.createTable();

		System.out.println();
	}

	static void testDropTable() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("테이블 삭제");

		mngr.dropTable();

		System.out.println();
	}

	static void testTruncateTable() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("테이블 초기화");

		mngr.truncateTable();

		System.out.println();
	}

	static void testSelectQuery() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("레코드셋 리스팅");

		String fieldset = null;// "max(employee_id) as employee_id";
		String where = null;// "employee_id > 203";
		String orderBy = "employee_id desc";
		int rCnt = mngr.doSelectQuery((item, idx) -> true, fieldset, where, orderBy);
		System.out.printf("rCnt: %d \n", rCnt);
		// System.out.println(mngr.getFirstItem().getId());

		// mngr.iterate((item, idx) -> {
		// System.out.println(item);
		// return true;
		// });
		mngr.iterate();

		System.out.println();
	}

	static void testInsertQuery() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("레코드 추가");

		var rnd = String.format("%04d", MathUtils.getRandomNumber(1, 4096));
		int id = mngr.getNextIdFromDb("employee_id");
		var item = new EmployeeItem(id, "sigwan", "lee", "aa@bb.com" + rnd, "010-0000-0000",
				LocalDate.of(1983, 5, 9), "IT_PROG", 100000, 0.2, 101, 110);
		mngr.doInsertQuery(item);

		// mngr.doSelectQuery();
		String where = null;// "employee_id > 200";
		String orderBy = "employee_id desc";
		mngr.doSelectQuery(null, null, where, orderBy);
		mngr.iterate();

		System.out.println();
	}

	static void testUpdateQuery() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("레코드 수정");

		var item = mngr.getFirstItem();
		String[] fieldset = null;// new String[] { "salary", "hire_date", "first_name" };
		var _where = String.format("employee_id=%d", item.getId());

		item.setSalary(999999);
		item.setHireDate(LocalDate.now());
		item.setFirstName("SIGWAN");
		int rows = mngr.doUpdateQuery(item, fieldset, _where);
		System.out.printf("rows: %d \n", rows);

		// mngr.doSelectQuery();
		String where = null;// "employee_id > 200";
		String orderBy = "employee_id desc";
		mngr.doSelectQuery(null, null, where, orderBy);
		mngr.iterate();

		System.out.println();
	}

	static void testDeleteQuery() throws Exception {
		var mngr = EmployeesManager.getInstance();

		printTitle("레코드 삭제");

		// String _where = "first_name='sigwan'";
		String _where = "salary=999999";
		int rows = mngr.doDeleteQuery(_where);
		System.out.printf("rows: %d \n", rows);

		// mngr.doSelectQuery();
		String where = null;// "employee_id > 200";
		String orderBy = "employee_id desc";
		mngr.doSelectQuery(null, null, where, orderBy);
		mngr.iterate();

		System.out.println();
	}

	public static void main(String[] args) {

		final boolean TEST_CREATE_TABLE = !true;
		final boolean TEST_DROP_TABLE = !true;
		final boolean TEST_TRUNCATE_TABLE = !true;

		final boolean TEST_SELECT_QUERY = true;
		final boolean TEST_INSERT_QUERY = true;
		final boolean TEST_UPDATE_QUERY = true;
		final boolean TEST_DELETE_QUERY = true;

		try {
			var dbConn = DBConnector.getInstance();
			dbConn.loadJdbcDriver();

			// DB 연결
			System.out.printf("[%s] DB 연결 중.. \n", TimeUtils.currTimeStr());
			dbConn.connect("localhost", DBConnector.DEFAULT_PORT_NUM, "hr", "1234");
			System.out.printf("[%s] DB 연결됨 \n", TimeUtils.currTimeStr());
			System.out.println();

			//

			if (TEST_CREATE_TABLE)
				testCreateTable();

			if (TEST_DROP_TABLE)
				testDropTable();

			if (TEST_TRUNCATE_TABLE)
				testTruncateTable();

			//

			if (TEST_SELECT_QUERY)
				testSelectQuery();

			if (TEST_INSERT_QUERY)
				testInsertQuery();

			if (TEST_UPDATE_QUERY)
				testUpdateQuery();

			if (TEST_DELETE_QUERY)
				testDeleteQuery();

			//

			// DB 연결 해제
			if (dbConn.isConnected()) {
				System.out.printf("[%s] DB 연결 해제 중.. \n", TimeUtils.currTimeStr());
				dbConn.disconnect();
				System.out.printf("[%s] DB 연결 해제됨 \n", TimeUtils.currTimeStr());
				System.out.println();
			}

			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
