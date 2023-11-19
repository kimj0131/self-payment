////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:YYYYMMDDddd_HHMMSS] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.manager.pages.login.views;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.login.LoginPage;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class LoginView extends View {
	// -------------------------------------------------------------------------

	private JButton mBtnLogin = new JButton();

	// -------------------------------------------------------------------------

	// 생성자
	public LoginView() {
		super(LoginPage.VIEW_NUM_LOGIN);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {
		this.mBtnLogin.setText("Login");

		this.add(this.mBtnLogin);
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
		this.mBtnLogin.addActionListener(e -> {
			UiUtils.showMsgBox("Login Success", MainPage.TITLE);
			try {
				Main main = (Main) LoginView.this.getStatusManager();
				main.setSelectedPageByNum(Main.PAGE_NUM_MAIN);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	// 뷰가 표시될 때
	@Override
	protected void onShow() {
		System.out.println("[MainView.onShow()]");
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[MainView.onHide()]");
	}
}
