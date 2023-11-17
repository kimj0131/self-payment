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

import ezen.project.first.team2.app.common.pages.splash.views.MainView;
import ezen.project.first.team2.app.framework.Page;
import ezen.project.first.team2.app.framework.StatusManager;
import ezen.project.first.team2.utils.SystemUtils;

public class SplashPage extends Page {
	public static final int PAGE_NUM = 0;

	private static final String TITLE = "Splash";
	private static final Dimension SIZE = new Dimension(640, 360);

	public static final int VIEW_NUM_MAIN = 0;

	private int mNextPageNum = -1;

	// 생성자
	public SplashPage(int nextPageNum) {
		// 페이지 정보 세팅
		super(SplashPage.PAGE_NUM, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN | Page.OPTION_BORDERLESS);

		this.mNextPageNum = nextPageNum;
	}

	@Override
	protected void onInit() {
		// 3초 후 메인 페이지 선택
		SystemUtils.setTimeout(1 * 1000, e -> {
			try {
				StatusManager stsMngr = SplashPage.this.getStatusManager();
				stsMngr.setSelectedPageByNum(SplashPage.this.mNextPageNum);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
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
	protected void onShow() {
		try {
			this.setSelectedViewByNum(SplashPage.VIEW_NUM_MAIN);
		} catch (Exception e) {
			//
		}
	}

	@Override
	protected void onHide() {
	}
}
