////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_140700] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.payment;

import java.awt.Font;

import javax.swing.ImageIcon;

import ezen.project.first.team2.app.common.framework.StatusManager;
import ezen.project.first.team2.app.common.modules.base.ListActionListener;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManagerMem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManagerMem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManagerMem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManagerMem;
import ezen.project.first.team2.app.common.pages.splash.SplashPage;
import ezen.project.first.team2.app.common.pages.splash.SplashPageParams;
import ezen.project.first.team2.app.common.pages.splash.views.MainView;
import ezen.project.first.team2.app.common.utils.TimeUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;

public class Main extends StatusManager {
	// 페이지 번호 정의 - 뷰에서도 사용하므로 public으로 선언
	public static final int PAGE_NUM_SPLASH = SplashPage.PAGE_NUM;
	public static final int PAGE_NUM_STANBY = 100;
	public static final int PAGE_NUM_MAIN = 200;

	public Font mFont0;
	public Font mFont1;
	public Font mFont2;
	public Font mFont3;
	public ImageIcon mImg0;

	// 초기화 작업 - DB 커넥션 등
	@Override
	protected void onInit() {
		System.out.println("[Main.onInit()]");
		setTestData();
	}
	
	private void setTestData() {
		// Test를 위한 더미 데이터 설정 작업
				var custMngr = CustomerManagerMem.getInstance();
				var prodMngr = ProductManagerMem.getInstance();
				var prodStocksMngr = ProductStocksManagerMem.getInstance();
				var prodDiscntsMngr = ProductDiscountsManagerMem.getInstance();
				var prodOrdersMngr = ProductOrdersManagerMem.getInstance();
				var prodOrderDetailsMngr = ProductOrderDetailsManagerMem.getInstance();
				
				prodMngr.setActionListener(new ListActionListener<ProductItem>() {
					
					@Override
					public void onUpdated(ListManager<ProductItem> mngr, ProductItem oldItem, ProductItem newItem) {}
					
					@Override
					public void onInitialized(ListManager<ProductItem> mngr) {}
					
					@Override
					public void onDeleted(ListManager<ProductItem> mngr, ProductItem item) {
						try {
							// [상품 재고 관리자]에서 상품 제거
							var pi0 = prodStocksMngr.getItemByProdId(item.getId());
							prodStocksMngr.deleteById(pi0.getId());

							// [상품 할인 관리자]에서 상품 제거
							var pi1 = prodDiscntsMngr.getItemByProdId(item.getId());
							prodDiscntsMngr.deleteById(pi1.getId());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					@Override
					public void onDeinitializing(ListManager<ProductItem> mngr) {}
					
					@Override
					public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
						try {
							// [상품 재고 관리자]에 상품 추가
							prodStocksMngr.add(new ProductStockItem(item.getId()));
							
							// [상품 할인 관리자]에 상품 추가
							prodDiscntsMngr.add(new ProductDiscountItem(item.getId()));

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				try {
					custMngr.init();
					
					prodMngr.init();
					prodStocksMngr.init();
					prodDiscntsMngr.init();
					
					prodOrdersMngr.init();
					prodOrderDetailsMngr.init();
					
					// prodMngr에 ProductItem 더미 데이터 추가
					for (int i = 0; i < ProductItem.getPredefinedProductData().length; i++) {
						var item = ProductItem.getPredefinedProductData()[i];
						prodMngr.add(item);
					}
					
					// cumstMngr에 CustomerItem 더미 데이터 추가
					for (int i = 0; i < CustomerItem.getPredefinedData().length; i++) {
						var item = CustomerItem.getPredefinedData()[i];
						custMngr.add(item);
					}
					
					// 재고, 할인율 설정 => 관리 앱에서 설정
					prodMngr.iterate((item, idx) -> {
						try {
							// 재고 10개씩 증가. "에이스" 과자는 제외. 상품 재고 수량 테스트.
							if (!item.getName().equals("에이스"))
								prodStocksMngr.setQuantityByProdId(item.getId(), 10);

							// 할인율 100원씩 증가
							if (item.getId() > 18)
								prodDiscntsMngr.setAmountByProdId(item.getId(), 100);
						} catch (Exception e) {
							e.printStackTrace();
						}

						return true;
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					System.out.println("customers");
					custMngr.iterate((item, idx) -> {
						System.out.println("  " + item);
						return true;
					});
					
					System.out.println("products");
					prodMngr.iterate((item, idx) -> {
						System.out.println("  " + item);
						return true;
					});
					
					System.out.println("product_stocks");
					prodStocksMngr.iterate((item, idx) -> {
						System.out.println("  " + item);
						return true;
					});
					
					System.out.println("product_discounts");
					prodDiscntsMngr.iterate((item, idx) -> {
						System.out.println("  " + item);
						return true;
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	// 페이지 추가 작업
	@Override
	protected void onAddPages() {
		try {
			// this.addPage(new SplashPage(this.getSplashPageParams()));
			// this.addPage(new StanbyPage());
			this.addPage(new MainPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이벤트 리스너 추가 작업
	@Override
	protected void onAddEventListeners() {
	}

	// 실행 작업 - 페이지 선택 등
	@Override
	protected void onRun() {
		try {
			// this.setSelectedPageByNum(PAGE_NUM_SPLASH);
			this.setSelectedPageByNum(Main.PAGE_NUM_MAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 종료 작업 - DB 디스커넥션 등
	@Override
	protected void onExit() {
		System.out.println("[Main.onExit()]");
	}

	@SuppressWarnings("unused")
	private SplashPageParams getSplashPageParams() {
		SplashPageParams params = new SplashPageParams(new SplashPageParams.Listener() {
			@Override
			public void onLoadResources(Object param, int resourceIndex, int resourceCount) {
				Main main = (Main) param;
				SplashPage splashPage = (SplashPage) main.getPageByNum(SplashPage.PAGE_NUM);
				MainView mainView = (MainView) splashPage.getViewByNum(SplashPage.VIEW_NUM_MAIN);

				final int rsrcIdx = resourceIndex;
				final int rsrcCnt = resourceCount;

				String s = String.format("rsrcIdx: %d, rsrcCnt: %d", rsrcIdx, rsrcCnt);
				System.out.println(s);
				mainView.setLabel0Text(s);

				// 1초가 소요되는 작업이라 가정
				// SystemUtils.sleep(1 * 1000);

				switch (rsrcIdx) {
					case 0:
						main.mFont0 = new Font("맑은 고딕", Font.BOLD, 16);
						break;

					case 1:
						main.mFont1 = new Font("맑은 고딕", Font.BOLD, 32);
						break;

					case 2:
						main.mFont2 = new Font("맑은 고딕", Font.BOLD, 48);
						break;

					case 3:
						main.mFont3 = new Font("맑은 고딕", Font.BOLD, 64);
						break;

					case 4:
						TimeUtils.startElapsedTime();
						main.mImg0 = new ImageIcon("resources/images/retriever0.jpg");
						// main.mImg0 = new ImageIcon("resources/images/img00.jpg");
						System.out.println(TimeUtils.getElapsedTimeStr());
						System.out.println(main.mImg0.getIconWidth());
						break;
				}
			}

			@Override
			public void onCompleteResources(Object param) {
				Main main = (Main) param;
				main.performSetResources();

				try {
					Main.this.setSelectedPageByNum(Main.PAGE_NUM_STANBY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, this, 5);

		return params;
	}

	public static void main(String[] args) {
		(new Main()).run();
	}
}