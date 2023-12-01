////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.stocks;

import java.sql.ResultSet;

import ezen.project.first.team2.app.common.modules.base.ListManagerDb;

public class ProductStocksManager extends ListManagerDb<ProductStockItem> {
	// -------------------------------------------------------------------------

	private static final String TABLE_NAME = "PRODUCT_STOCKS";

	private static ProductStocksManager mInstance = null;
	private static ProductStocksManager mTmpInstance = new ProductStocksManager();

	// -------------------------------------------------------------------------

	// 생성자
	private ProductStocksManager() {
		super(TABLE_NAME);
	}

	// 인스턴스 얻기
	public static ProductStocksManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductStocksManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 Id로 아이템 얻기
	public ProductStockItem getItemByProdId(int prodId) {
		return this.find((_item, _idx) -> _item.getProdId() == prodId);
	}

	// 상품 Id로 수량 설정
	public void updateQuantityByProdId(int prodId, int quantity) throws Exception {
		var item = this.getItemByProdId(prodId);
		item.setQuantity(quantity);
		this.updateById(item.getId(), item);
	}

	// 상품 Id로 수량 증가
	public void incQuantityByProdId(int prodId, int amount) throws Exception {
		var item = this.getItemByProdId(prodId);
		item.incQuantity(amount);
		this.updateById(item.getId(), item);
	}

	// 상품 Id로 수량 감소
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

	// -------------------------------------------------------------------------

	@Override
	protected ListManagerDb<ProductStockItem> onGetTmpInstance() {
		return mTmpInstance;
	}

	@Override
	protected String onMakeCreateTableQuery(String tableName) {
		return String.format("create table %s(" +
				"prod_stock_id number primary key, " +
				"prod_id number not null, " +
				"quantity number not null" +
				")", tableName);
	}

	@Override
	protected ProductStockItem onResultSetToItem(ResultSet rs) {
		return new ProductStockItem(
				this.getInt(rs, "prod_stock_id"),
				this.getInt(rs, "prod_id"),
				this.getInt(rs, "quantity"));
	}

	@Override
	protected String[] onMakeFieldsetAndValues(ProductStockItem item) {
		String fieldset = "prod_stock_id, prod_id, quantity";
		String values = String.format("%d, %d, %d",
				item.getId(),
				item.getProdId(),
				item.getQuantity());

		return new String[] { fieldset, values };
	}

	@Override
	protected String onMakeSetAll(ProductStockItem item) throws Exception {
		String s = "";

		// s += String.format("prod_stock_id = %d, ", item.getId());
		// s += String.format("prod_id = %d, ", item.getProdId());
		s += String.format("quantity = %d", item.getQuantity());

		return s;
	}

	@Override
	protected String onMakeSet(ProductStockItem item, String[] fieldset) throws Exception {
		String s = "";

		for (var f : fieldset) {
			if (f.equals("prod_stock_id"))
				s += String.format("prod_stock_id = %d, ", item.getId());
			else if (f.equals("prod_id"))
				s += String.format("prod_id = %d, ", item.getProdId());
			else if (f.equals("quantity"))
				s += String.format("quantity = %d, ", item.getQuantity());
		}

		s = s.substring(0, s.length() - 2);

		return s;
	}
}
