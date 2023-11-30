package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView1_CheckMember;

public class PopUpView1_VerifiedMemberInfo extends PopupView {

	private static final int PADDING = 20;
	private static final Dimension VIEW_SIZE = new Dimension(600, 500);

	private static final String MSG_LABEL_TEXT_FORMAT = 
			"<html><center>"
			+ "%s 회원님<br>"
			+ "확인 감사합니다"
			+ "</center></html>";
	private static final String CHECK_BTN_TEXT = "확인";
	private static final String CANCEL_BTN_TEXT = "재입력";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// Msg Label
	private static final Font MASSAGE_LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color MASSAGE_LABEL_FONT_COLOR = new Color(103, 121, 133);
	private static final Color MASSAGE_LABEL_COLOR = new Color(255, 255, 255);
	
	// Check Button & Cancel Button
	private static final Font BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color BTN_COLOR = new Color(3, 181, 208);

	private String mMemName;

	private JLabel mMsg_label;
	private JButton mCheck_btn;
	private JButton mCancel_btn;
	
	// 그리드백 레이아웃을 사용하기 위한 constraint
	GridBagConstraints mGbc;

	private CustomerManager mCustMngr;
	private ProductPurchasing mProdPurchasing;

	public PopUpView1_VerifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_VERIFIED_MEMBER_INFO_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		super.onInit();

		mMsg_label = new JLabel();
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		mCancel_btn = new JButton(CANCEL_BTN_TEXT);

		mMsg_label.setBackground(Color.WHITE);
		
		mCustMngr = CustomerManager.getInstance();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();
	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		
		// 디자인 관련 설정
		mMsg_label.setOpaque(true);
		mMsg_label.setBackground(MASSAGE_LABEL_COLOR);
		mMsg_label.setForeground(MASSAGE_LABEL_FONT_COLOR);
		mMsg_label.setFont(MASSAGE_LABEL_FONT);
		mMsg_label.setHorizontalAlignment(JLabel.CENTER);
		
		mCheck_btn.setBackground(BTN_COLOR);
		mCheck_btn.setForeground(BTN_FONT_COLOR);
		mCheck_btn.setFont(BTN_FONT);
		
		mCancel_btn.setBackground(BTN_COLOR);
		mCancel_btn.setForeground(BTN_FONT_COLOR);
		mCancel_btn.setFont(BTN_FONT);
	}

	@Override
	protected void onAddCtrls() {
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 1;
		mGbc.weighty = 1;
		
		mGbc.gridwidth = 2;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mMsg_label, mGbc);
		
		mGbc.insets = new Insets(PADDING, 0, 0, PADDING / 2);
		
		mGbc.gridwidth = 1;
		
		mGbc.weighty = 0.06;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mCheck_btn, mGbc);
		
		mGbc.insets = new Insets(PADDING, PADDING / 2, 0, 0);
		
		mGbc.gridx = 1;
		mGbc.gridy = 1;
		this.add(mCancel_btn, mGbc);
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
				rightView1.resetMember();

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
		mMsg_label.setText(String.format(MSG_LABEL_TEXT_FORMAT, mMemName));
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
	}

}
