package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView2_UnverifiedMemberInfo extends PopupView {

	// private static final int PADDING = 10;
	private static final Dimension VIEW_SIZE = new Dimension(500, 300);

	private static final String MSG_LABEL_TEXT = "없는 회원입니다";
	private static final String CHECK_BTN_TEXT = "확인";

	JLabel mMsg_label;
	JButton mCheck_btn;

	public PopUpView2_UnverifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_UNVERIFIED_MEMBER_INFO_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		super.onInit();

		mMsg_label = new JLabel(MSG_LABEL_TEXT);
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
	}

	@Override
	protected void onSetLayout() {
		setLayout(new GridLayout(2, 1));
	}

	@Override
	protected void onAddCtrls() {
		add(mMsg_label);
		add(mCheck_btn);
	}

	@Override
	protected void onAddEventListeners() {
		mCheck_btn.addActionListener(e -> {
			try {
				performClose();
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
	}

}
