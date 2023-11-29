////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_140700] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.payment.pages.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.common.modules.base.ListActionAdapter;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.popup.PopUpView0_FruitsSelector;
import ezen.project.first.team2.app.payment.pages.main.views.popup.PopUpView1_VerifiedMemberInfo;
import ezen.project.first.team2.app.payment.pages.main.views.popup.PopUpView2_UnverifiedMemberInfo;
import ezen.project.first.team2.app.payment.pages.main.views.popup.PopUpView3_UsePoints;

public class MainPage extends Page {
	// -------------------------------------------------------------------------

	// 페이지 정보 상수 정의

	public static final String TITLE = "결제 프로그램";
	public static final Dimension SIZE = new Dimension(1280, 720);
	//public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	// 뷰 번호 정의
	public static final int VIEW_NUM_MAIN = 0;

	// Left
	public static final int LEFT_VIEW_ORDER_LIST_NUM = 100;
	public static final int LEFT_VIEW_CHECK_MEMBER_NUM = 101;
	public static final int LEFT_VIEW_POINT_INFO_NUM = 102;
	public static final int LEFT_VIEW_PAYMENT_NUM = 103;

	// Right
	public static final int RIGHT_VIEW_ORDER_LIST_NUM = 200;
	public static final int RIGHT_VIEW_CHECK_MEMBER_NUM = 201;
	public static final int RIGHT_VIEW_POINT_INFO_NUM = 202;
	public static final int RIGHT_VIEW_PAYMENT_NUM = 203;

	// PopUp view
	public static final int POPUP_VIEW_FRUITS_SELECTOR_NUM = 300;
	public static final int POPUP_VIEW_VERIFIED_MEMBER_INFO_NUM = 301;
	public static final int POPUP_VIEW_UNVERIFIED_MEMBER_INFO_NUM = 302;
	public static final int POPUP_VIEW_USE_POINTS_NUM = 303;

	// ProductPurchasing
	public ProductPurchasing mProdPurchasing;

	// 생성자
	public MainPage() {
		super(Main.PAGE_NUM_MAIN, TITLE, SIZE,
				OPTION_CENTER_IN_SCREEN |
						OPTION_VISIBLE * 0 |
						OPTION_BORDERLESS * 0 |
						OPTION_FULL_SCREEN * 0 |
						OPTION_FIXED_SIZE * 0);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
		mProdPurchasing = new ProductPurchasing();
		setTestData();
	}

	// Test를 위한 더미 데이터 설정 작업
	private void setTestData() {

		var custMngr = CustomerManager.getInstance();

		var prodMngr = ProductManager.getInstance();
		var prodStocksMngr = ProductStocksManager.getInstance();
		var prodDiscntsMngr = ProductDiscountsManager.getInstance();

		try {

			// 고객 임시 데이터 생성
			CustomerItem[] customerDummyData = CustomerItem.getPredefinedData();

			for (CustomerItem ci : customerDummyData)
				custMngr.add(ci);

			prodMngr.setActionListener(new ListActionAdapter<ProductItem>() {
				@Override
				public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
					try {
						// 상품 재고 관리자에 추가
						prodStocksMngr.add(new ProductStockItem(item.getId()));
						// 상품 할인 관리자에 추가
						prodDiscntsMngr.add(new ProductDiscountItem(item.getId()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			// 상품 임시 데이터 생성
			ProductItem[] productDummyData = ProductItem.getPredefinedProductData();

			for (ProductItem pi : productDummyData)
				prodMngr.add(pi);

			// 상품 재고 생성
			prodMngr.iterate((item, idx) -> {
				try {
					prodStocksMngr.updateQuantityByProdId(item.getId(), 10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			});

			// 상품 할인율 설정
			prodMngr.iterate((item, idx) -> {
				try {
					prodDiscntsMngr.setAmountByProdId(item.getId(), 100);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return true;
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 테스트용 데이터 확인을 위한 콘솔 출력
		// try {
		//
		// System.out.println("고객 리스트");
		// custMngr.iterate((item, idx) -> {
		// System.out.println(" " + item);
		//
		// return true;
		// });
		//
		// System.out.println("------------------------------");
		//
		// System.out.println("상품 리스트");
		// prodMngr.iterate((item, idx) -> {
		// System.out.println(" " + item);
		//
		// return true;
		// });
		//
		// System.out.println("------------------------------");
		//
		// System.out.println("상품 재고 리스트");
		// prodStocksMngr.iterate((item, idx) -> {
		// try {
		// System.out.println(item + " => " + item.getProdItem().getName());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return true;
		// });
		//
		// System.out.println("------------------------------");
		//
		// System.out.println("상품 할인 리스트");
		// prodDiscntsMngr.iterate((item, idx) -> {
		// System.out.println(" " + item);
		// return true;
		// });
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	// 뷰 추가
	@Override
	protected void onAddViews() {
		try {
			this.addView(new MainView());

			// PopUp View
			this.addView(new PopUpView0_FruitsSelector());
			this.addView(new PopUpView1_VerifiedMemberInfo());
			this.addView(new PopUpView2_UnverifiedMemberInfo());
			this.addView(new PopUpView3_UsePoints());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
	}

	// 페이지가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[MainPage.onShow()]");

		try {
			this.setSelectedViewByNum(MainPage.VIEW_NUM_MAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 페이지가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[MainPage.onHide()]");
	}
}
