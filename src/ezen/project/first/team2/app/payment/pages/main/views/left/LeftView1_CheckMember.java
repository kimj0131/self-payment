package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView1_CheckMember extends View {
	private static final int PADDING = 10;

	private static final String INFO_MESSAGE_TEXT = "<html>회원인 경우<br>휴대폰 번호를<br>입력해주세요</html>";
	private static final String PREVIOUS_BUTTON_TEXT = "이전단계";

	JLabel mInfoMessage;
	JButton mPreviousButton;
	
	public LeftView1_CheckMember() {
		super(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
		
		mInfoMessage = new JLabel(INFO_MESSAGE_TEXT);
		mPreviousButton = new JButton(PREVIOUS_BUTTON_TEXT);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mInfoMessage);
		this.add(this.mPreviousButton);
	}

	@Override
	protected void onAddEventListeners() {
		mPreviousButton.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
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
		Main main = (Main) this.getStatusManager();
		mInfoMessage.setFont(main.mFont0);
	}
}
