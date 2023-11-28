package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView3_Payment extends View {

	private static final int PADDING = 10;

	private static final String ORG_PRICE_TITLE_LABEL_TEXT = "총 금액";
	private static final String DISCOUNT_TITLE_lABEL_TEXT = "회원 할인 금액";
	private static final String USED_POINTS_TITLE_lABEL_TEXT = "사용한 포인트";
	private static final String FINAL_PRICE_TITLE_LABEL_TEXT = "최종 금액";

	private static final String PREV_BTN_TEXT = "이전단계";

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

	private ProductPurchasing mProdPurchasing;

	public LeftView3_Payment() {
		super(MainPage.LEFT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;

		mOrgPrice_title_label = new JLabel(ORG_PRICE_TITLE_LABEL_TEXT);
		mOrgPrice_label = new JLabel();

		mDiscount_title_label = new JLabel(DISCOUNT_TITLE_lABEL_TEXT);
		mDiscount_label = new JLabel();

		mUsedPoints_title_label = new JLabel(USED_POINTS_TITLE_lABEL_TEXT);
		mUsedPoints_label = new JLabel();

		mFinalPrice_title_label = new JLabel(FINAL_PRICE_TITLE_LABEL_TEXT);
		mFinalPrice_label = new JLabel();
		//

		mPrev_btn = new JButton(PREV_BTN_TEXT);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {

		this.add(mOrgPrice_title_label);
		this.add(mOrgPrice_label);

		this.add(mDiscount_title_label);
		this.add(mDiscount_label);

		this.add(mUsedPoints_title_label);
		this.add(mUsedPoints_label);

		this.add(mFinalPrice_title_label);
		this.add(mFinalPrice_label);

		this.add(mPrev_btn);
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
			mDiscount = UnitUtils.numToCurrencyStr(mProdPurchasing.getProdOrderItem().getOrgTotalPrice()
					- mProdPurchasing.getProdOrderItem().getUsedPoint());

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
