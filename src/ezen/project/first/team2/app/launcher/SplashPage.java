////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231114TUE_101700] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.launcher;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.Page;
import ezen.project.first.team2.utils.SystemUtils;

public class SplashPage extends Page {
	private static final String TITLE = "Splash";
	private static final Dimension SIZE = new Dimension(640, 360);

	// 생성자
	public SplashPage() {
		// 페이지 정보 세팅
		super(Main.PAGE_NUM_SPLASH, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN | Page.OPTION_BORDERLESS);
	}

	@Override
	protected void onInit() {
		// 3초 후 메인 페이지 선택
		SystemUtils.setTimeout(1 * 1000, e -> {
			try {
				Main main = (Main) SplashPage.this.getStatusManager();
				main.setSelectPageByNum(Main.PAGE_NUM_MAIN);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onAddViews() {
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow() {
		// System.out.println(this.getTitle() + " => onShow()");
	}

	@Override
	protected void onHide() {
		// System.out.println(this.getTitle() + " => onHide()");
	}
}
