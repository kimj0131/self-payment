package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView3_UsePoints extends View {
	
	private static final int PADDING = 10;
	
	JLabel mInfo;
	JButton mAllUsedButton;
	JButton mCancleButton;
	JButton mCheckButton;
	JPanel mNumberPanel;

	public PopUpView3_UsePoints() {
		super(MainPage.POPUP_VIEW_USE_POINTS_NUM);
	}

	@Override
	protected void onInit() {
		setSize(100, 100);
		
		mInfo = new JLabel("사용가능 포인트");
		mAllUsedButton = new JButton("전부사용");
		mCheckButton = new JButton("확인");
		mCancleButton = new JButton("취소");
		mNumberPanel = new JPanel();
	}

	@Override
	protected void onSetLayout() {}

	@Override
	protected void onAddCtrls() {
		this.add(mInfo);
		this.add(mAllUsedButton);
		this.add(mNumberPanel);
		this.add(mCheckButton);
		this.add(mCancleButton);
		
	}

	@Override
	protected void onAddEventListeners() {
		mCheckButton.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_PAYMENT_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_PAYMENT_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mCancleButton.addActionListener(e -> {
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
