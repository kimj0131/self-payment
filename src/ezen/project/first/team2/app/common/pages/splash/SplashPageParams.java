////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_011300] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.pages.splash;

public class SplashPageParams {
	public final static String DB_HOST = "192.168.0.64";
	public final static int DB_PORT = 1521;
	public final static String DB_ID = "hr";
	public final static String DB_PW = "1234";

	public static interface Listener {
		public void onConnectingDb(Object param);

		public void onLoadResources(Object param, int resourceIndex, int resourceCount);

		public void onCompleteResources(Object param);
	}

	private Listener mListener = null;
	private Object mParam = null;
	private int mResourceCount = 0;

	private String mDbHost;
	private int mDbPort;
	private String mDbId;
	private String mDbPw;

	// 생성자
	public SplashPageParams(Listener listener, Object param, int resourceCount,
			String dbHost, int dbPort, String dbId, String dbPw) {
		this.mListener = listener;
		this.mParam = param;
		this.mResourceCount = resourceCount;

		this.mDbHost = dbHost;
		this.mDbPort = dbPort;
		this.mDbId = dbId;
		this.mDbPw = dbPw;
	}

	public SplashPageParams(Listener listener, Object param, int resourceCount) {
		this(listener, param, resourceCount, DB_HOST, DB_PORT, DB_ID, DB_PW);
	}

	public Listener getListener() {
		return this.mListener;
	}

	public Object getParam() {
		return this.mParam;
	}

	public int getResurceCount() {
		return this.mResourceCount;
	}

	public String getDbHost() {
		return this.mDbHost;
	}

	public int getDbPort() {
		return this.mDbPort;
	}

	public String getDbId() {
		return this.mDbId;
	}

	public String getDbPw() {
		return this.mDbPw;
	}
}
