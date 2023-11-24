package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView3_UsePoints extends View {
	
	private static final int PADDING = 10;
	
	// 컴포넌트에 설정되는 텍스트 모음
	private static final String AVAIL_POINTS_LABEL_TEXT = "사용가능한 포인트";
	private static final String POINTS_TO_USE_LABEL_TEXT = "사용할 포인트";
	private static final String ALL_USED_BTN_TEXT = "전부사용";
	private static final String CHECK_BTN_TEXT = "확인";
	private static final String CANCLE_BTN_TEXT = "취소";
	
	private JLabel mAvailPoints_label;
	private JLabel mPointsToUse_label;
	
	private JButton mAllUsed_btn;
	private JButton mCheck_btn;
	private JButton mCancle_btn;
	private JPanel mNumbers_panel;

	public PopUpView3_UsePoints() {
		super(MainPage.POPUP_VIEW_USE_POINTS_NUM);
	}

	@Override
	protected void onInit() {
		setSize(100, 100);
		
		mAvailPoints_label = new JLabel(AVAIL_POINTS_LABEL_TEXT);
		mPointsToUse_label = new JLabel(POINTS_TO_USE_LABEL_TEXT);
		
		mAllUsed_btn = new JButton(ALL_USED_BTN_TEXT);
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		mCancle_btn = new JButton(CANCLE_BTN_TEXT);
		
		mNumbers_panel = new JPanel();
	}

	@Override
	protected void onSetLayout() {
		setLayout(new GridLayout(2,3));
	}

	@Override
	protected void onAddCtrls() {
		this.add(mAvailPoints_label);
		this.add(mPointsToUse_label);
		this.add(mAllUsed_btn);
		this.add(mNumbers_panel);
		this.add(mCheck_btn);
		this.add(mCancle_btn);
		
	}

	@Override
	protected void onAddEventListeners() {
		mCheck_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_PAYMENT_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_PAYMENT_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mCancle_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
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
