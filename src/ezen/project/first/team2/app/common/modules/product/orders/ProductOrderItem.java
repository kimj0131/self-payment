////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.orders;

import java.time.LocalDateTime;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class ProductOrderItem extends ListItem {
	// -------------------------------------------------------------------------

	// 주문일시
	private LocalDateTime mOrderDateTime;

	// 고객 ID
	private int mCustId;

	// 사용한 포인트
	private int mUsedPoint;

	// 적립된 포인트
	private int mEarnedPoint;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductOrderItem(int id, LocalDateTime orderDateTime, int custId,
			int usedPoint, int earnedPoint) {
		this.setValues(id, orderDateTime, custId, usedPoint, earnedPoint);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 설정
	public void setValues(int id, LocalDateTime orderDateTime, int custId,
			int usedPoint, int earnedPoint) {
		this.mId = id;

		this.mOrderDateTime = orderDateTime;
		this.mCustId = custId;
		this.mUsedPoint = usedPoint;
		this.mEarnedPoint = earnedPoint;
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

	// 원래 금액 얻기
	public int getOrgTotalPrice() {
		return -1;
	}

	public int getTotalPrice() {
		return -1;
	}

	//

}
