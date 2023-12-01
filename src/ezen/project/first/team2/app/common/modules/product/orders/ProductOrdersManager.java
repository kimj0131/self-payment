////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.orders;

import java.sql.ResultSet;

import ezen.project.first.team2.app.common.modules.base.ListManagerDb;

public class ProductOrdersManager extends ListManagerDb<ProductOrderItem> {

	private static final String TABLE_NAME = "PRODUCT_ORDERS";

	// -------------------------------------------------------------------------

	private static ProductOrdersManager mInstance = null;
	private ProductOrdersManager mTmpInstance = new ProductOrdersManager();

	// -------------------------------------------------------------------------

	// 생성자
	private ProductOrdersManager() {
		super(TABLE_NAME);
	}

	// 인스턴스 얻기
	public static ProductOrdersManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductOrdersManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	@Override
	protected ListManagerDb<ProductOrderItem> onGetTmpInstance() {
		return this.mTmpInstance;
	}

	@Override
	protected String onMakeCreateTableQuery(String tableName) {
		return String.format("create table %s(" +
				"prod_order_id number(6) primary key, " +
				"order_datetime timestamp not null, " +
				"cust_id number(6) not null, " +
				"used_point number(7), " +
				"earned_point number(7) " +
				")", tableName);
	}

	@Override
	protected ProductOrderItem onResultSetToItem(ResultSet rs) {
		return new ProductOrderItem(
				this.getInt(rs, "prod_order_id"),
				this.getTimestamp(rs, "order_datetime").toLocalDateTime(),
				this.getInt(rs, "cust_id"),
				this.getInt(rs, "used_point"),
				this.getInt(rs, "earned_point"));
	}

	@Override
	protected String[] onMakeFieldsetAndValues(ProductOrderItem item) {

		String fieldset = "prod_order_id, order_datetime, cust_id, used_point, earned_point";
		String values = String.format("%d, '%s', %d, %d, %d",
				item.getId(), item.getOrderDateTimeSqlStr(), item.getCustId(), item.getUsedPoint(),
				item.getEarnedPoint());
		return new String[] { fieldset, values };
	}

	@Override
	protected String onMakeSet(ProductOrderItem item, String[] fieldset) throws Exception {
		String s = "";

		for (var f : fieldset) {
			if (f.equals("prod_order_id"))
				s += String.format("prod_order_id = %d, ", item.getId());
			else if (f.equals("order_datetime"))
				s += String.format("order_datetime = '%s', ", item.getOrderDateTimeSqlStr());
			else if (f.equals("cust_id"))
				s += String.format("cust_id = %d, ", item.getCustId());
			else if (f.equals("used_point"))
				s += String.format("used_point = %d, ", item.getUsedPoint());
			else if (f.equals("earned_point"))
				s += String.format("earned_point = %d, ", item.getEarnedPoint());
			else {
				String msg = String.format("[ProductOrdersManager.onMakeSet()]" +
						" Invalid field name('%s')!", f);
				throw new Exception(msg);
			}
		}

		// 마지막 comma와 space 제거
		s = s.substring(0, s.length() - 2);

		return s;
	}

}
