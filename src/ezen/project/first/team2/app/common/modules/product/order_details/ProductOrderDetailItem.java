////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.order_details;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManagerMem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManagerMem;

public class ProductOrderDetailItem extends ListItem {
	// -------------------------------------------------------------------------

	// 상품 구매 ID
	private int mProdOrderId;

	// 상품 ID
	private int mProdId;

	// 상품 할인 ID
	private int mProdDiscntId;

	// 수량
	private int mQuantity;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductOrderDetailItem(int id, int prodOrderId, int prodId,
			int prodDiscntId, int quantity) {
		this.setValues(id, prodOrderId, prodId, prodDiscntId, quantity);
	}

	// -------------------------------------------------------------------------

	// 개별 값 설정
	public void setValues(int id, int prodOrderId, int prodId,
			int prodDiscntId, int quantity) {
		this.mId = id;

		this.mProdOrderId = prodOrderId;
		this.mProdId = prodId;
		this.mProdDiscntId = prodDiscntId;
		this.mQuantity = quantity;
	}

	// -------------------------------------------------------------------------

	// 상품 구매 ID 얻기
	public int getProdOrderId() {
		return this.mProdOrderId;
	}

	// 상품 ID 얻기
	public int getProdId() {
		return this.mProdId;
	}

	// 상품 할인 ID 얻기
	public int getProdDiscntId() {
		return this.mProdDiscntId;
	}

	// 수량 얻기
	public int getQuantity() {
		return this.mQuantity;
	}

	// 원래 금액 얻기
	public int getOrgPrice() {
		return -1;
	}

	// 최종 금액 얻기
	public int getFinalPrice() {
		return -1;
	}

	//

	// 상품 구매 아이템 얻기
	public ProductOrderItem getProdOrderItem() throws Exception {
		var prodOrderMngr = ProductOrdersManagerMem.getInstance();
		return prodOrderMngr.findById(this.getProdOrderId());
	}

	// 상품 아이템 얻기
	public ProductItem getProdItem() throws Exception {
		var prodMngr = ProductManagerMem.getInstance();
		return prodMngr.findById(this.getProdId());
	}

	// 상품 할인 아이템 얻기
	public ProductDiscountItem getProdDiscntItem() throws Exception {
		var prodDiscntMngr = ProductDiscountsManagerMem.getInstance();
		return prodDiscntMngr.findById(this.getProdDiscntId());
	}
}
