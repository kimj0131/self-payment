////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.stocks;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductStocksManagerMem extends ListManagerMem<ProductStockItem> {
	// -------------------------------------------------------------------------

	private static ProductStocksManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductStocksManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductStocksManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductStocksManagerMem();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 Id로 아이템 얻기
	public ProductStockItem getItemByProdId(int prodId) throws Exception {
		return this.find((_item, _idx) -> _item.getProdId() == prodId);
	}

	// 상품 Id로 수량 설정
	public void setQuantityByProdId(int prodId, int quantity) throws Exception {
		var item = this.getItemByProdId(prodId);
		item.setQuantity(quantity);
		this.updateById(item.getId(), item);
	}

	// 상품 Id로 수량 증가 설정
	public void incQuantityByProdId(int prodId, int amount) throws Exception {
		var item = this.getItemByProdId(prodId);
		item.incQuantity(amount);
		this.updateById(item.getId(), item);
	}

	// 상품 Id로 수량 설정
	public void decQuantityByProdId(int prodId, int amount) throws Exception {
		var item = this.getItemByProdId(prodId);
		item.decQuantity(amount);
		this.updateById(item.getId(), item);
	}

	// 상품 Id로 재고 수량 얻기
	public int getQuantityByProdId(int prodId) throws Exception {
		var item = this.getItemByProdId(prodId);
		return item.getQuantity();
	}
}
