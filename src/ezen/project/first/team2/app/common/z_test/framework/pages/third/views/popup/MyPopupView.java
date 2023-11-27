package ezen.project.first.team2.app.common.z_test.framework.pages.third.views.popup;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.ThirdPage;

public class MyPopupView extends PopupView {

	private static final Dimension VIEW_SIZE = new Dimension(320, 180);

	private JButton mCloseBtn = new JButton();

	public MyPopupView() {
		super(ThirdPage.POPUP_VIEW_NUM_MY, VIEW_SIZE);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();

		this.setBackground(Color.BLUE);
	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {
		this.mCloseBtn.setText("닫기");

		this.add(this.mCloseBtn);
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
		this.mCloseBtn.addActionListener(e -> {
			performClose();
		});
	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		//
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		//
	}
}
