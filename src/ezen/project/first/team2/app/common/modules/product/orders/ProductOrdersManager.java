////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.orders;

import ezen.project.first.team2.app.common.modules.base.ListManager;

public class ProductOrdersManager extends ListManager<ProductOrderItem> {
	// -------------------------------------------------------------------------

	private static ProductOrdersManager mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductOrdersManager() {
	}

	// 인스턴스 얻기
	public static ProductOrdersManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductOrdersManager();
		}

		return mInstance;
	}
}
