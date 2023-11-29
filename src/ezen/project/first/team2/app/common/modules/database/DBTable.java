////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_110100] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

import ezen.project.first.team2.app.common.utils.BlueThread;
import ezen.project.first.team2.app.common.utils.BlueThread.Listener;

public class DBTable {
	// --------------------------------------------------------------------------

	public static final int DEFAULT_PORT_NUM = 1531;

	// --------------------------------------------------------------------------

	private DBTableActionListener mListener;

	private AtomicBoolean mConnected = new AtomicBoolean(false);
	private BlueThread mDbThread;

	private String mHost;
	private int mPortNum;
	private String mId;
	private String mPasswd;

	private Connection mDbConn;

	// --------------------------------------------------------------------------

	// 생성자
	public DBTable() {
	}

	// --------------------------------------------------------------------------

	// 액션 리스너 설정 (필수)
	public void setActionListener(DBTableActionListener listener) {
		this.mListener = listener;
	}

	// 데이터베이스 연결
	public void connect(String host, int portNum, String id, String passwd) throws Exception {
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

		// DB 스레드 시작
		this.startDbThread();
	}

	// 데이터베이스 연결 해제
	public void disconnect() throws Exception {
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
		this.mDbThread = new BlueThread(new Listener() {

			@Override
			public void onStart(BlueThread sender, Object param) {
			}

			@Override
			public boolean onRun(BlueThread sender, Object param) {

				// DB 연결

				// DB 연결 해제

				return true;
			}

			@Override
			public void onStop(BlueThread sender, Object param, boolean interrupted) {
			}

		}, this);
	}

	private void stopDbThread() {
		try {
			this.mDbThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
