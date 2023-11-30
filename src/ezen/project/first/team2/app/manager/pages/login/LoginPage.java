////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:YYYYMMDDddd_HHMMSS] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.manager.pages.login;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.login.views.LoginView;

public class LoginPage extends Page {
	// -------------------------------------------------------------------------

	// 페이지 정보 상수 정의

	public static final String TITLE = "시스템 로그인";
	public static final Dimension SIZE = new Dimension(960, 540);

	public static final int VIEW_NUM_LOGIN = 1;

	// -------------------------------------------------------------------------

	//

	// -------------------------------------------------------------------------

	// 생성자
	public LoginPage() {
		super(Main.PAGE_NUM_LOGIN, TITLE, SIZE,
				OPTION_CENTER_IN_SCREEN |
						OPTION_VISIBLE * 0 |
						OPTION_BORDERLESS * 0 |
						OPTION_FULL_SCREEN * 0 |
						OPTION_FIXED_SIZE * 0);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
	}

	// 뷰 추가
	@Override
	protected void onAddViews() {
		try {
			this.addView(new LoginView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
	}

	// 페이지가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[LoginPage.onShow()]");

		try {
			this.setSelectedViewByNum(LoginPage.VIEW_NUM_LOGIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 페이지가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[LoginPage.onHide()]");
	}
}
