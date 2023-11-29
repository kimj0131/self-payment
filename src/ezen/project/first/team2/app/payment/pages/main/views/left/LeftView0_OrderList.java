package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView0_OrderList;

public class LeftView0_OrderList extends View {
	private static final int PADDING = 20;

	private static final String MSG_LABEL_TEXT = "<html>구매하실<br>상품을<br>스캔해주세요</html>";
	private static final String SELF_INPUT_BTN_TEXT = "<html>과일/채소<br>직접입력</html>";
	private static final String RAN_BARCORDE_BTN_TEXT = "바코드 찍기";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);

	// title
	private static final Font TITLE_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color TITLE_FONT_COLOR = new Color(103, 121, 133);

	// SelfInput button
	private static final Font SELF_INPUT_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color SELF_INPUT_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color SELF_INPUT_BTN_COLOR = new Color(21, 150, 136);
	
	// RanBarCode_btn
	private static final Font RAN_BARCODE_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color RAN_BARCODE_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color RAN_BARCODE_BTN_COLOR = new Color(21, 150, 136);

	
	JLabel mTitle_label;
	JButton mSelfInput_btn;
	JButton mRanBarCode_btn;
	
	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;
	
	private ProductManager mProdMngr;
	private ProductOrderDetailsManager mProdOrderDetailsMngr;
	
	private ProductPurchasing mProdPurchasing;

	public LeftView0_OrderList() {
		super(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		this.setBackground(BACKGROUND_COLOR);
		
		mTitle_label = new JLabel(MSG_LABEL_TEXT);
		mSelfInput_btn = new JButton(SELF_INPUT_BTN_TEXT);
		mRanBarCode_btn = new JButton(RAN_BARCORDE_BTN_TEXT);
		
		mSelfInput_btn.setFocusable(false);
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();
		
		mProdMngr = ProductManager.getInstance();
		mProdOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, 0));
		this.setLayout(new GridBagLayout());

		// 디자인 관련 설정
		mTitle_label.setFont(TITLE_FONT);
		mTitle_label.setForeground(TITLE_FONT_COLOR);
		
		mSelfInput_btn.setFont(SELF_INPUT_BTN_FONT);
		mSelfInput_btn.setBackground(SELF_INPUT_BTN_COLOR);
		mSelfInput_btn.setForeground(SELF_INPUT_BTN_FONT_COLOR);
		
		mRanBarCode_btn.setFont(RAN_BARCODE_BTN_FONT);
		mRanBarCode_btn.setBackground(RAN_BARCODE_BTN_COLOR);
		mRanBarCode_btn.setForeground(RAN_BARCODE_BTN_FONT_COLOR);
		mRanBarCode_btn.setFocusable(false);
	}

	@Override
	protected void onAddCtrls() {
		
		mGbc.anchor = GridBagConstraints.NORTH;
		mGbc.weighty = 0.05;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(this.mTitle_label, mGbc);
		
		mGbc.fill = GridBagConstraints.HORIZONTAL;
		mGbc.weighty = 0.8;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(this.mSelfInput_btn, mGbc);
		
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weighty = 0.05;
		mGbc.gridx = 0;
		mGbc.gridy = 2;
		this.add(this.mRanBarCode_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mSelfInput_btn.addActionListener(e -> {
			try {
				MainPage mainPage = (MainPage) this.getPage();
				mainPage.showPopupViewByNum(MainPage.POPUP_VIEW_FRUITS_SELECTOR_NUM);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		mRanBarCode_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				RightView0_OrderList rv0 = (RightView0_OrderList) mainView
						.getViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				// 테이블 초기화 작업
				rv0.get_mTableModel().setNumRows(0);

				// 과일 채소를 제외한 랜덤한 상품 하나 생성
				boolean isNotFv = true; 
				ProductItem productItem = null;
			
				while (isNotFv) {
					int randomId = (int) (Math.random() * 35);
					productItem = mProdMngr.findById(randomId);
					String prodCode = productItem.getProdCode().toString();
					if (!(prodCode.substring(0, 1).equals("F") || prodCode.substring(0, 1).equals("V"))) {
						isNotFv = false;
					}
				}
				
				System.out.println("추가한 상품\n " + productItem);
				
				// 생성된 상품을 상세 구매내역에 넣기
				mProdPurchasing._2_addProduct(productItem.getId(), 1);

				// 테이블에 추가
				mProdOrderDetailsMngr.iterate((item, idx) -> {
					try {
						if (item.getProdOrderId() == mProdPurchasing.getProdOrderId()) {

							String prodCode = item.getProdItem().getProdCodeStr();
							String prodName = mProdMngr.findById(item.getProdId()).getName();
							String prodQty = String.valueOf(item.getQuantity());
							int prodPrice = mProdMngr.findById(item.getProdId()).getPrice() * item.getQuantity();

							String[] row = new String[] { prodCode, prodName, prodQty,
									UnitUtils.numToCurrencyStr(prodPrice) };

							rv0.get_mTableModel().addRow(row);

						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					return true;
				});

				// 실시간으로 합계 구해서 표시
				ProductOrderItem prodOrderItem = mProdPurchasing.getProdOrderItem();
				rv0.get_mSum_tf().setText(UnitUtils.numToCurrencyStr(prodOrderItem.getOrgTotalPrice()) + "원");

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {}
}
