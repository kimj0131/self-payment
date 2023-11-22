////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.order_details;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManagerMem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManagerMem;

public class ProductOrderDetailsManagerMem extends ListManagerMem<ProductOrderDetailItem> {
	// -------------------------------------------------------------------------

	private static ProductOrderDetailsManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductOrderDetailsManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductOrderDetailsManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductOrderDetailsManagerMem();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 구매 아이템 얻기
	public ProductOrderItem getProdOrderItem(int id) throws Exception {
		var prodOrderMngr = ProductOrdersManagerMem.getInstance();
		return prodOrderMngr.findById(id);
	}

	// 상품 아이템 얻기
	public ProductItem getProdItem(int id) throws Exception {
		var prodMngr = ProductManagerMem.getInstance();
		return prodMngr.findById(id);
	}

	// 상품 할인 아이템 얻기
	public ProductDiscountItem getProdDiscntItem(int id) throws Exception {
		var prodDiscntMngr = ProductDiscountsManagerMem.getInstance();
		return prodDiscntMngr.findById(id);
	}
}
