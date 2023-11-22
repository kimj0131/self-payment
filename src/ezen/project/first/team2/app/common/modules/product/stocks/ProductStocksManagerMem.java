////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.stocks;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductStocksManagerMem extends ListManagerMem<ProductStockItem> {
	// -------------------------------------------------------------------------

	private static ProductStocksManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductStocksManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductStocksManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductStocksManagerMem();
		}

		return mInstance;
	}
}
