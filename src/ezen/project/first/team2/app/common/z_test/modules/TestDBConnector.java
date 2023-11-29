package ezen.project.first.team2.app.common.z_test.modules;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.database.DBConnector;
import ezen.project.first.team2.app.common.modules.database.DBConnectorListener;
import ezen.project.first.team2.app.common.utils.MathUtils;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class TestDBConnector {
	static void printTitle(String text) {
		System.out.println("-".repeat(60));
		System.out.printf("[%s] # %s \n", TimeUtils.currTimeStr(), text);
		System.out.println("-".repeat(60));
	}

	// 레코드셋 리스팅
	static void listRecordset(DBConnector dbConn) {
		printTitle("select 쿼리");

		try {
			String sql = "select * from employees where employee_id > ? order by employee_id desc";
			PreparedStatement pstmt = dbConn.getConnection().prepareStatement(sql,
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	// 레코드 추가
	static void addRecord(DBConnector dbConn) {
		printTitle("insert 쿼리");

		try {
			String sql = "insert into employees(" +
					"employee_id, first_name, last_name, email, " +
					"phone_number, hire_date, job_id, salary, " +
					"commission_pct, manager_id, department_id" +
					") values(employees_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = dbConn.getConnection().prepareStatement(sql);

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
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	// 레코드 수정
	static void updateRecord(DBConnector dbConn) {
		printTitle("update 쿼리");

		try {
			String sql = "update employees set salary=salary+? where first_name=?";
			PreparedStatement pstmt = dbConn.getConnection().prepareStatement(sql);
			pstmt.setInt(1, 10000);
			pstmt.setString(2, "sigwan");

			int rows = pstmt.executeUpdate();
			System.out.printf("pstmt.executeUpdate() => %d \n", rows);

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();

	}

	// 레코드 삭제
	static void deleteRecord(DBConnector dbConn) {
		printTitle("delete 쿼리");

		try {
			String sql = "delete from employees where first_name=?";
			PreparedStatement pstmt = dbConn.getConnection().prepareStatement(sql);
			pstmt.setString(1, "sigwan");

			int rows = pstmt.executeUpdate();
			System.out.printf("[%s] pstmt.executeUpdate() => %d \n", TimeUtils.currTimeStr(), rows);

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();

	}

	public static void main(String[] args) {
		System.out.println("Test DBTable class");

		try {
			var conn = DBConnector.getInstance();
			conn.loadJdbcDriver();

			conn.setActionListener(new DBConnectorListener() {

				@Override
				public void onConnecting(DBConnector sender) {
					System.out.println("DB 연결 중..");
				}

				@Override
				public void onConnected(DBConnector sender) {
					System.out.println("DB 연결됨");

					// 레코드셋 리스팅
					listRecordset(sender);

					// 레코드 추가
					addRecord(sender);
					listRecordset(sender);

					// 레코드 수정
					updateRecord(sender);
					listRecordset(sender);

					// 레코드 삭제
					deleteRecord(sender);
					listRecordset(sender);
				}

				@Override
				public void onConnectionFailure(DBConnector sender, String reason) {
					System.out.println("DB 연결 실패 => " + reason);
				}

				@Override
				public void onDisconnected(DBConnector sender) {
					System.out.println("DB 연결 해제");
				}

			});

			System.out.printf("[%s] [main] start \n", TimeUtils.currTimeStr());

			// DB 접속
			conn.connect("localhost", DBConnector.DEFAULT_PORT_NUM, "hr", "1234");

			SystemUtils.sleep(10 * 1000);
			// for (int i = 0; i < 10; i++) {
			// System.out.printf("[main] i=%d \n", i);
			// SystemUtils.sleep(1000);
			// }

			// DB 접속 해제
			if (conn.isConnected()) {
				conn.disconnect();
			}

			System.out.printf("[%s] [main] end \n", TimeUtils.currTimeStr());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
