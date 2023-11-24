////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.order_details;

import java.util.List;

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

	//

	// 상품 구매 ID, 상품 ID 기준으로 아이템 얻기
	public ProductOrderDetailItem getItemByProdOrderIdAndProdId(int prodOrderId, int prodId)
			throws Exception {
		return find((_item, _idx) -> {
			return _item.getProdOrderId() == prodOrderId && _item.getProdId() == prodId;
		});
	}

	// 상품 구매 ID 기준으로 상세 구매 내역 아이템 얻기
	public List<ProductOrderDetailItem> getItemsByProdOrderId(int prodOrderId)
			throws Exception {
		return this.findItems((item, idx) -> item.getProdOrderId() == prodOrderId);
	}

	// -------------------------------------------------------------------------

	// 상품 수량 업데이트
	public void updateQuantity(int prodOrderId, int prodId, int quantity) throws Exception {
		var poi = this.find((item, idx) -> {
			return item.getProdOrderId() == prodOrderId && item.getProdId() == prodId;
		});

		if (poi == null) {
			String msg = String.format("[ProductOrderDetailsManagerMem.updateQuantity()]"
					+
					" Invalid prodOrderId(%d) and/or prodId(%d)!",
					prodOrderId, prodId);
			throw new Exception(msg);
		}

		poi.setQuantity(quantity);
		this.updateById(poi.getId(), poi);
	}
}
