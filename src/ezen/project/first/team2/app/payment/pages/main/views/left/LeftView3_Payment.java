package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView3_Payment extends View {

	private static final int PADDING = 20;

	private static final String ORG_PRICE_TITLE_LABEL_TEXT = "총 금액";
	private static final String DISCOUNT_TITLE_lABEL_TEXT = "회원 할인 금액";
	private static final String USED_POINTS_TITLE_lABEL_TEXT = "사용한 포인트";
	private static final String FINAL_PRICE_TITLE_LABEL_TEXT = "최종 금액";
	private static final String PREV_BTN_TEXT = "이전단계";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// title Label
	private static final Font TITLE_LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color TITLE_LABEL_FONT_COLOR = new Color(103, 121, 133);
	
	// label
	private static final Font LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 25);
	private static final Color LABEL_FONT_COLOR = new Color(103, 121, 133);
	
	// Prev button
	private static final Font PREV_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color PREV_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color PREV_BTN_COLOR = new Color(21, 150, 136);
	
	
	// 타이틀 라벨, 금액표시 라벨, 금액
	private JLabel mOrgPrice_title_label;
	private JLabel mOrgPrice_label;
	private String mOrgPrice;

	private JLabel mDiscount_title_label;
	private JLabel mDiscount_label;
	private String mDiscount;

	private JLabel mUsedPoints_title_label;
	private JLabel mUsedPoints_label;
	private String mUsedPoints;

	private JLabel mFinalPrice_title_label;
	private JLabel mFinalPrice_label;
	private String mFinalPrice;
	//

	private JButton mPrev_btn;

	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;
	
	private ProductDiscountsManager mProdDiscntMngr;
	private ProductPurchasing mProdPurchasing;

	public LeftView3_Payment() {
		super(MainPage.LEFT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {

		mOrgPrice_title_label = new JLabel(ORG_PRICE_TITLE_LABEL_TEXT);
		mOrgPrice_label = new JLabel();

		mDiscount_title_label = new JLabel(DISCOUNT_TITLE_lABEL_TEXT);
		mDiscount_label = new JLabel();

		mUsedPoints_title_label = new JLabel(USED_POINTS_TITLE_lABEL_TEXT);
		mUsedPoints_label = new JLabel();

		mFinalPrice_title_label = new JLabel(FINAL_PRICE_TITLE_LABEL_TEXT);
		mFinalPrice_label = new JLabel();

		mPrev_btn = new JButton(PREV_BTN_TEXT);
		
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();
		
		mProdDiscntMngr = ProductDiscountsManager.getInstance();
		
		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		
		// 디자인 관련 설정
		mOrgPrice_title_label.setFont(TITLE_LABEL_FONT);
		mOrgPrice_title_label.setForeground(TITLE_LABEL_FONT_COLOR);
		mOrgPrice_label.setFont(LABEL_FONT);
		mOrgPrice_label.setForeground(LABEL_FONT_COLOR);

		mDiscount_title_label.setFont(TITLE_LABEL_FONT);
		mDiscount_title_label.setForeground(TITLE_LABEL_FONT_COLOR);
		mDiscount_label.setFont(LABEL_FONT);
		mDiscount_label.setForeground(LABEL_FONT_COLOR);
		
		mUsedPoints_title_label.setFont(TITLE_LABEL_FONT);
		mUsedPoints_title_label.setForeground(TITLE_LABEL_FONT_COLOR);
		mUsedPoints_label.setFont(LABEL_FONT);
		mUsedPoints_label.setForeground(LABEL_FONT_COLOR);
		
		mFinalPrice_title_label.setFont(TITLE_LABEL_FONT);
		mFinalPrice_title_label.setForeground(TITLE_LABEL_FONT_COLOR);
		mFinalPrice_label.setFont(LABEL_FONT);
		mFinalPrice_label.setForeground(LABEL_FONT_COLOR);
		
		mPrev_btn.setFont(PREV_BTN_FONT);
		mPrev_btn.setBackground(PREV_BTN_COLOR);
		mPrev_btn.setForeground(PREV_BTN_FONT_COLOR);
	}

	@Override
	protected void onAddCtrls() {
		mGbc.anchor = GridBagConstraints.NORTH;
		mGbc.fill = GridBagConstraints.HORIZONTAL;
		mGbc.weighty = 0.01;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mOrgPrice_title_label, mGbc);
		
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mOrgPrice_label, mGbc);
		
		//
		
		mGbc.insets = new Insets(20, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 2;
		this.add(mDiscount_title_label, mGbc);
		
		mGbc.insets = new Insets(0, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 3;
		this.add(mDiscount_label, mGbc);

		//
		
		mGbc.insets = new Insets(20, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 4;
		this.add(mUsedPoints_title_label, mGbc);
		
		mGbc.insets = new Insets(0, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 5;
		this.add(mUsedPoints_label, mGbc);

		//
		
		mGbc.insets = new Insets(20, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 6;
		this.add(mFinalPrice_title_label, mGbc);
		
		mGbc.insets = new Insets(0, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 7;
		this.add(mFinalPrice_label, mGbc);

		mGbc.fill = GridBagConstraints.HORIZONTAL;
		mGbc.anchor = GridBagConstraints.LAST_LINE_END;
		mGbc.weighty = 0.5;
		mGbc.gridx = 0;
		mGbc.gridy = 8;
		this.add(mPrev_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mPrev_btn.addActionListener(e -> {
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
		
		// 금액 넣기
		try {
			mOrgPrice = UnitUtils.numToCurrencyStr(mProdPurchasing.getProdOrderItem().getOrgTotalPrice());

			// 회원 할인가만 표시
			if (mProdPurchasing.getProdOrderItem().getCustId() == 0)
				mDiscount = "0";
			else {
				var items = mProdPurchasing.getProdOrderItem().getProdOrderDetailItems();
				int dscntSum = 0;
				for (var i : items) {
					int discntId = i.getProdDiscntId();
					dscntSum += mProdDiscntMngr.findById(discntId).getAmount() * i.getQuantity();
				}
				mDiscount = UnitUtils.numToCurrencyStr(dscntSum);
			}

			mUsedPoints = UnitUtils.numToCurrencyStr(mProdPurchasing.getProdOrderItem().getUsedPoint());
			mFinalPrice = UnitUtils.numToCurrencyStr(mProdPurchasing.getProdOrderItem().getFinalTotalPrice());

			mOrgPrice_label.setText(mOrgPrice);
			mDiscount_label.setText(mDiscount);
			mUsedPoints_label.setText(mUsedPoints);
			mFinalPrice_label.setText(mFinalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
	}
}
