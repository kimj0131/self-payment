////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import ezen.project.first.team2.app.common.modules.base.ListItem;

public class ProductDiscountItem extends ListItem {
	// -------------------------------------------------------------------------

	// 할인 금액
	private int mAmount;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductDiscountItem() {
	}

	// 생성자
	public ProductDiscountItem(int id, int amount) {
		this.setValues(id, amount);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 설정
	public void setValues(int id, int amount) {
		this.mId = id;

		this.mAmount = amount;
	}

	// -------------------------------------------------------------------------

	// 할인 금액 설정
	public void setAmount(int amount) {
		this.mAmount = amount;
	}

	// -------------------------------------------------------------------------

	// 할인 금액 얻기
	public int getAmount() {
		return this.mAmount;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		return String.format("id:%06d, amount:%6d", this.getId(), this.getAmount());
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		var pdi = (ProductDiscountItem) item;
		pdi.setValues(pdi.getId(), pdi.getAmount());
	}
}
