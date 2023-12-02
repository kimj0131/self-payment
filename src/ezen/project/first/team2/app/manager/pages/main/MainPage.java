////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_124500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.manager.pages.main;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.common.modules.base.ListActionAdapter;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.views.MainView;

public class MainPage extends Page {
	// -------------------------------------------------------------------------

	// 페이지 정보 상수 정의

	public static final String TITLE = "관리 프로그램";
	public static final Dimension SIZE = new Dimension(1280, 720);

	public static final int VIEW_NUM_MAIN = 0;

	public static final int VIEW_NUM_LEFT = 100;
	public static final int VIEW_NUM_RIGHT = 200;

	// 고객
	public static final int VIEW_NUM_CUST_LIST = 211;
	public static final int VIEW_NUM_CUST_ADD = 212;
	public static final int VIEW_NUM_CUST_UPDATE = 213;
	public static final int VIEW_NUM_CUST_DELETE = 214;
	// 상품
	public static final int VIEW_NUM_PROD_LIST = 221;
	public static final int VIEW_NUM_PROD_ADD = 222;
	public static final int VIEW_NUM_PROD_UPDATE = 223;
	public static final int VIEW_NUM_PROD_DELETE = 224;
	// 재고
	public static final int VIEW_NUM_STOCK_LIST = 231;
	public static final int VIEW_NUM_STOCK_ADJUST = 232;
	// 할인
	public static final int VIEW_NUM_DISCOUNT_ADJUST = 241;
	// -------------------------------------------------------------------------

	//

	// -------------------------------------------------------------------------

	// 생성자
	public MainPage() {
		super(Main.PAGE_NUM_MAIN, TITLE, SIZE,
				OPTION_CENTER_IN_SCREEN |
						OPTION_VISIBLE * 0 |
						OPTION_BORDERLESS * 0 |
						OPTION_FULL_SCREEN * 0 |
						OPTION_FIXED_SIZE);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();

	}

	// 뷰 추가
	@Override
	protected void onAddViews() {
		try {
			this.addView(new MainView());
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

		if (firstTime) {
			var prodMngr = ProductManager.getInstance();
			var prodStocksMngr = ProductStocksManager.getInstance();
			var prodDiscntsMngr = ProductDiscountsManager.getInstance();

			// try {
			// // prodMngr.createTable();
			// // SystemUtils.sleep(100);
			// // prodStocksMngr.createTable();
			// // SystemUtils.sleep(100);
			// // prodDiscntsMngr.createTable();
			// // SystemUtils.sleep(100);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			prodMngr.setActionListener(new ListActionAdapter<ProductItem>() {

				@Override
				public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
					System.out.println("MainPage.onShow().[ProductManagerMem] onAdded()");
					System.out.println(" -> item: " + item);
					System.out.println();

					try {
						// [상품 재고 관리자]에 상품 추가
						var psi = new ProductStockItem(item.getId());
						prodStocksMngr.add(psi);
						prodStocksMngr.doInsertQuery(psi);

						// [상품 할인 관리자]에 상품 추가
						var pdi = new ProductDiscountItem(item.getId());
						prodDiscntsMngr.add(pdi);
						prodDiscntsMngr.doInsertQuery(pdi);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onDeleted(ListManager<ProductItem> mngr, ProductItem item) {
					System.out.println("MainPage.onShow().[ProductManagerMem] onDeleted()");
					System.out.println(" -> item: " + item);
					System.out.println();

					try {
						// [상품 재고 관리자]에서 상품 제거
						var pi0 = prodStocksMngr.getItemByProdId(item.getId());
						prodStocksMngr.deleteById(pi0.getId());
						prodStocksMngr.doDeleteQuery("prod_id = " + pi0.getId());

						// [상품 할인 관리자]에서 상품 제거
						var pi1 = prodDiscntsMngr.getItemByProdId(item.getId());
						prodDiscntsMngr.deleteById(pi1.getId());
						prodDiscntsMngr.doDeleteQuery("prod_id = " + pi1.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});

			// this.addDummyDataToMemAndDb();
			this.loadDataFromDb();
		}

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

	// 더미데이터 메모리 추가
	private void addDummyDataToMemAndDb() {
		var custMngr = CustomerManager.getInstance();
		var prodMngr = ProductManager.getInstance();

		try {

			// 고객 더미데이터 추가

			// for (var ci : CustomerItem.getPredefinedData()) {
			// custMngr.add(ci);
			// System.out.println(ci);
			// custMngr.doInsertQuery(ci);
			// SystemUtils.sleep(100);
			// // System.out.println(", " + ci.getName());
			// }

			// 상품 더미데이터 추가

			prodMngr.enableActionListener(true);
			for (var di : ProductItem.getPredefinedProductData()) {
				prodMngr.add(di);
				prodMngr.doInsertQuery(di);
				SystemUtils.sleep(100);
				// System.out.println(", " + di.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadDataFromDb() {
		var custMngr = CustomerManager.getInstance();
		var prodMngr = ProductManager.getInstance();
		var prodStMngr = ProductStocksManager.getInstance();
		var prodDiscMngr = ProductDiscountsManager.getInstance();

		try {
			prodMngr.doSelectQuery(null, null, null, "prod_id");
			custMngr.doSelectQuery(null, null, null, "cust_id");
			prodStMngr.doSelectQuery(null, null, null, "prod_id");
			prodDiscMngr.doSelectQuery(null, null, null, "prod_id");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
