package ezen.project.first.team2.app.common.z_test.modules.product;

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

public class TestProductPurchasing {
	static void printTitle(String text) {
		System.out.println("=".repeat(70));
		System.out.println("= " + text);
		System.out.println("=".repeat(70));
		System.out.println();
	}

	static void printSection(String text) {
		System.out.println("-".repeat(60));
		System.out.println("- " + text);
		System.out.println("-".repeat(60));
		System.out.println();
	}

	static void printCustList() {
		printSection("고객 리스트");

		try {
			var custMngr = CustomerManager.getInstance();
			custMngr.iterate((item, idx) -> {
				System.out.println(item);

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	static void printProdList() {
		printSection("상품 리스트");

		try {
			var prodMngr = ProductManager.getInstance();
			prodMngr.iterate((item, idx) -> {
				System.out.println(item);

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	static void printProdStockList() {
		printSection("상품 재고 리스트");

		try {
			var prodStocksMngr = ProductStocksManager.getInstance();
			prodStocksMngr.iterate((item, idx) -> {
				try {
					System.out.println(item + " => " + item.getProdItem().getName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	static void printProdDiscntList() {
		printSection("상품 할인 리스트");

		try {
			var prodDiscntMngr = ProductDiscountsManager.getInstance();
			prodDiscntMngr.iterate((item, idx) -> {
				System.out.println(item);

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public static void main(String[] args) {
		var custMngr = CustomerManager.getInstance();
		//
		var prodMngr = ProductManager.getInstance();
		var prodStockMngr = ProductStocksManager.getInstance();
		var prodDiscntsMngr = ProductDiscountsManager.getInstance();

		try {
			// 고객
			{
				custMngr.add(CustomerItem.getPredefinedData()[0]);

				printCustList();

				System.out.println();
			}

			// 상품
			{
				prodMngr.setActionListener(new ListActionAdapter<ProductItem>() {
					@Override
					public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
						try {
							// 상품 재고 관리자에 추가
							prodStockMngr.add(new ProductStockItem(item.getId()));

							// 상품 할인 관리자에 추가
							prodDiscntsMngr.add(new ProductDiscountItem(item.getId()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				for (int i = 0; i < 5; i++) {
					var pi = ProductItem.getPredefinedProductData()[i];
					prodMngr.add(pi);
				}

				printProdList();

				System.out.println();
			}

			// 상품 재고
			{
				prodMngr.iterate((item, idx) -> {
					try {
						prodStockMngr.updateQuantityByProdId(item.getId(), 10);
					} catch (Exception e) {
						e.printStackTrace();
					}

					return true;
				});

				printProdStockList();

				System.out.println();
			}

			// 상품 할인
			{
				prodMngr.iterate((item, idx) -> {
					try {
						prodDiscntsMngr.setAmountByProdId(item.getId(), 100);
					} catch (Exception e) {
						e.printStackTrace();
					}

					return true;
				});

				printProdDiscntList();

				System.out.println();
			}

			var pp = new ProductPurchasing();

			// 1. 구매 내역(영수증) 생성
			{
				pp._1_makeProdOrder();
			}

			// 2. 구매 내역(영수증)에 상품 추가
			{
				final String[] PROD_NAMES = {
						"빼빼로", "오감자", "썬칩", "빼빼로"
				};

				// 상품 구매
				System.out.println("상품 구매");
				for (var name : PROD_NAMES) {
					var pi = prodMngr.findByName(name).get(0);
					pp._2_addProduct(pi.getId(), 1);

					System.out.println("  - " + name + ", 1개 => " + pp.getProdOrderItem().getFinalTotalPrice());
				}
				System.out.println();
			}

			// 3. 회원 포인트 적립
			{
				var ci = custMngr.findByPhoneNumber("010-0000-8086");
				System.out.println("# 고객 정보");
				if (ci != null) {
					pp._3_applyCustomerPoint(ci.getId());
					System.out.println("  - " + ci);
					System.out.println();
					//
					pp._4_setUsedPoint(1000);
					System.out.println("  => 사용 가능 포인트: " + pp.getProdOrderItem().getCustItem().getPoint());
					System.out.println("  => 적립 예정 포인트: " + pp.getProdOrderItem().calcEarnedPoint());
					System.out.println();
				} else {
					System.out.println("  - 비회원");
				}
			}

			// 5. 취소
			{
				final boolean ROLLBACK_FLAG = !true;
				if (ROLLBACK_FLAG) {
					System.out.println("취소!!");
					System.out.println();
					pp._5_rollback();
				}
			}

			// ## 결제 시작 ##
			// ~~
			// ## 결제 완료 ##

			// 6. 완료
			pp._6_commit();

			// 취소를 한 경우 pp.getProdOrderItem()가 null을 리턴한다
			if (pp.getProdOrderItem() != null) {
				// 영수증
				System.out.println("# 구매 내역");
				System.out.println("  - " + pp.getProdOrderItem());

				// 상세 구매 내역
				for (var podi : pp.getProdOrderItem().getProdOrderDetailItems()) {
					System.out.println("    - " + podi + " => " + podi.getProdItem().getName());
				}

				System.out.println();
			}

			// 고객 리스트
			printCustList();

			// 상품 재고 리스트
			printProdStockList();

			//

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
