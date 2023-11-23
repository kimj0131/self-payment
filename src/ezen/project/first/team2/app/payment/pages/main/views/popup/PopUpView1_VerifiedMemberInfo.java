package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView1_VerifiedMemberInfo extends View {
	
	private static final int PADDING = 10;
	
	JLabel mNameLabel;
	JButton mCheckButton;
	JButton mCancelButton;

	public PopUpView1_VerifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_VERIFIED_MEMBER_INFO_NUM);
	}

	@Override
	protected void onInit() {
		
		mNameLabel = new JLabel("@@@ 회원님");
		mCheckButton = new JButton("확인");
		mCancelButton = new JButton("재입력");
		
		mNameLabel.setBackground(Color.WHITE);
	}

	@Override
	protected void onSetLayout() {
		setLayout(new GridLayout(2, 2));
	}

	@Override
	protected void onAddCtrls() {
		this.add(mNameLabel);
		this.add(mCheckButton);
		this.add(mCancelButton);
	}

	@Override
	protected void onAddEventListeners() {
		
		mCheckButton.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_POINT_INFO_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		mCancelButton.addActionListener(e -> {
			// 유효한
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
