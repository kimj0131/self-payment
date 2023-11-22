////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;

public class ProductDiscountItem extends ListItem {
	// -------------------------------------------------------------------------

	// 상품 ID
	private int mProdId;
	// 할인 금액
	private int mAmount;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductDiscountItem(int prodId) {
		this(-1, prodId, 0);
	}

	// 생성자
	public ProductDiscountItem(int id, int prodId, int amount) {
		this.setValues(id, prodId, amount);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 설정
	public void setValues(int id, int prodId, int amount) {
		this.mId = id;

		this.mProdId = prodId;
		this.mAmount = amount;
	}

	// -------------------------------------------------------------------------

	// 할인 금액 설정
	public void setAmount(int amount) {
		this.mAmount = amount;
	}

	// -------------------------------------------------------------------------

	// 상품 ID 얻기
	public int getProdId() {
		return this.mProdId;
	}

	// 할인 금액 얻기
	public int getAmount() {
		return this.mAmount;
	}

	//

	// 상품 아이템 얻기
	public ProductItem getProdItem() throws Exception {
		var prodMngr = ProductManagerMem.getInstance();
		return prodMngr.findById(this.getProdId());
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		return String.format("id:%06d, prodId:%6d, amount:%6d",
				this.getId(), this.getProdId(), this.getAmount());
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		var pdi = (ProductDiscountItem) item;
		this.setValues(pdi.getId(), pdi.getProdId(), pdi.getAmount());
	}
}
