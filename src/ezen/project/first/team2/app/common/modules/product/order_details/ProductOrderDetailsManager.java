////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.order_details;

import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManager;

public class ProductOrderDetailsManager extends ListManager<ProductOrderDetailItem> {
	// -------------------------------------------------------------------------

	private static ProductOrderDetailsManager mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductOrderDetailsManager() {
	}

	// 인스턴스 얻기
	public static ProductOrderDetailsManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductOrderDetailsManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 구매 아이템 얻기
	public ProductOrderItem getProdOrderItem(int id) {
		var prodOrderMngr = ProductOrdersManager.getInstance();
		return prodOrderMngr.findById(id);
	}

	// 상품 아이템 얻기
	public ProductItem getProdItem(int id) {
		var prodMngr = ProductManager.getInstance();
		return prodMngr.findById(id);
	}

	// 상품 할인 아이템 얻기
	public ProductDiscountItem getProdDiscntItem(int id) {
		var prodDiscntMngr = ProductDiscountsManager.getInstance();
		return prodDiscntMngr.findById(id);
	}

	//

	// 상품 구매 ID, 상품 ID 기준으로 아이템 얻기
	public ProductOrderDetailItem getItemByProdOrderIdAndProdId(int prodOrderId, int prodId) {
		return find((_item, _idx) -> _item.getProdOrderId() == prodOrderId && _item.getProdId() == prodId);
	}

	// 상품 구매 ID 기준으로 상세 구매 내역 아이템 얻기
	public List<ProductOrderDetailItem> getItemsByProdOrderId(int prodOrderId) {
		return this.findItems((item, idx) -> item.getProdOrderId() == prodOrderId);
	}

	// -------------------------------------------------------------------------

	// 상품 수량 업데이트
	public void updateQuantity(int prodOrderId, int prodId, int quantity) throws Exception {
		// 상품 구매 ID와 제품 ID가 유효하지 않은 경우
		var poi = this.find((item, idx) -> item.getProdOrderId() == prodOrderId && item.getProdId() == prodId);
		if (poi == null) {
			String msg = String.format("[ProductOrderDetailsManager.updateQuantity()]"
					+
					" Invalid prodOrderId(%d) and/or prodId(%d)!",
					prodOrderId, prodId);
			throw new Exception(msg);
		}

		poi.setQuantity(quantity);
		this.updateById(poi.getId(), poi);
	}
}
