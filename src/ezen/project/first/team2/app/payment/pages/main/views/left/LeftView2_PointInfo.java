package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerInfo;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView1_CheckMember;

public class LeftView2_PointInfo extends View {
	private static final int PADDING = 10;
	
	private static final String PREVIOUS_BUTTON_TEXT = "이전단계";
	private static final String LABEL_TEXT_FORMAT = 
			"<html>%s 고객님<br>확인해<br>주셔서<br>감사합니다</html>";
	
	String mMemberName;
	String mTextFormat;
	
	JLabel mLabel0 = new JLabel();
	JButton mPreviousButton = new JButton();
	
	CustomerInfo mCustomerInfo;
	
	public LeftView2_PointInfo() {
		super(MainPage.LEFT_VIEW_POINT_INFO_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		mLabel0.setText(mTextFormat);
		this.add(this.mLabel0);
		
		mPreviousButton.setText(PREVIOUS_BUTTON_TEXT);
		this.add(mPreviousButton);
	}

	@Override
	protected void onAddEventListeners() {
		mPreviousButton.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
		MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
		RightView1_CheckMember rightView = (RightView1_CheckMember) mainView.getViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
		mCustomerInfo = rightView.getCustomerInfo();
		
		mMemberName = mCustomerInfo.getName();
		mLabel0.setText(String.format(LABEL_TEXT_FORMAT, mMemberName));
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mLabel0.setFont(main.mFont0);
	}
}
