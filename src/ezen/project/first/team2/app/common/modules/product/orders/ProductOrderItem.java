////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.orders;

import java.time.LocalDateTime;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailItem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManagerMem;
import ezen.project.first.team2.app.common.utils.TimeUtils;
import ezen.project.first.team2.app.common.utils.UnitUtils;

public class ProductOrderItem extends ListItem {
	// -------------------------------------------------------------------------

	// 포인트 적립률 (1%)
	final double POINT_RATE = 0.01;

	// -------------------------------------------------------------------------

	// 주문일시
	private LocalDateTime mOrderDateTime;

	// 고객 ID
	private int mCustId;

	// 사용한 포인트
	private int mUsedPoint;

	// 적립된 포인트
	private int mEarnedPoint = 0;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductOrderItem(int id, LocalDateTime orderDateTime, int custId,
			int usedPoint) {
		this.setValues(id, orderDateTime, custId, usedPoint);
	}

	// 생성자 => 고객 ID : 0(비회원), 사용할 포인트: 0
	public ProductOrderItem(int id, LocalDateTime orderDateTime) {
		this.setValues(id, orderDateTime, CustomerItem.GUEST_ID, 0);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 설정
	public void setValues(int id, LocalDateTime orderDateTime, int custId,
			int usedPoint) {
		this.mId = id;

		this.mOrderDateTime = orderDateTime;
		this.mCustId = custId;
		this.mUsedPoint = usedPoint;
	}

	// -------------------------------------------------------------------------

	// 구매자 설정. 상품을 구입한 후 결제 전에 설정한다.
	public void setCustId(int custId) {
		//
	}

	// 사용할 포인트 설정
	public void setUsedPoint(int point) throws Exception {
		if (this.getCustId() == CustomerItem.GUEST_ID) {
			// 예외 발생!!
		}

		//
	}

	// 적립된 포인트 업데이트. 최종 금액을 기준으로 계산되므로 나중에 호출해야 한다.
	public void updateEarnedPoint() throws Exception {
		if (this.getCustId() == CustomerItem.GUEST_ID) {
			// 예외 발생!!
		}

		this.mEarnedPoint = (int) Math.floor(this.getFinalTotalPrice() * POINT_RATE);
	}

	// -------------------------------------------------------------------------

	// 주문일시 얻기
	public LocalDateTime getOrderDateTime() {
		return this.mOrderDateTime;
	}

	// 주문일시 문자열 얻기
	public String getOrderDateTimeStr() {
		return TimeUtils.localDateTimeToStr(this.getOrderDateTime());
	}

	// 고객 ID 얻기
	public int getCustId() {
		return this.mCustId;
	}

	// 사용한 포인트 얻기
	public int getUsedPoint() {
		return this.mUsedPoint;
	}

	// 적된된 포인트 얻기
	public int getEarnedPoint() {
		return this.mEarnedPoint;
	}

	//

	// 원래 금액 얻기 => 상세 구매 내역 [원래 금액] 합계
	public int getOrgTotalPrice() throws Exception {
		var items = this.getProdOrderDetailItems();
		int sum = 0;
		for (var i : items) {
			sum += i.getOrgPrice();
		}

		return sum;
	}

	// 최종 금액 얻기 => 상세 구매 내역 [최종 합계] - 사용한 포인트
	public int getFinalTotalPrice() throws Exception {
		var items = this.getProdOrderDetailItems();
		int sum = 0;
		for (var i : items) {
			sum += i.getFinalPrice();
		}

		return sum - this.getUsedPoint();
	}

	//

	// 고객 아이템 얻기
	public CustomerItem getCustItem() throws Exception {
		var custMngr = CustomerManagerMem.getInstance();
		return custMngr.findById(this.getCustId());
	}

	//

	// 상세 구매 내역 아이템 얻기
	public List<ProductOrderDetailItem> getProdOrderDetailItems() throws Exception {
		var prodOrderDetailsMngr = ProductOrderDetailsManagerMem.getInstance();
		return prodOrderDetailsMngr.getProdOrderDetailItemsByProdOrderId(this.getId());
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		try {
			return String.format("id:%06d, order_datetime:%s, cust_id:%06d, " +
					"used_point:%8s, earned_point:%8s, " +
					"org_total_price:%8s, final_total_price:%8s ",
					this.getId(), TimeUtils.localDateTimeToStr(this.getOrderDateTime()),
					this.getCustId(),
					UnitUtils.numToCurrencyStr(this.getUsedPoint()),
					UnitUtils.numToCurrencyStr(this.getEarnedPoint()),
					UnitUtils.numToCurrencyStr(this.getOrgTotalPrice()),
					UnitUtils.numToCurrencyStr(this.getFinalTotalPrice()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		try {
			var poi = (ProductOrderItem) item;
			this.setValues(poi.getId(), poi.getOrderDateTime(), poi.getCustId(), poi.mUsedPoint);
			this.updateEarnedPoint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
