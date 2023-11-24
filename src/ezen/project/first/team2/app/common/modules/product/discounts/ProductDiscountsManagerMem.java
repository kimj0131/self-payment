////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductDiscountsManagerMem extends ListManagerMem<ProductDiscountItem> {
	// -------------------------------------------------------------------------

	private static ProductDiscountsManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductDiscountsManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductDiscountsManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductDiscountsManagerMem();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 Id로 아이템 얻기
	public ProductDiscountItem getItemByProdId(int prodId) throws Exception {
		return this.find((_item, _idx) -> _item.getProdId() == prodId);
	}

	// 상품 Id로 할인율 설정
	public void setAmountByProdId(int prodId, int amount) throws Exception {
		var item = this.getItemByProdId(prodId);
		item.setAmount(amount);
		this.updateById(item.getId(), item);
	}

	// 상품 Id로 할인율 얻기
	public int getAmountByProdId(int prodId) throws Exception {
		var item = this.getItemByProdId(prodId);
		return item.getAmount();
	}
}
