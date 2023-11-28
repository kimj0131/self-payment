////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.stocks;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;

public class ProductStockItem extends ListItem {
	// -------------------------------------------------------------------------

	// 상품 ID
	private int mProdId;
	// 수량
	private int mQuantity;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductStockItem(int prodId) {
		this(-1, prodId, 0);
	}

	// 생성자
	public ProductStockItem(int id, int prodId, int quantity) {
		this.setValues(id, prodId, quantity);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 설정
	public void setValues(int id, int prodId, int quantity) {
		this.mId = id;

		this.mProdId = prodId;
		this.mQuantity = quantity;
	}

	// -------------------------------------------------------------------------

	// 수량 설정
	public void setQuantity(int quantity) {
		this.mQuantity = quantity;
	}

	// 수량 증가
	public void incQuantity(int amount) {
		this.mQuantity += amount;
	}

	// 수량 감소
	public void decQuantity(int amount) {
		this.mQuantity -= amount;
	}

	// -------------------------------------------------------------------------

	// 상품 ID 얻기
	public int getProdId() {
		return this.mProdId;
	}

	// 수량 얻기
	public int getQuantity() {
		return this.mQuantity;
	}

	//

	// 상품 아이템 얻기
	public ProductItem getProdItem() {
		var prodMngr = ProductManager.getInstance();
		return prodMngr.findById(this.getProdId());
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		return String.format("id:%06d, prodId:%06d, quantity:%4d",
				this.getId(), this.getProdId(), this.getQuantity());
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		var psi = (ProductStockItem) item;
		this.setValues(psi.getId(), psi.getProdId(), psi.getQuantity());
	}
}
