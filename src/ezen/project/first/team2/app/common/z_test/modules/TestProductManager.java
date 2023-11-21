package ezen.project.first.team2.app.common.z_test.modules;

import ezen.project.first.team2.app.common.modules.product.ProductCode;
import ezen.project.first.team2.app.common.modules.product.ProductInfo;
import ezen.project.first.team2.app.common.modules.product.ProductManagerMem;

public class TestProductManager {
	static void printList(ProductManagerMem mngr) {
		System.out.println("-".repeat(60));
		System.out.println("제품 목록");

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
			prodMngr.init();

			// 제품 추가
			{
				System.out.println("#".repeat(70));
				System.out.println("# 제품 추가");

				// 같은 제품 코드로 등록했을 경우 예외 발생 테스트
				// var i = ProductInfo.getPredefinedProductData()[0];
				// prodMngr.add(i);
				// System.out.println(" - " + i.toString());

				// for (var i2 : ProductInfo.getPredefinedProductData()) {
				// prodMngr.add(i2);
				// System.out.println(" - " + i2.toString());
				// }

				for (int i = 0; i < 5; i++) {
					var i2 = ProductInfo.getPredefinedProductData()[i];
					prodMngr.add(i2);
					System.out.println("  - " + i2.toString());
				}

				System.out.println();

				printList(prodMngr);
			}

			// 제품 수정
			{
				System.out.println("#".repeat(70));
				System.out.println("# 제품 수정");

				var i = prodMngr.find((info, idx) -> info.getName().equals("빼빼로"));
				i.setName("아몬드 빼빼로");

				prodMngr.updateById(i.getId(), i);

				System.out.println();

				printList(prodMngr);
			}

			// 제품 삭제
			{
				System.out.println("#".repeat(70));
				System.out.println("# 제품 삭제");

				var i = prodMngr.find((info, idx) -> info.getProdCode().equals(new ProductCode("S002")));
				prodMngr.deleteById(i.getId());

				System.out.println();
				printList(prodMngr);
			}

			prodMngr.deinit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
