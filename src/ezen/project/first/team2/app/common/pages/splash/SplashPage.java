////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231114TUE_101700] Created
// [SGLEE:20231117FRI_103800]
//		스플래시 페이지에서 앱의 초기화 작업을 할 수 있도록 코드 업데이트 예정
//		ex) 폰트 객체 할당, 이미지 로딩 등
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.pages.splash;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.common.framework.StatusManager;
import ezen.project.first.team2.app.common.pages.splash.views.MainView;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.common.utils.thread.BlueThread;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadListener;

public class SplashPage extends Page {
	public static final int PAGE_NUM = 0;
	public static final int DEFAULT_NEXT_PAGE_TIMEOUT = 3 * 1000;

	private static final String TITLE = "Splash";
	private static final Dimension SIZE = new Dimension(640, 360);

	public static final int VIEW_NUM_MAIN = 0;

	private int mNextPageNum = -1;
	private int mNextPageTimeout = -1;
	private SplashPageParams mParams = null;

	// 생성자 - 다음 페이지 정보 설정
	public SplashPage(int nextPageNum, int nextPageTimeout) {
		// 페이지 정보 세팅
		super(SplashPage.PAGE_NUM, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN | Page.OPTION_BORDERLESS);

		this.mNextPageNum = nextPageNum;
		this.mNextPageTimeout = nextPageTimeout;
	}

	// 생성자 - 다음 페이지 정보 설정
	public SplashPage(int nextPageNum) {
		this(nextPageNum, DEFAULT_NEXT_PAGE_TIMEOUT);
	}

	// 생성자 - 파라미터 설정
	public SplashPage(SplashPageParams params) {
		// 페이지 정보 세팅
		super(SplashPage.PAGE_NUM, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN | Page.OPTION_BORDERLESS);

		this.mParams = params;
	}

	@Override
	protected void onInit() {
		// 다음 페이지가 유효한 경우 타이머 설정
		this.setTimerIfValidNextPageNum();

		// 리스너가 설정된 경우 리소스 로드 스레드 생성
		this.createThreadIfValidParams();
	}

	@Override
	protected void onAddViews() {
		try {
			this.addView(new MainView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow(boolean firstTime) {
		// System.out.println("[SplashPage.onShow()]");

		try {
			this.setSelectedViewByNum(SplashPage.VIEW_NUM_MAIN);
		} catch (Exception e) {
			//
		}
	}

	@Override
	protected void onHide() {
		// System.out.println("[SplashPage.onHide()]");
	}

	private void setTimerIfValidNextPageNum() {
		if (this.mNextPageNum == -1)
			return;

		SystemUtils.setTimeout(this.mNextPageTimeout, e -> {
			try {
				StatusManager stsMngr = SplashPage.this.getStatusManager();
				stsMngr.setSelectedPageByNum(SplashPage.this.mNextPageNum);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	private void createThreadIfValidParams() {
		if (this.mParams == null)
			return;

		BlueThread t = new BlueThread(new BlueThreadListener() {
			private int mRsrcIdx = 0;

			@Override
			public boolean onStart(BlueThread sender, Object param) {
				// 페이지가 추가될 때까지 잠시 기다린다
				// SystemUtils.sleep(100);

				// 1초 동안 스플래시 화면을 표시한다
				SystemUtils.sleep(1 * 1000);

				return true;
			}

			@Override
			public boolean onRun(BlueThread sender, Object param) {
				SplashPageParams.Listener listener = SplashPage.this.mParams.getListener();
				int rsrcCnt = SplashPage.this.mParams.getResurceCount();

				listener.onLoadResources(param, this.mRsrcIdx, rsrcCnt);
				if (this.mRsrcIdx++ == rsrcCnt) {
					// 1초 동안 스플래시 화면을 표시한다
					SystemUtils.sleep(1 * 1000);

					listener.onCompleteResources(param);

					// 스레드 종료
					return false;
				}

				// 스레드 계속
				return true;
			}

			@Override
			public void onStop(BlueThread sender, Object param, boolean interrupted) {
				//
			}

		}, this.mParams.getParam());

		try {
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
