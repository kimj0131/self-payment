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
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManagerMem;
import ezen.project.first.team2.app.common.utils.UnitUtils;

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
			int prodDiscntId, int quantity) throws Exception {
		this.setValues(id, prodOrderId, prodId, prodDiscntId, quantity);
	}

	// -------------------------------------------------------------------------

	// 개별 값 설정 => 일단 재고 수량 감소
	public void setValues(int id, int prodOrderId, int prodId, int prodDiscntId,
			int quantity) {
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

	// 원래 금액 얻기 => 상품 가격 * 수량
	public int getOrgPrice() throws Exception {
		var prodMngr = ProductManagerMem.getInstance();
		var prodItem = prodMngr.findById(this.getProdId());

		return prodItem.getPrice() * this.getQuantity();
	}

	// 최종 금액 얻기 => 원래 금액 - (할인 금액 * 수량)
	public int getFinalPrice() throws Exception {
		// 비회원인 경우
		if (true) {
			return this.getOrgPrice();
		}

		var prodDiscntsMngr = ProductDiscountsManagerMem.getInstance();
		var prodDiscntItem = prodDiscntsMngr.findById(this.getProdId());

		return this.getOrgPrice() - (prodDiscntItem.getAmount() * this.getQuantity());
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

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		try {
			return String.format("id:%06d, prod_order_id:%06d, prod_id:%06d, " +
					"prod_dicnt_id:%06d, quantity:%2d, org_price:%8s, final_price:%8s ",
					this.getId(), this.getProdOrderId(), this.getProdDiscntId(),
					this.getProdDiscntId(), this.getQuantity(),
					UnitUtils.numToCurrencyStr(this.getOrgPrice()),
					UnitUtils.numToCurrencyStr(this.getFinalPrice()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		try {
			var podi = (ProductOrderDetailItem) item;
			this.setValues(podi.getId(), podi.getProdOrderId(), podi.getProdId(),
					podi.getProdDiscntId(), podi.getQuantity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
