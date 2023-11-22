////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.stocks;

import ezen.project.first.team2.app.common.modules.base.ListItem;

public class ProductStockItem extends ListItem {
	// -------------------------------------------------------------------------

	// 수량
	private int mQuantity;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductStockItem() {
	}

	// 생성자
	public ProductStockItem(int id, int quantity) {
		this.setValues(id, quantity);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 설정
	public void setValues(int id, int quantity) {
		this.mId = id;

		this.mQuantity = quantity;
	}

	// -------------------------------------------------------------------------

	// 수량 설정
	public void setQuantity(int quantity) {
		this.mQuantity = quantity;
	}

	// -------------------------------------------------------------------------

	// 수량 얻기
	public int getQuantity() {
		return this.mQuantity;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		return String.format("id:%06d, quantity:%4d", this.getId(), this.getQuantity());
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		var psi = (ProductStockItem) item;
		psi.setValues(psi.getId(), psi.getQuantity());
	}
}
