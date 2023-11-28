package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView1_CheckMember;

public class PopUpView1_VerifiedMemberInfo extends PopupView {

	// private static final int PADDING = 10;
	private static final Dimension VIEW_SIZE = new Dimension(500, 300);

	private static final String MSG_TA_TEXT_FORMAT = "PopUpView_VerifiedMemInfo\n%s 회원님\n확인 감사합니다";
	private static final String CHECK_BTN_TEXT = "확인";
	private static final String CANCEL_BTN_TEXT = "재입력";

	String mMemName;

	JTextArea mMsg_ta;
	JButton mCheck_btn;
	JButton mCancel_btn;

	private CustomerManager mCustMngr;

	private ProductPurchasing mProdPurchasing;

	public PopUpView1_VerifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_VERIFIED_MEMBER_INFO_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		super.onInit();

		mMsg_ta = new JTextArea();
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		mCancel_btn = new JButton(CANCEL_BTN_TEXT);

		mMsg_ta.setBackground(Color.WHITE);

		mCustMngr = CustomerManager.getInstance();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
	}

	@Override
	protected void onAddCtrls() {

		this.add(mMsg_ta);

		this.add(mCheck_btn);
		this.add(mCancel_btn);
	}

	@Override
	protected void onAddEventListeners() {

		mCheck_btn.addActionListener(e -> {
			try {
				performClose();
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_POINT_INFO_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mCancel_btn.addActionListener(e -> {
			try {
				performClose();

				// rightView1 번호 리셋 시키기
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				RightView1_CheckMember rightView1 = (RightView1_CheckMember) mainView
						.getViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
				rightView1.resetPhoneNums();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {

		try {
			var prodOrderItem = mProdPurchasing.getProdOrderItem();
			// RightView0에서 발급된 구매내역id로 구매내역 찾고 구매내역에 있는 고객id로 고객 이름 가져오기
			mMemName = mCustMngr.findById(prodOrderItem.getCustId()).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 회원이름 설정
		mMsg_ta.setText(String.format(MSG_TA_TEXT_FORMAT, mMemName));
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
	}

}
