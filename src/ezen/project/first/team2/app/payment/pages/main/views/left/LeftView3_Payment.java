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

public class LeftView3_Payment extends View {
	private static final int PADDING = 10;

	private static final String INFO_MESSAGE_FORMAT = "<html>총 금액<br>%d<br>할인 금액<br>%d<br>최종 금액<br>%d</html>";
	private static final String PREVIOUS_BUTTON_TEXT = "이전단계";
	
	int mTotalAmount = 50000;
	int mDiscountAmount = 5000;
	
	JLabel mInfoMessage;
	JButton mPreviousButton;

	public LeftView3_Payment() {
		super(MainPage.LEFT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
		
		mInfoMessage = new JLabel();
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
		mInfoMessage.setText(String.format(INFO_MESSAGE_FORMAT, mTotalAmount, mDiscountAmount, mTotalAmount - mDiscountAmount));
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
