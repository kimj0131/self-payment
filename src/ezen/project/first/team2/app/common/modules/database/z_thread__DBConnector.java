////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_110100] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicBoolean;

import ezen.project.first.team2.app.common.utils.TimeUtils;
import ezen.project.first.team2.app.common.utils.thread.BlueThread;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadEx;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadExListener;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadExMessage;

public class z_thread__DBConnector {
	// --------------------------------------------------------------------------

	public static final int DEFAULT_PORT_NUM = 1521;
	// public static final int DEFAULT_TIMEOUT = 5 * 1000;

	// --------------------------------------------------------------------------

	private static z_thread__DBConnector mInstance;
	private boolean mJdbcDriverLoaded;

	private z_thread__DBConnectorListener mListener;

	private AtomicBoolean mConnected = new AtomicBoolean(false);
	private BlueThreadEx mDbThread;

	private String mHost;
	private int mPortNum;
	private String mId;
	private String mPasswd;
	// private int mTimeout;

	private Connection mDbConn;

	// --------------------------------------------------------------------------

	// 생성자
	private z_thread__DBConnector() {
	}

	public static z_thread__DBConnector getInstance() {
		if (mInstance == null) {
			mInstance = new z_thread__DBConnector();
		}

		return mInstance;
	}

	public Connection getConnection() throws Exception {
		// 연결 여부 확인
		if (!this.isConnected()) {
			String msg = String.format("[DBTable.getConnection()]" +
					" You must connect database!");
			throw new Exception(msg);
		}

		return this.mDbConn;
	}

	// --------------------------------------------------------------------------

	public void loadJdbcDriver() throws Exception {
		try {
			System.out.println("[DBTable.loadJdbcDriver()] start");
			TimeUtils.startElapsedTime();

			//
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//
			this.mJdbcDriverLoaded = true;

			System.out.println("[DBTable.loadJdbcDriver()] end => " + TimeUtils.getElapsedTimeStr());
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isJdbcDriverLoaded() {
		return this.mJdbcDriverLoaded;
	}

	// 액션 리스너 설정 (필수)
	public void setActionListener(z_thread__DBConnectorListener listener) {
		this.mListener = listener;
	}

	// --------------------------------------------------------------------------

	// 데이터베이스 연결
	public void connect(String host, int portNum, String id, String passwd) throws Exception {
		// JDBC 드라이버 로드 확인
		if (!this.isJdbcDriverLoaded()) {
			String msg = String.format("[DBTable.connect()]" +
					" You must load JDBC Driver!");
			throw new Exception(msg);
		}

		// 액션 리스너 설정 확인
		if (this.mListener == null) {
			String msg = String.format("[DBTable.connect()]" +
					" You must call setActionListener()!");
			throw new Exception(msg);
		}

		// 연결 여부 확인
		if (this.isConnected()) {
			String msg = String.format("[DBTable.connect()]" +
					" Already connected!");
			throw new Exception(msg);
		}

		this.mHost = host;
		this.mPortNum = portNum;
		this.mId = id;
		this.mPasswd = passwd;
		// this.mTimeout = timeout;

		// DB 스레드 시작
		this.startDbThread();
	}

	// 데이터베이스 연결 해제
	public void disconnect() throws Exception {
		// JDBC 드라이버 로드 확인
		if (!this.isJdbcDriverLoaded()) {
			String msg = String.format("[DBTable.connect()]" +
					" You must load JDBC Driver!");
			throw new Exception(msg);
		}

		// 액션 리스너 설정 확인
		if (this.mListener == null) {
			String msg = String.format("[DBTable.disconnect()]" +
					" You must call setActionListener()!");
			throw new Exception(msg);
		}

		// 연결 여부 확인
		if (!this.isConnected()) {
			String msg = String.format("[DBTable.connect()]" +
					" Already disconnected!");
			throw new Exception(msg);
		}

		// DB 스레드 종료
		this.stopDbThread();
	}

	// 데이터베이스 연결 여부 확인
	public boolean isConnected() {
		return this.mConnected.get();
	}

	// --------------------------------------------------------------------------

	private void startDbThread() {
		this.mDbThread = new BlueThreadEx(new BlueThreadExListener() {

			@Override
			public boolean onStart(BlueThread sender, Object param) {
				// 액션 리스너 호출
				mListener.onConnecting(z_thread__DBConnector.this);

				try {
					// DB 연결
					String str = String.format("jdbc:oracle:thin:@%s:%d:XE", mHost, mPortNum);
					mDbConn = DriverManager.getConnection(str, mId, mPasswd);
					mConnected.set(true);

					// 액션 리스너 호출
					mListener.onConnected(z_thread__DBConnector.this);
				} catch (Exception e) {
					// e.printStackTrace();

					// 액션 리스너 호출
					mListener.onConnectionFailure(z_thread__DBConnector.this, e.getMessage());

					return false;
				}

				return true;
			}

			@Override
			public boolean onRun(BlueThread sender, Object param) {
				return true;
			}

			@Override
			public void onStop(BlueThread sender, Object param, boolean interrupted) {
				try {
					// DB 연결 해제
					if (mConnected.get()) {
						mDbConn.close();
						mConnected.set(false);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 액션 리스너 호출
				mListener.onDisconnected(z_thread__DBConnector.this);

			}

			@Override
			public void onReceviedMessage(BlueThreadEx sender, Object param, BlueThreadExMessage msg) {
				//
			}

		}, this);

		try {
			this.mDbThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stopDbThread() {
		try {
			this.mDbThread.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
