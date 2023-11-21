package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerInfo;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView1_CheckMember extends View {
	
	private static final int PADDING = 10;
	
	private static final String CHECK_BUTTON_TEXT = "확인";
	private static final String PASS_BUTTON_TEXT = "적립안함";
	private static final String DELETE_BUTTON_TEXT = "전체삭제";
	private static final String UNDO_BUTTON_TEXT = "삭제";
	private static final String PHONE_ID_NUMEBER_TEXT = "010-";
	
	JButton mCheckButton;
	JButton mPassButton;
	
	// 숫자패드
	JTextField mNumberTextField;
	StringBuilder mPhoneNumber;
	StringBuilder mHidedPhoneNumber;
	JButton[] mNumberBtnArr;
	JButton mDeleteBtn;
	JButton mUndoBtn;
	JPanel mNumberPanel;
	//
	
	CustomerInfo mCustomerInfo;

	public RightView1_CheckMember() {
		super(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);

		// 버튼 초기화
		mCheckButton = new JButton(CHECK_BUTTON_TEXT);
		mPassButton = new JButton(PASS_BUTTON_TEXT);
		
		// 숫자패드 초기화
		mPhoneNumber = new StringBuilder(PHONE_ID_NUMEBER_TEXT);
		mHidedPhoneNumber = new StringBuilder(PHONE_ID_NUMEBER_TEXT);
		mNumberTextField = new JTextField(PHONE_ID_NUMEBER_TEXT);
		mNumberBtnArr = new JButton[10];
		mDeleteBtn = new JButton(DELETE_BUTTON_TEXT);
		mUndoBtn = new JButton(UNDO_BUTTON_TEXT);
		mNumberPanel = new JPanel();
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
		// 숫자패널에 숫자, delete, undo 버튼 달기
		for (JButton btn : mNumberBtnArr) {
			if (btn.getName() != "0")
				mNumberPanel.add(btn);
		}
		mNumberPanel.add(mDeleteBtn);
		mNumberPanel.add(mNumberBtnArr[0]);
		mNumberPanel.add(mUndoBtn);

		this.add(mNumberTextField);
		this.add(mCheckButton);
		this.add(mNumberPanel);
		this.add(mPassButton);
	}

	@Override
	protected void onAddEventListeners() {
		mCheckButton.addActionListener(e -> {
//			for (var idx : CustomerInfo.DummyDataIndex.values()) {
//				mCustomerInfo = CustomerInfo.getDummyData(idx);
//				if (mCustomerInfo.getPhoneNumber().equals(mPhoneNumber.toString()))
//					break;
//				mCustomerInfo = null;
//			}
			
			mCustomerInfo = null;				
			for (var ci : CustomerInfo.getPredefinedData()) {
				if (ci.getPhoneNumber().equals(mPhoneNumber.toString())) {
					mCustomerInfo = ci;
					
					break;
				}				
			}
			

			if (mCustomerInfo != null) {
				// 유효한
				UiUtils.showMsgBox(mCustomerInfo.getName() + " 회원님", MainPage.TITLE);
				
				try {
					MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
					mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_POINT_INFO_NUM);
					mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			} else {
				// 무효한
				UiUtils.showMsgBox("없는 회원입니다", MainPage.TITLE);
			}
			
		});

		mPassButton.addActionListener(e -> {
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
					// 폰번호를 다 입력했으면 버튼을 눌러도 번호가 추가되지 않는다
					if (mPhoneNumber.length() < 13) {
						
						mPhoneNumber.append(i);
						mHidedPhoneNumber.append(i);

						// 010-숫자4자리가 될때 하이픈 추가
						// OR 하이픈 다음에 숫자가 추가돠면 하이픈 앞 숫자 *로 바꾸기
						if (mHidedPhoneNumber.length() == 8) {
							mPhoneNumber.append('-');
							mHidedPhoneNumber.append('-');
						} else if (mHidedPhoneNumber.length() == 10) {
							mHidedPhoneNumber.setCharAt(7, '*');
						}

						mNumberTextField.setText(mHidedPhoneNumber.toString());

						// 번호의 마지막 숫자를 *로 바꿈
						if (mHidedPhoneNumber.charAt(mHidedPhoneNumber.length() - 1) != '-')
							mHidedPhoneNumber.setCharAt(mHidedPhoneNumber.length() - 1, '*');
						break;
					}
				}
			}

			if (e.getSource() == mDeleteBtn) {
				mPhoneNumber.delete(4, mPhoneNumber.length());
				mHidedPhoneNumber.delete(4, mHidedPhoneNumber.length());
				mNumberTextField.setText(mHidedPhoneNumber.toString());
			}

			if (e.getSource() == mUndoBtn) {
				
				if (mPhoneNumber.length() >= 5) {
					// 하이픈 삭제 - 하이픈 다음에 숫자 입력이 없었을 경우
					if (mHidedPhoneNumber.charAt(mHidedPhoneNumber.length() - 1) == '-' && mHidedPhoneNumber.length() > 4) {
						mHidedPhoneNumber.deleteCharAt(mHidedPhoneNumber.length() - 1);
						mPhoneNumber.deleteCharAt(mPhoneNumber.length() - 1);
					}
					
					// 번호삭제
					mPhoneNumber.delete(mPhoneNumber.length() - 1, mPhoneNumber.length());
					mHidedPhoneNumber.delete(mHidedPhoneNumber.length() - 1, mHidedPhoneNumber.length());
					
					// 하이픈 삭제 - 하이픈 다음에 숫자 입력이 있었을 경우
					if (mHidedPhoneNumber.charAt(mHidedPhoneNumber.length() - 1) == '-' && mHidedPhoneNumber.length() > 4) {
						mHidedPhoneNumber.deleteCharAt(mHidedPhoneNumber.length() - 1);
						mPhoneNumber.deleteCharAt(mPhoneNumber.length() - 1);
					}

					// 끝 숫자가 지워진 mHidedPhoneNumber의 끝 숫자를 *에서 숫자로 바꾸기
					mHidedPhoneNumber.setCharAt(mHidedPhoneNumber.length() - 1, 
							mPhoneNumber.charAt(mPhoneNumber.length() - 1));

					mNumberTextField.setText(mHidedPhoneNumber.toString());

					// 이후 추가될 번호를 위해 다시 mHidedPhoneNumber의 끝 숫자를 *로 바꾸기
					if (mHidedPhoneNumber.length() > 4)
						mHidedPhoneNumber.setCharAt(mHidedPhoneNumber.length() - 1, '*');
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

	public CustomerInfo getCustomerInfo() {
		return mCustomerInfo;
	}
}
