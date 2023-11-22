////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductDiscountsManagerMem extends ListManagerMem<ProductDiscountItem> {
	// -------------------------------------------------------------------------

	private static ProductDiscountsManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductDiscountsManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductDiscountsManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductDiscountsManagerMem();
		}

		return mInstance;
	}
}
