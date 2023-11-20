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

	JButton mButton0;
	JButton mButton1;
	
	// 숫자패드
	StringBuilder mPhoneNumber;
	StringBuilder mHideNumber;
	JTextField mNumberTextField;
	JPanel mNumberPanel;
	JButton[] mNumberBtnArr;
	JButton mDeleteBtn;
	JButton mUndoBtn;
	//
	
	public RightView1_CheckMember() {
		super(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
		
		// 버튼 초기화
		mButton0 = new JButton("확인");
		mButton1 = new JButton("적립안함");
		
		// 숫자패드 초기화
		mPhoneNumber = new StringBuilder();
		mHideNumber = new StringBuilder();
		mNumberTextField = new JTextField("010-");
		mNumberPanel = new JPanel();
		mNumberBtnArr = new JButton[10];
		mDeleteBtn = new JButton("전체삭제");
		mUndoBtn = new JButton("삭제");
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout(2, 2));
		
		mNumberPanel.setLayout(new GridLayout(4, 3));
	}

	@Override
	protected void onAddCtrls() {
		
		// 숫자 버튼 만들기
		for (int i = 0; i < mNumberBtnArr.length; ++i) {
			mNumberBtnArr[i] = new JButton(String.valueOf(i));
		}
		// 숫자패널에 숫자버튼 달기
		for (JButton btn : mNumberBtnArr) {
			if (btn.getName() != "0")
				mNumberPanel.add(btn);
		}
		mNumberPanel.add(mDeleteBtn);
		mNumberPanel.add(mNumberBtnArr[0]);
		mNumberPanel.add(mUndoBtn);
		
		
		this.add(mNumberTextField);
		this.add(mButton0);
		this.add(mNumberPanel);
		this.add(mButton1);
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
		
		// 숫자패드 버튼 액션리스너 만들기
		ActionListener BtnActionListener =  e -> {
			for (int i = 0; i < mNumberBtnArr.length; ++i) {
				if (e.getSource() == mNumberBtnArr[i]) {
					mPhoneNumber.append(i);
					mHideNumber.append(i);
					mNumberTextField.setText("010-" + mHideNumber);
					mHideNumber.setCharAt(mHideNumber.length() - 1, '*');
					break;
				}
			}
			
			if (e.getSource() == mDeleteBtn) {
				mPhoneNumber.delete(0, mPhoneNumber.length());
				mHideNumber.delete(0, mHideNumber.length());
				mNumberTextField.setText("010-");
			}
			
			if (e.getSource() == mUndoBtn) {
				
				if (mPhoneNumber.length() != 0) {
					
					mPhoneNumber.delete(mPhoneNumber.length() - 1, mPhoneNumber.length());
					mHideNumber.delete(mHideNumber.length() - 1, mHideNumber.length());

					if (mHideNumber.length() != 0) // 끝 숫자가 지워진 mHideNumber의 끝 숫자를 *에서 숫자로 바꾸기
						mHideNumber.setCharAt(mHideNumber.length() - 1, mPhoneNumber.charAt(mPhoneNumber.length() - 1));

					mNumberTextField.setText("010-" + mHideNumber);

					if (mHideNumber.length() != 0) // 이후 추가될 번호를 위해 다시 mHideNumber의 끝 숫자를 *로 바꾸기
						mHideNumber.setCharAt(mHideNumber.length() - 1, '*');
				}
			}
		};
		
		// 버튼에 액션리스너 달기
		for (JButton btn : mNumberBtnArr) {
			btn.addActionListener(BtnActionListener);
		}
		mDeleteBtn.addActionListener(BtnActionListener);
		mUndoBtn.addActionListener(BtnActionListener);
	
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

}
