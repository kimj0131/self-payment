////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import ezen.project.first.team2.app.common.modules.base.ListManager;

public class ProductDiscountsManager extends ListManager<ProductDiscountItem> {
	// -------------------------------------------------------------------------

	private static ProductDiscountsManager mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductDiscountsManager() {
	}

	// 인스턴스 얻기
	public static ProductDiscountsManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductDiscountsManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 Id로 아이템 얻기
	public ProductDiscountItem getItemByProdId(int prodId) {
		return this.find((_item, _idx) -> _item.getProdId() == prodId);
	}

	// 상품 Id로 할인율 설정
	public void setAmountByProdId(int prodId, int amount) {
		try {
			var item = this.getItemByProdId(prodId);
			item.setAmount(amount);
			this.updateById(item.getId(), item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 상품 Id로 할인율 얻기
	public int getAmountByProdId(int prodId) {
		var item = this.getItemByProdId(prodId);
		return item.getAmount();
	}
}
