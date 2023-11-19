////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_124500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.launcher.pages.main.views;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.launcher.pages.main.MainPage;

public class MainView extends View {
	// -------------------------------------------------------------------------

	private JButton mBtn0 = new JButton();

	// -------------------------------------------------------------------------

	// 생성자
	public MainView() {
		super(MainPage.VIEW_NUM_MAIN);
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
		this.mBtn0.setText("버튼0");

		this.add(this.mBtn0);
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
		this.mBtn0.addActionListener(e -> {
			UiUtils.showMsgBox("버튼0", MainPage.TITLE);
		});
	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		// System.out.println("[MainView.onShow()]");
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		// System.out.println("[MainView.onHide()]");
	}
}
