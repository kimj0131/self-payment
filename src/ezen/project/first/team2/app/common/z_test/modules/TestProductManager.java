package ezen.project.first.team2.app.common.z_test.modules;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListActionListener;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManagerMem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManagerMem;

public class TestProductManager {
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

	static void printList() {
		var prodMngr = ProductManagerMem.getInstance();
		var prodStocksMngr = ProductStocksManagerMem.getInstance();
		var prodDiscntsMngr = ProductDiscountsManagerMem.getInstance();

		try {
			// 상품 리스트
			{
				printSection("상품 리스트");
				prodMngr.iterate((item, idx) -> {
					System.out.println("  " + item);

					return true;
				});

				System.out.println();
			}

			// 상품 재고 리스트
			{
				printSection("상품 재고 리스트");
				prodStocksMngr.iterate((item, idx) -> {
					try {
						var prodItem = prodMngr.findById(item.getProdId());
						System.out.println("  " + item + " => " + prodItem.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}

					return true;
				});

				System.out.println();
			}

			// 상품 할인 리스트
			{
				printSection("상품 할인 리스트");
				prodDiscntsMngr.iterate((item, idx) -> {
					try {
						var prodItem = prodMngr.findById(item.getProdId());
						System.out.println("  " + item + " => " + prodItem.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}

					return true;
				});

				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public static void main(String[] args) {
		var prodMngr = ProductManagerMem.getInstance();
		var prodStocksMngr = ProductStocksManagerMem.getInstance();
		var prodDiscntsMngr = ProductDiscountsManagerMem.getInstance();

		try {
			prodMngr.setActionListener(new ListActionListener<ProductItem>() {

				@Override
				public void onInitialized(ListManager<ProductItem> mngr) {
					System.out.println("[TestProductManagerMem] onInitialized()");
				}

				@Override
				public void onDeinitializing(ListManager<ProductItem> mngr) {
					System.out.println("[TestProductManagerMem] onDeinitializing()");
				}

				@Override
				public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
					System.out.println("[TestProductManagerMem] onAdded()");
					System.out.println("  -> item: " + item);
					System.out.println();

					try {
						// 상품 재고 관리자 객체에 상품 추가
						prodStocksMngr.add(new ProductStockItem(item.getId()));

						// 상품 할인 관리자 객체에 상품 추가
						prodDiscntsMngr.add(new ProductDiscountItem(item.getId()));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onUpdated(ListManager<ProductItem> mngr, ProductItem oldItem, ProductItem newItem) {
					System.out.println("[TestProductManagerMem] onUpdated()");
					System.out.println("  -> oldItem: " + oldItem);
					System.out.println("  -> newItem: " + newItem);
					System.out.println();
				}

				@Override
				public void onDeleted(ListManager<ProductItem> mngr, ProductItem item) {
					System.out.println("[TestProductManagerMem] onDeleted()");
					System.out.println("  -> item: " + item);
					System.out.println();
				}

			});

			//
			prodMngr.init();
			prodStocksMngr.init();
			prodDiscntsMngr.init();
			//

			// 상품 추가
			{
				printTitle("상품 추가");

				// 같은 제품 코드로 등록했을 경우 예외 발생 테스트
				// var i = ProductInfo.getPredefinedProductData()[0];
				// prodMngr.add(i);
				// System.out.println(" - " + i.toString());

				// for (var i2 : ProductInfo.getPredefinedProductData()) {
				// prodMngr.add(i2);
				// //System.out.println(" - " + i2.toString());
				// }

				for (int i = 0; i < 5; i++) {
					var i2 = ProductItem.getPredefinedProductData()[i];
					prodMngr.add(i2);
					// System.out.println(" - " + i2.toString());
				}

				System.out.println();

				printList();
			}

			// 상품 수정
			{
				printTitle("상품 수정");

				var pi = new ProductItem(-1, null, LocalDate.of(2023, 1, 1),
						"과자이름", 1500, "새로운 과자");
				prodMngr.updateById(0, pi);

				System.out.println();

				printList();
			}

			// 상품 삭제
			{
				printTitle("상품 삭제");

				var pi = prodMngr.findById(3);
				System.out.println(pi);
				prodMngr.deleteById(pi.getId());

				System.out.println();
				printList();
			}

			// 상품 조회
			{
				printTitle("상품 조회");

				printList();

				ProductItem pi = null;
				List<ProductItem> piList = null;

				pi = prodMngr.findByProductCode(new ProductCode("S001"));
				System.out.println(" - findByProductCode()");
				System.out.println("    - " + pi);

				piList = prodMngr.findByRegDate(LocalDate.now());
				System.out.println(" - findByRegDate()");
				for (var pi1 : piList) {
					System.out.println("    - " + pi1);
				}

				pi = prodMngr.findByName("오감자");
				System.out.println(" - findByName()");
				System.out.println("    - " + pi);

				piList = prodMngr.findByPrice(1400);
				System.out.println(" - findByPrice()");
				for (var pi1 : piList) {
					System.out.println("    - " + pi1);
				}
			}

			//
			prodMngr.deinit();
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}