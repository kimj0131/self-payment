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

	private static final String PREVIOUS_BUTTON_TEXT = "이전단계";
	
	int mTotalAmount = 50000;
	int mDiscountAmount = 5000;
	String mTextFormat = String.format("<html>총 금액<br>%d<br>할인 금액<br>%d<br>최종 금액<br>%d</html>", 
			mTotalAmount, mDiscountAmount, mTotalAmount - mDiscountAmount);
	
	JLabel mLabel0 = new JLabel();
	JButton mPreviousButton = new JButton();

	public LeftView3_Payment() {
		super(MainPage.LEFT_VIEW_PAYMENT_NUM);
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
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_POINT_INFO_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
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
		mLabel0.setFont(main.mFont0);
	}
}
