////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.orders;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductOrdersManagerMem extends ListManagerMem<ProductOrderItem> {
	// -------------------------------------------------------------------------

	private static ProductOrdersManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductOrdersManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductOrdersManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductOrdersManagerMem();
		}

		return mInstance;
	}
}
