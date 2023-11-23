package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView2_UnverifiedMemberInfo extends View {
	
	private static final int PADDING = 10;
	
	JLabel mInfoLabel;
	JButton mCheckButton;

	public PopUpView2_UnverifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_UNVERIFIED_MEMBER_INFO_NUM);
	}

	@Override
	protected void onInit() {
		mInfoLabel = new JLabel("입력된 번호로 조회가 되지 않았습니다");
		mCheckButton = new JButton("확인");
	}

	@Override
	protected void onSetLayout() {
		setLayout(new GridLayout(2, 1));
	}

	@Override
	protected void onAddCtrls() {
		add(mInfoLabel);
		add(mCheckButton);
	}

	@Override
	protected void onAddEventListeners() {
		mCheckButton.addActionListener(e -> {
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
