////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.order_details;

import java.sql.ResultSet;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.base.ListManagerDb;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManager;
import ezen.project.first.team2.app.common.z_test.modules.base.EmployeeItem;

public class ProductOrderDetailsManager extends ListManagerDb<ProductOrderDetailItem> {
	
	private static final String TABLE_NAME = "PRODUCT_ORDER_DETAILS";
	
	// -------------------------------------------------------------------------

	private static ProductOrderDetailsManager mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductOrderDetailsManager() {
		super(TABLE_NAME);
	}

	// 인스턴스 얻기
	public static ProductOrderDetailsManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductOrderDetailsManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 상품 구매 아이템 얻기
	public ProductOrderItem getProdOrderItem(int id) {
		var prodOrderMngr = ProductOrdersManager.getInstance();
		return prodOrderMngr.findById(id);
	}

	// 상품 아이템 얻기
	public ProductItem getProdItem(int id) {
		var prodMngr = ProductManager.getInstance();
		return prodMngr.findById(id);
	}

	// 상품 할인 아이템 얻기
	public ProductDiscountItem getProdDiscntItem(int id) {
		var prodDiscntMngr = ProductDiscountsManager.getInstance();
		return prodDiscntMngr.findById(id);
	}

	//

	// 상품 구매 ID, 상품 ID 기준으로 아이템 얻기
	public ProductOrderDetailItem getItemByProdOrderIdAndProdId(int prodOrderId, int prodId) {
		return find((_item, _idx) -> _item.getProdOrderId() == prodOrderId && _item.getProdId() == prodId);
	}

	// 상품 구매 ID 기준으로 상세 구매 내역 아이템 얻기
	public List<ProductOrderDetailItem> getItemsByProdOrderId(int prodOrderId) {
		return this.findItems((item, idx) -> item.getProdOrderId() == prodOrderId);
	}

	// -------------------------------------------------------------------------

	// 상품 수량 업데이트
	public void updateQuantity(int prodOrderId, int prodId, int quantity) throws Exception {
		// 상품 구매 ID와 제품 ID가 유효하지 않은 경우
		var poi = this.find((item, idx) -> item.getProdOrderId() == prodOrderId && item.getProdId() == prodId);
		if (poi == null) {
			String msg = String.format("[ProductOrderDetailsManager.updateQuantity()]"
					+
					" Invalid prodOrderId(%d) and/or prodId(%d)!",
					prodOrderId, prodId);
			throw new Exception(msg);
		}

		poi.setQuantity(quantity);
		this.updateById(poi.getId(), poi);
	}
	
	// -------------------------------------------------------------------------

	@Override
	protected String onMakeCreateTableQuery(String tableName) {
		return String.format("create table %s(" +
				"prod_order_detail_id number(6) primary key, " +
				"prod_order_id number(6) not null, " +
				"prod_id number(6) not null, " +
				"prod_discnt_id number(6) not null, " +
				"quantity number(4) not null" +
				")", tableName);
	}

	@Override
	protected ProductOrderDetailItem onResultSetToItem(ResultSet rs) {
		ProductOrderDetailItem prodODI = null;
		try {
			prodODI = new ProductOrderDetailItem(
					this.getInt(rs, "prod_order_detail_id"),
					this.getInt(rs, "prod_order_id"),
					this.getInt(rs, "prod_id"),
					this.getInt(rs, "prod_discnt_id"),
					this.getInt(rs, "quantity")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prodODI;
	}

	@Override
	protected String[] onMakeFieldsetAndValues(ProductOrderDetailItem item) {

		String fieldset = "prod_order_detail_id, prod_order_id, prod_id, prod_discnt_id, quantity";
		String values = String.format("%d, %d, %d, %d, %d", item.getId(), item.getProdOrderId(), item.getProdId(),
				item.getProdDiscntId(), item.getQuantity());

		return new String[] { fieldset, values };
	}

	@Override
	protected String onMakeSet(ProductOrderDetailItem item, String[] fieldset) throws Exception {
		String s = "";

		for (var f : fieldset) {
			if (f.equals("prod_order_detail_id"))
				s += String.format("prod_order_detail_id = %d, ", item.getId());
			else if (f.equals("prod_order_id"))
				s += String.format("prod_order_id = %d, ", item.getProdOrderId());
			else if (f.equals("prod_id"))
				s += String.format("prod_id = %d, ", item.getProdId());
			else if (f.equals("prod_discnt_id"))
				s += String.format("prod_discnt_id = %d, ", item.getProdDiscntId());
			else if (f.equals("quantity"))
				s += String.format("quantity = %d, ", item.getQuantity());
			else {
				String msg = String.format("[ProductOrderDetailsManager.onMakeSet()]" +
						" Invalid field name('%s')!", f);
				throw new Exception(msg);
			}
		}

		// 마지막 comma와 space 제거
		s = s.substring(0, s.length() - 2);

		return s;
	}


}
