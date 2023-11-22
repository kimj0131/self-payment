package ezen.project.first.team2.app.common.z_test.modules;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.product.ProductCode;
import ezen.project.first.team2.app.common.modules.product.ProductItem;
import ezen.project.first.team2.app.common.modules.product.ProductManagerMem;

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

	static void printList(ProductManagerMem mngr) {
		printSection("상품 리스트");

		try {
			mngr.iterate((info, idx) -> {
				System.out.println("  " + info);

				return true;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public static void main(String[] args) {
		ProductManagerMem prodMngr = ProductManagerMem.getInstance();
		try {
			//
			prodMngr.init();
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
				// System.out.println(" - " + i2.toString());
				// }

				for (int i = 0; i < 5; i++) {
					var i2 = ProductItem.getPredefinedProductData()[i];
					prodMngr.add(i2);
					System.out.println("  - " + i2.toString());
				}

				System.out.println();

				printList(prodMngr);
			}

			// 상품 수정
			{
				printTitle("상품 수정");

				var pi = prodMngr.findById(0);
				System.out.println(pi);
				pi.setName("아몬드 빼배로");

				prodMngr.updateById(pi.getId(), pi);

				System.out.println();

				printList(prodMngr);
			}

			// 상품 삭제
			{
				printTitle("상품 삭제");

				var pi = prodMngr.findById(3);
				System.out.println(pi);
				prodMngr.deleteById(pi.getId());

				System.out.println();
				printList(prodMngr);
			}

			// 상품 조회
			{
				printTitle("상품 조회");

				printList(prodMngr);

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