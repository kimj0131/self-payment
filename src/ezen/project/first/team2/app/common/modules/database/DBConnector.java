///////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_110100] Created
// [SGLEE:20231201FRI_094100] 스레드를 사용하지 않도록 변경
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

import java.sql.Connection;
import java.sql.DriverManager;

import ezen.project.first.team2.app.common.utils.TimeUtils;

public class DBConnector {
	// --------------------------------------------------------------------------

	public static final int DEFAULT_PORT_NUM = 1521;
	// public static final int DEFAULT_TIMEOUT = 5 * 1000;

	// --------------------------------------------------------------------------

	private static DBConnector mInstance;
	private boolean mJdbcDriverLoaded;

	private boolean mConnected;

	private String mHost;
	private int mPortNum;
	private String mId;
	private String mPasswd;
	// private int mTimeout;

	private Connection mDbConn;

	// --------------------------------------------------------------------------

	// 생성자
	private DBConnector() {
	}

	public static DBConnector getInstance() {
		if (mInstance == null) {
			mInstance = new DBConnector();
		}

		return mInstance;
	}

	public Connection getConnection() throws Exception {
		// 연결 여부 확인
		if (!this.isConnected()) {
			String msg = String.format("[DBConnector].getConnection()]" +
					" You must connect database!");
			throw new Exception(msg);
		}

		return this.mDbConn;
	}

	// --------------------------------------------------------------------------

	public void loadJdbcDriver() throws Exception {
		try {
			System.out.println("[DBConnector.loadJdbcDriver()] start");
			TimeUtils.startElapsedTime();

			//
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//
			this.mJdbcDriverLoaded = true;

			System.out.println("[DBConnector.loadJdbcDriver()] end => " + TimeUtils.getElapsedTimeStr());
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isJdbcDriverLoaded() {
		return this.mJdbcDriverLoaded;
	}

	// --------------------------------------------------------------------------

	// 데이터베이스 연결
	public void connect(String host, int portNum, String id, String passwd) throws Exception {
		// JDBC 드라이버 로드 확인
		if (!this.isJdbcDriverLoaded()) {
			String msg = String.format("[DBConnector.connect()]" +
					" You must load JDBC Driver!");
			throw new Exception(msg);
		}

		// 연결 여부 확인
		if (this.isConnected()) {
			String msg = String.format("[DBConnector.connect()]" +
					" Already connected!");
			throw new Exception(msg);
		}

		this.mHost = host;
		this.mPortNum = portNum;
		this.mId = id;
		this.mPasswd = passwd;
		// this.mTimeout = timeout;

		// DB 연결
		try {
			String str = String.format("jdbc:oracle:thin:@%s:%d:XE",
					DBConnector.this.mHost, DBConnector.this.mPortNum);
			DBConnector.this.mDbConn = DriverManager.getConnection(
					str, DBConnector.this.mId, DBConnector.this.mPasswd);

			this.mConnected = true;
		} catch (Exception e) {
			String msg = String.format("[DBConnector.connect()]" +
					" Connection failed => %s", e.getMessage());
			throw new Exception(msg);
		}
	}

	// 데이터베이스 연결 해제
	public void disconnect() throws Exception {
		// JDBC 드라이버 로드 확인
		if (!this.isJdbcDriverLoaded()) {
			String msg = String.format("[DBConnector.connect()]" +
					" You must load JDBC Driver!");
			throw new Exception(msg);
		}

		// 연결 여부 확인
		if (!this.isConnected()) {
			String msg = String.format("[DBConnector.connect()]" +
					" Already disconnected!");
			throw new Exception(msg);
		}

		// DB 연결 해제
		this.mDbConn.close();
		this.mConnected = false;
	}

	// 데이터베이스 연결 여부 확인
	public boolean isConnected() {
		return this.mConnected;
	}
}
