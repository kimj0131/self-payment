package ezen.project.first.team2.app.common.z_test.etc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;

import ezen.project.first.team2.app.common.utils.MathUtils;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class JdbcTest {
	static void printTitle(String text) {
		System.out.println("-".repeat(60));
		System.out.printf("[%s] # %s \n", TimeUtils.currTimeStr(), text);
		System.out.println("-".repeat(60));
	}

	static void testSelectQuery(Connection conn) throws Exception {
		printTitle("select 쿼리");

		String sql = "select * from employees where employee_id > ? order by employee_id desc";
		PreparedStatement pstmt = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		pstmt.setInt(1, 200);

		ResultSet rs = pstmt.executeQuery();

		// 개수 얻기
		int rcnt = 0;
		while (rs.next())
			rcnt++;
		rs.beforeFirst();

		System.out.printf("[%s] 레코드 개수: %d \n", TimeUtils.currTimeStr(), rcnt);

		while (rs.next()) {
			System.out.printf("[%s] %4s | %-12s | %-12s | %s | %6d \n",
					TimeUtils.currTimeStr(),
					rs.getString("employee_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("hire_date"),
					rs.getInt("salary"));
		}

		rs.close();
		pstmt.close();

		System.out.println();
	}

	static void testInsertQuery(Connection conn) throws Exception {
		printTitle("insert 쿼리");

		String sql = "insert into employees(" +
				"employee_id, first_name, last_name, email, " +
				"phone_number, hire_date, job_id, salary, " +
				"commission_pct, manager_id, department_id" +
				") values(employees_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		Object[] values = {
				"sigwan", "lee", "id@domain.com" + MathUtils.getRandomNumber(1, 4096),
				"000.000.000", LocalDate.now(), "IT_PROG", 100000,
				0.5, null, 90
		};

		int idx = 1;
		for (var v : values) {
			if (v instanceof String)
				pstmt.setString(idx++, (String) v);
			else if (v instanceof Integer)
				pstmt.setInt(idx++, (Integer) v);
			else if (v instanceof Double)
				pstmt.setDouble(idx++, (Double) v);
			else if (v instanceof LocalDate)
				pstmt.setDate(idx++, Date.valueOf((LocalDate) v));
			else if (v == null)
				pstmt.setNull(idx++, Types.NUMERIC);
		}

		int rows = pstmt.executeUpdate();
		System.out.printf("[%s] pstmt.executeUpdate() => %d \n", TimeUtils.currTimeStr(), rows);

		pstmt.close();

		System.out.println();
	}

	static void testUpdateQuery(Connection conn) throws Exception {
		printTitle("update 쿼리");

		String sql = "update employees set salary=salary+? where first_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 10000);
		pstmt.setString(2, "sigwan");

		int rows = pstmt.executeUpdate();
		System.out.printf("pstmt.executeUpdate() => %d \n", rows);

		pstmt.close();

		System.out.println();
	}

	static void testDeleteQuery(Connection conn) throws Exception {
		printTitle("delete 쿼리");

		String sql = "delete from employees where first_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "sigwan");

		int rows = pstmt.executeUpdate();
		System.out.printf("[%s] pstmt.executeUpdate() => %d \n", TimeUtils.currTimeStr(), rows);

		pstmt.close();

		System.out.println();
	}

	public static void main(String[] args) {
		System.out.printf("[%s] Hello, Jdbc! \n", TimeUtils.currTimeStr());
		System.out.println();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String ip = "localhost";
			// String ip = "192.168.0.64";
			int port = 1521;
			String str = String.format("jdbc:oracle:thin:@%s:%d:XE", ip, port);
			String id = "hr";
			String pw = "1234";

			System.out.printf("[%s] Getting connection.. (%s:%d) \n",
					TimeUtils.currTimeStr(), ip, port);
			TimeUtils.startElapsedTime();
			// ----------
			Connection conn = DriverManager.getConnection(str, id, pw);
			// ----------
			System.out.printf("[%s]  => elapsed: %s \n", TimeUtils.currTimeStr(), TimeUtils.getElapsedTimeStr());
			System.out.println();

			// select 쿼리
			testSelectQuery(conn);

			// insert 쿼리
			testInsertQuery(conn);
			testSelectQuery(conn);

			// update 쿼리
			testUpdateQuery(conn);
			testSelectQuery(conn);

			// delete 쿼리
			testDeleteQuery(conn);
			testSelectQuery(conn);

			// ----------
			conn.close();
			// ----------
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}