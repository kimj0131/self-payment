////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import java.sql.ResultSet;

import ezen.project.first.team2.app.common.modules.base.ListManagerDb;

public class ProductDiscountsManager extends ListManagerDb<ProductDiscountItem> {
	// -------------------------------------------------------------------------

	private static final String TABLE_NAME = "PRODUCT_DISCOUNT";

	private static ProductDiscountsManager mInstance = null;
	private static ProductDiscountsManager mTmpInstance = new ProductDiscountsManager();

	// -------------------------------------------------------------------------

	// 생성자
	private ProductDiscountsManager() {
		super(TABLE_NAME);
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

	// -------------------------------------------------------------------------

	@Override
	protected ListManagerDb<ProductDiscountItem> onGetTmpInstance() {
		return mTmpInstance;
	}

	@Override
	protected String onMakeCreateTableQuery(String tableName) {
		return String.format("create table %s(" +
				"prod_discnt_id number primary key, " +
				"prod_id number not null, " +
				"amount number not null" +
				")", tableName);
	}

	@Override
	protected ProductDiscountItem onResultSetToItem(ResultSet rs) {
		return new ProductDiscountItem(
				this.getInt(rs, "prod_discnt_id"),
				this.getInt(rs, "prod_id"),
				this.getInt(rs, "amount"));
	}

	@Override
	protected String[] onMakeFieldsetAndValues(ProductDiscountItem item) {
		String fieldset = "prod_discnt_id, prod_id, amount";
		String values = String.format("%d, %d, %d",
				item.getId(),
				item.getProdId(),
				item.getAmount());

		return new String[] { fieldset, values };

	}

	@Override
	protected String onMakeSet(ProductDiscountItem item, String[] fieldset) throws Exception {
		String s = "";

		for (var f : fieldset) {
			if (f.equals("prod_discnt_id"))
				s += String.format("prod_discnt_id = %d", item.getId());
			else if (f.equals("prod_id"))
				s += String.format("prod_id = %d", item.getProdId());
			else if (f.equals("amount"))
				s += String.format("amount = %d", item.getAmount());
		}

		return s;
	}
}
