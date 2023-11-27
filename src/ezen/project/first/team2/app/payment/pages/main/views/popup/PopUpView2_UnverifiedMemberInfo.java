package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView2_UnverifiedMemberInfo extends View {
	
	private static final int PADDING = 10;
	
	private static final String MSG_LABEL_TEXT = "없는 회원입니다";
	private static final String CHECK_BTN_TEXT = "확인";
	
	JLabel mMsg_label;
	JButton mCheck_btn;

	public PopUpView2_UnverifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_UNVERIFIED_MEMBER_INFO_NUM);
	}

	@Override
	protected void onInit() {
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
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {}

	@Override
	protected void onHide() {}

	@Override
	protected void onSetResources() {}
	
}
