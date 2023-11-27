package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView1_CheckMember extends View {

	private static final int PADDING = 10;

	private static final String CHECK_BTN_TEXT = "확인";
	private static final String PASS_BTN_TEXT = "적립안함";
	private static final String DEL_BTN_TEXT = "전체삭제";
	private static final String UNDO_BTN_TEXT = "삭제";
	private static final String PHONE_ID_NUMEBER_TEXT = "010-";
	
	private JButton mCheck_btn;
	private JButton mPass_btn;

	// 숫자패드
	private StringBuilder mPhoneNum; // 010-0000-0000
	private StringBuilder mHidedPhoneNum; // 010 -****-***0
	private JTextField mNums_tf; // 누른 번호가 표시되는 곳
	private JPanel mNum_panel; // 숫자버튼을 담을 패널
	private JButton[] mNum_btn_arr; // "숫자" 버튼을 모아두는 배열
	private JButton mDel_btn; // 전체삭제 버튼
	private JButton mUndo_btn; // 한칸지우기 버튼
	//

	private CustomerManagerMem mCustMngr;
	private ProductPurchasing mProdPurchasing;

	public RightView1_CheckMember() {
		super(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);

		// 버튼 초기화
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		mPass_btn = new JButton(PASS_BTN_TEXT);

		// 숫자패드 초기화
		mPhoneNum = new StringBuilder();
		mHidedPhoneNum = new StringBuilder();
		
		mNums_tf = new JTextField();
		mNums_tf.setEditable(false);
		
		// 숫자 버튼 만들기
		mNum_btn_arr = new JButton[10];
		for (int i = 0; i < mNum_btn_arr.length; ++i) {
			mNum_btn_arr[i] = new JButton(String.valueOf(i));
		}
		
		mDel_btn = new JButton(DEL_BTN_TEXT);
		mUndo_btn = new JButton(UNDO_BTN_TEXT);
		mNum_panel = new JPanel();
		
		// 매니저 인스턴스 가져오기
		mCustMngr = CustomerManagerMem.getInstance();
		
		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout(2, 2));

		mNum_panel.setLayout(new GridLayout(4, 3));
	}

	@Override
	protected void onAddCtrls() {

		// 숫자패널에 숫자, delete, undo 버튼 달기
		for (JButton btn : mNum_btn_arr) {
			if (btn.getName() != "0")
				mNum_panel.add(btn);
		}
		mNum_panel.add(mDel_btn);
		mNum_panel.add(mNum_btn_arr[0]);
		mNum_panel.add(mUndo_btn);

		this.add(mNums_tf);
		this.add(mCheck_btn);
		this.add(mNum_panel);
		this.add(mPass_btn);
	}

	@Override
	protected void onAddEventListeners() {
		mCheck_btn.addActionListener(e -> {

			try {
				MainPage mainPage = (MainPage) this.getPage();

				// 입력한 번호와 일치하는 고객아이템 가져오기
				CustomerItem customerItem = mCustMngr.findByPhoneNumber(mPhoneNum.toString());

				// 확인된 회원
				if (customerItem != null) {

					// 구매내역에 고객 아이디 설정
					mProdPurchasing._3_applyCustomerPoint(customerItem.getId());

					mainPage.showPopupViewByNum(MainPage.POPUP_VIEW_VERIFIED_MEMBER_INFO_NUM);

				} else {
					// 없는 회원
					mainPage.showPopupViewByNum(MainPage.POPUP_VIEW_UNVERIFIED_MEMBER_INFO_NUM);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mPass_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_PAYMENT_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_PAYMENT_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// 버튼에 액션리스너 달기
		for (JButton btn : mNum_btn_arr) {
			btn.addActionListener(e -> {
				for (int i = 0; i < mNum_btn_arr.length; ++i) {

					if (e.getSource() == mNum_btn_arr[i]) {
						// 폰번호를 다 입력했으면 버튼을 눌러도 번호가 추가되지 않는다
						if (mPhoneNum.length() < 13) {

							mPhoneNum.append(i);
							mHidedPhoneNum.append(i);

							// 010-숫자4자리가 될때 하이픈 추가
							// OR 하이픈 다음에 숫자가 추가돠면 하이픈 앞 숫자 *로 바꾸기
							if (mHidedPhoneNum.length() == 8) {
								mPhoneNum.append('-');
								mHidedPhoneNum.append('-');
							} else if (mHidedPhoneNum.length() == 10) {
								mHidedPhoneNum.setCharAt(7, '*');
							}

//							mNums_tf.setText(mHidedPhoneNumber.toString());
							mNums_tf.setText(mPhoneNum.toString());

							// 번호의 마지막 숫자를 *로 바꿈
							if (mHidedPhoneNum.charAt(mHidedPhoneNum.length() - 1) != '-')
								mHidedPhoneNum.setCharAt(mHidedPhoneNum.length() - 1, '*');
							break;
						}
					}
				}
			});
		}
		
		mDel_btn.addActionListener(e -> {
			mPhoneNum.delete(4, mPhoneNum.length());
			mHidedPhoneNum.delete(4, mHidedPhoneNum.length());
//			mNums_tf.setText(mHidedPhoneNumber.toString());
			mNums_tf.setText(mPhoneNum.toString());
		});
		
		mUndo_btn.addActionListener(e -> {
			if (mPhoneNum.length() >= 5) {
				// 하이픈 삭제 - 하이픈 다음에 숫자 입력이 없었을 경우
				if (mHidedPhoneNum.charAt(mHidedPhoneNum.length() - 1) == '-' && mHidedPhoneNum.length() > 4) {
					mHidedPhoneNum.deleteCharAt(mHidedPhoneNum.length() - 1);
					mPhoneNum.deleteCharAt(mPhoneNum.length() - 1);
				}

				// 번호삭제
				mPhoneNum.delete(mPhoneNum.length() - 1, mPhoneNum.length());
				mHidedPhoneNum.delete(mHidedPhoneNum.length() - 1, mHidedPhoneNum.length());

				// 하이픈 삭제 - 하이픈 다음에 숫자 입력이 있었을 경우
				if (mHidedPhoneNum.charAt(mHidedPhoneNum.length() - 1) == '-' && mHidedPhoneNum.length() > 4) {
					mHidedPhoneNum.deleteCharAt(mHidedPhoneNum.length() - 1);
					mPhoneNum.deleteCharAt(mPhoneNum.length() - 1);
				}

				// 끝 숫자가 지워진 mHidedPhoneNumber의 끝 숫자를 *에서 숫자로 바꾸기
				mHidedPhoneNum.setCharAt(mHidedPhoneNum.length() - 1, mPhoneNum.charAt(mPhoneNum.length() - 1));

//				mNums_tf.setText(mHidedPhoneNumber.toString());
				mNums_tf.setText(mPhoneNum.toString());

				// 이후 추가될 번호를 위해 다시 mHidedPhoneNumber의 끝 숫자를 *로 바꾸기
				if (mHidedPhoneNum.length() > 4)
					mHidedPhoneNum.setCharAt(mHidedPhoneNum.length() - 1, '*');
			}
		});
		
	}

	@Override
	protected void onShow(boolean firstTime) {
		resetPhoneNums();
	}

	@Override
	protected void onHide() {}

	
	public void resetPhoneNums() {
		// 유효한 회원 번호 입력했다가 재입력 버튼을 누른경우 비회원으로 초기화
		mProdPurchasing.getProdOrderItem().setCustId(0);
		
		mNums_tf.setText("010-");
		
		mPhoneNum.delete(0, mPhoneNum.length());
		mHidedPhoneNum.delete(0, mHidedPhoneNum.length());
		
		mPhoneNum.append(PHONE_ID_NUMEBER_TEXT);
		mHidedPhoneNum.append(PHONE_ID_NUMEBER_TEXT);
	}
}
