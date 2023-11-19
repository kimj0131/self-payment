////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_011300] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.pages.splash;

public class SplashPageParams {
	public static interface Listener {
		public void onLoadResources(Object param, int resourceIndex, int resourceCount);

		public void onCompleteResources(Object param);
	}

	private Listener mListener = null;
	private Object mParam = null;
	private int mResourceCount = 0;

	// 생성자
	public SplashPageParams(Listener listener, Object param,
			int resourceCount) {
		this.mListener = listener;
		this.mParam = param;
		this.mResourceCount = resourceCount;
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
}
