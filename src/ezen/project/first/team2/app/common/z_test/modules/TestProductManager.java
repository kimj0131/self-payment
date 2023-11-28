package ezen.project.first.team2.app.common.z_test.modules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListActionAdapter;
import ezen.project.first.team2.app.common.modules.base.ListActionListener;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailItem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;

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
		var prodMngr = ProductManager.getInstance();
		var prodStocksMngr = ProductStocksManager.getInstance();
		var prodDiscntsMngr = ProductDiscountsManager.getInstance();

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
						System.out.println("  " + item + " => " + item.getProdItem().getName());
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
	}

	public static void main(String[] args) {
		var custMngr = CustomerManager.getInstance();

		var prodMngr = ProductManager.getInstance();
		var prodStocksMngr = ProductStocksManager.getInstance();
		var prodDiscntsMngr = ProductDiscountsManager.getInstance();

		var prodOrdersMngr = ProductOrdersManager.getInstance();
		var prodOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

		try {
			prodMngr.setActionListener(new ListActionListener<ProductItem>() {
				@Override
				public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
					System.out.println("[TestProductManagerMem] onAdded()");
					System.out.println(" -> item: " + item);
					System.out.println();

					try {
						// [상품 재고 관리자]에 상품 추가
						prodStocksMngr.add(new ProductStockItem(item.getId()));

						// [상품 할인 관리자]에 상품 추가
						prodDiscntsMngr.add(new ProductDiscountItem(item.getId()));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onUpdated(ListManager<ProductItem> mngr, ProductItem oldItem,
						ProductItem newItem) {
					System.out.println("[TestProductManagerMem] onUpdated()");
					System.out.println(" -> oldItem: " + oldItem);
					System.out.println(" -> newItem: " + newItem);
					System.out.println();
				}

				@Override
				public void onDeleted(ListManager<ProductItem> mngr, ProductItem item) {
					System.out.println("[TestProductManagerMem] onDeleted()");
					System.out.println(" -> item: " + item);
					System.out.println();

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
				public void onDeleteItems(ListManager<ProductItem> mngr, List<Integer> idList) {
				}

				@Override
				public void onDeletedItems(ListManager<ProductItem> mngr, List<Integer> idList) {
				}
			});

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
					var item = ProductItem.getPredefinedProductData()[i];
					prodMngr.add(item);

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

				pi = prodMngr.findByName("오감자").get(0);
				System.out.println(" - findByName()");
				System.out.println("    - " + pi);

				piList = prodMngr.findByPrice(1400);
				System.out.println(" - findByPrice()");
				for (var pi1 : piList) {
					System.out.println("    - " + pi1);
				}

				System.out.println();
			}

			// # 상품 구매
			{

				// 비회원 고객 추가

				printTitle("상품 구매");

				System.out.println("=> 재고, 할인율 설정");
				System.out.println();

				// 재고, 할인율 설정 => 관리 앱에서 설정
				prodMngr.iterate((item, idx) -> {
					try {
						// 재고 10개씩 증가. "에이스" 과자는 제외. 상품 재고 수량 테스트.
						if (!item.getName().equals("에이스"))
							prodStocksMngr.updateQuantityByProdId(item.getId(), 10);

						// 할인율 100원씩 증가
						prodDiscntsMngr.setAmountByProdId(item.getId(), 100);
					} catch (Exception e) {
						e.printStackTrace();
					}

					return true;
				});

				printList();

				// POS =========================================================

				// 구매 내역(영수증) ID 발급
				int poiId = prodOrdersMngr.getNextID();
				// 구매 내역(영수증) 아이템 생성 => 비회원 + 사용할 포인트 0.
				var poi = new ProductOrderItem(poiId, LocalDateTime.now());
				prodOrdersMngr.add(poi);

				// 상세 구매 내역이 추가되면 해당 상품 재고 수량 감소
				// 해당 상품에 재고가 없더라도 일단 수량 감소
				prodOrderDetailsMngr.setActionListener(new ListActionAdapter<ProductOrderDetailItem>() {
					@Override
					public void onAdded(ListManager<ProductOrderDetailItem> mngr, ProductOrderDetailItem item) {
						try {
							prodStocksMngr.decQuantityByProdId(item.getProdId(), item.getQuantity());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// 영수증에 상품 추가
				String[] prodNames = { "오감자", "썬칩", "에이스" };
				for (var name : prodNames) {
					var pi = prodMngr.findByName(name).get(0);
					var pdi = prodDiscntsMngr.getItemByProdId(pi.getId());
					// 구매 상품 정보 설정
					var podi = new ProductOrderDetailItem(-1, poiId, pi.getId(), pdi.getId(), 1);
					prodOrderDetailsMngr.add(podi);
				}

				// 회원 관련 처리 => 휴대폰 번호(findByPhoneNumber)로 CustomerItem 객체 획득
				var ci = custMngr.findByPhoneNumber("010-0000-8086");
				if (ci != null) {
					custMngr.add(CustomerItem.getPredefinedData()[0]);

					System.out.println("=> 고객 : " + ci);
					System.out.println();

					// 고객 설정
					poi.setCustId(ci.getId());

					// 사용할 포인트 설정
					poi.setUsedPoint(1000);

					// 포인트 적립
					poi.calcEarnedPoint();
				}

				// 구매 내역 출력
				poi = prodOrdersMngr.findById(poiId);
				System.out.println("구매 내역(영수증)");
				System.out.println("  - " + poi);
				System.out.println();

				// 고객 정보 출력
				if (ci != null) {
					ci = poi.getCustItem();
					System.out.printf("- 고객 정보: %s(%s) \n", ci.getName(), ci.getPhoneNumber());
				} else {
					System.out.println("- 고객 정보: 비회원");
				}

				// 상세 구매 내역 출력
				System.out.println("- 상세 구매 내역");
				var podItems = poi.getProdOrderDetailItems();
				for (var item : podItems) {
					var pi = item.getProdItem();
					System.out.printf("  - %s => %s \n", item, pi.getName());
				}

				System.out.println();
				printList();

				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}