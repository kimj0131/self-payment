package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView1_CheckMember extends View {

	private static final int PADDING = 10;

	JButton mButton0 = new JButton("확인");
	JButton mButton1 = new JButton("적립안함");
	
	// 숫자패드
	JTextField mPhoneNumber = new JTextField();
	JPanel mNumberPanel = new JPanel();
	JButton[] mNumberBtnArr = new JButton[10];
	//
	
	public RightView1_CheckMember() {
		super(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout());
		mNumberPanel.setLayout(new BorderLayout());
	}

	@Override
	protected void onAddCtrls() {
		// 숫자 패널 만들기
		ActionListener BtnActionListener =  e -> {
			for (int i = 0; i < mNumberBtnArr.length; ++i) {
				if (e.getSource() == mNumberBtnArr[i]) {
					String PhoneNumber = mPhoneNumber.getText();
					mPhoneNumber.setText(PhoneNumber + i);
					break;
				}
			}
		};
		
		for (int i = 0; i < mNumberBtnArr.length; ++i) {
			mNumberBtnArr[i] = new JButton(String.valueOf(i));
			mNumberBtnArr[i].addActionListener(BtnActionListener);
		}
		
		for (JButton btn : mNumberBtnArr) {
			mNumberPanel.add(btn);
		}
		
		
		this.add(mPhoneNumber, "North");
		this.add(mNumberPanel, "Center");
		this.add(mButton0, "East");
		this.add(mButton1, "East");
	}

	@Override
	protected void onAddEventListeners() {
		mButton0.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_POINT_INFO_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		mButton1.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_PAYMENT_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_PAYMENT_NUM);
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

}
