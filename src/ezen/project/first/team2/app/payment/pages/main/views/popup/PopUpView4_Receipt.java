package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView0_OrderList;

public class PopUpView4_Receipt extends PopupView {

	private static final int PADDING = 10;
	private static final Dimension VIEW_SIZE = new Dimension(355, 600);

	private static final String CHECK_BTN_TEXT = "확인";
	private static final String RECEIPT_CONTENTS_TEXT_FORMAT_1 = "\t    신용카드 매출전표\n\n"
			+ "[매장명] EZEN Mart\n"
			+ "[사업자번호] 111-22-33333\n"
			+ "[주소] 경기도 구리시 건원대로 44 태영빌딩 4층\n"
			+ "[대표자] 권혁준\t     [Tel] 031)555-4449\n"
			+ "[매출일] %s\n"
			+ "===================================\n"
			+ "상품명\t단가\t할인   수량      금액\n"
			+ "---------------------------------------------------------------\n";

	private static final String RECEIPT_CONTENTS_TEXT_FORMAT_2 = "%s\t%s\t %s        %d    %7s\n";
	private static final String RECEIPT_CONTENTS_TEXT_FORMAT_3 = "---------------------------------------------------------------\n"
			+ "합계 금액\t\t%s\n\n"
			+ "회원할인\t\t%s\n"
			+ "포인트사용\t\t%s\n\n"
			+ "최종금액\t\t%s\n"
			+ "---------------------------------------------------------------\n";

	private static final String RECEIPT_CONTENTS_TEXT_FORMAT_4 = "부과세과세물품가액\t%s\n"
			+ "부        가        세\t%s\n"
			+ "---------------------------------------------------------------\n"
			+ "신용카드\t\t%s\n"
			+ "---------------------------------------------------------------\n"
			+ "\t    ***신용승인정보***\n\n"
			+ "[카드종류] 신한카드\t      [할부개월] 일시불\n"
			+ "[카드번호] 1234-5678-xxxx-xxxx\n"
			+ "[유효기간] xx/xx\n"
			+ "[승인금액] %s\n"
			+ "[승인번호] 12345678\n"
			+ "[승인일시] %s";;

	// 영수증 폰트 관련
	private static final Font RECEIPT_TEXT_FONT = new Font("맑은 고딕", Font.PLAIN, 13);
	private static final Color RECEIPT_TEXT_FONT_COLOR = new Color(50, 70, 80);

	// Check Button & Cancel Button
	private static final Font BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color BTN_COLOR = new Color(3, 181, 208);

	private JTextArea mReceipt_ta;
	private JScrollPane mScrolledReceipt;
	private JButton mCheck_btn;

	// GridBagLayout 조절하기 위한 클래스
	private GridBagConstraints mGbc;

	private ProductDiscountsManager mProdDiscntMngr;
	private ProductPurchasing mProdPurchasing;

	public PopUpView4_Receipt() {
		super(MainPage.POPUP_VIEW_RECEIPT_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		super.onInit();

		mReceipt_ta = new JTextArea();
		mScrolledReceipt = new JScrollPane(mReceipt_ta);
		mCheck_btn = new JButton(CHECK_BTN_TEXT);

		mProdDiscntMngr = ProductDiscountsManager.getInstance();

		// GridBagLayout 조절하기 위한 클래스
		mGbc = new GridBagConstraints();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);

		mReceipt_ta.setDragEnabled(false);
		mReceipt_ta.setEditable(false);

		// 디자인 관련 설정
		mReceipt_ta.setFont(RECEIPT_TEXT_FONT);
		mReceipt_ta.setForeground(RECEIPT_TEXT_FONT_COLOR);

		mCheck_btn.setBackground(BTN_COLOR);
		mCheck_btn.setForeground(BTN_FONT_COLOR);
		mCheck_btn.setFont(BTN_FONT);
	}

	@Override
	protected void onAddCtrls() {

		mGbc.fill = GridBagConstraints.BOTH;

		mGbc.weightx = 1;
		mGbc.weighty = 1;
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mScrolledReceipt, mGbc);

		mGbc.insets = new Insets(10, 0, 0, 0);

		mGbc.weighty = 0.03;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mCheck_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mCheck_btn.addActionListener(e -> {
			RightView0_OrderList.RECEIPT_ISSUANCE = false;

			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);

				RightView0_OrderList rv0 = (RightView0_OrderList) mainView
						.getViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				// 결제 완료가 됐다면 RightView0_OrdetList에 테이블 초기화
				rv0.get_mTableModel().setNumRows(0);
				// 결제 완료가 됐다면 RightView0_OrdetList에 총금액 텍스트필드 초기화
				rv0.get_mSum_tf().setText("");
				// 결제 완료가 됐다면 RightView0_OrdetList에 결제하기 버튼 비활성화
				rv0.deactivateButton();

				mProdPurchasing._6_commit();

				this.performClose();

				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				getStatusManager().setSelectedPageByNum(Main.PAGE_NUM_STANBY);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
		String orderDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
				.format(mProdPurchasing.getProdOrderItem().getOrderDateTime());

		mReceipt_ta.setText(String.format(RECEIPT_CONTENTS_TEXT_FORMAT_1, orderDateTime));

		var items = mProdPurchasing.getProdOrderItem().getProdOrderDetailItems();
		for (var item : items) {
			String name = item.getProdItem().getName();
			int price = item.getProdItem().getPrice();
			int discnt = item.getProdDiscntItem().getAmount() * item.getQuantity();
			int qyt = item.getQuantity();
			int finalPrice = price * item.getQuantity() - discnt;

			mReceipt_ta.setText(mReceipt_ta.getText()
					+ String.format(RECEIPT_CONTENTS_TEXT_FORMAT_2, name, UnitUtils.numToCurrencyStr(price),
							UnitUtils.numToCurrencyStr(discnt), qyt, UnitUtils.numToCurrencyStr(finalPrice)));
		}

		int orgPrice = mProdPurchasing.getProdOrderItem().getOrgTotalPrice();

		// 회원 할인가만 표시
		int memberDiscnt;
		if (mProdPurchasing.getProdOrderItem().getCustId() == 0)
			memberDiscnt = 0;
		else {
			var poditems = mProdPurchasing.getProdOrderItem().getProdOrderDetailItems();
			int dscntSum = 0;
			for (var i : poditems) {
				int discntId = i.getProdDiscntId();
				dscntSum += mProdDiscntMngr.findById(discntId).getAmount() * i.getQuantity();
			}
			memberDiscnt = dscntSum;
		}

		int usedPoints = mProdPurchasing.getProdOrderItem().getUsedPoint();
		int finalPrice = mProdPurchasing.getProdOrderItem().getFinalTotalPrice();
		int tax = (int) (finalPrice / 10);
		int freeTaxPrice = finalPrice - tax;

		mReceipt_ta.setText(mReceipt_ta.getText() + String.format(RECEIPT_CONTENTS_TEXT_FORMAT_3,
				UnitUtils.numToCurrencyStr(orgPrice), UnitUtils.numToCurrencyStr(memberDiscnt),
				UnitUtils.numToCurrencyStr(usedPoints), UnitUtils.numToCurrencyStr(finalPrice)));

		// 결제 금액이 0원이 아니라면 결제 정보까지 출력
		if (finalPrice != 0) {
			mReceipt_ta.setText(mReceipt_ta.getText() + String.format(RECEIPT_CONTENTS_TEXT_FORMAT_4,
					UnitUtils.numToCurrencyStr(freeTaxPrice), UnitUtils.numToCurrencyStr(tax),
					UnitUtils.numToCurrencyStr(finalPrice), UnitUtils.numToCurrencyStr(finalPrice), orderDateTime));
		}
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
	}
}
