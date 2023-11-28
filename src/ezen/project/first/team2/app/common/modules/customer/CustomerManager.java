////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_105900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.customer;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManager;

public class CustomerManager extends ListManager<CustomerItem> {
	// -------------------------------------------------------------------------

	private static CustomerManager mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private CustomerManager() {
	}

	// 인스턴스 얻기
	public static CustomerManager getInstance() {
		if (mInstance == null) {
			mInstance = new CustomerManager();

			try {
				// 비회원 객체 추가
				mInstance.add(CustomerItem.GUEST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 포인트 업데이트
	public void updatePoint(int custId, int point) throws Exception {
		var ci = this.findById(custId);

		// 비회원인 경우 예외 발생
		if (ci == null) {
			String msg = String.format("[CustomerManager.updatePoint()]"
					+
					" Invalid custId(%d)!",
					custId);
			throw new Exception(msg);
		}

		ci.setPoint(point);
		this.updateById(ci.getId(), null);
	}

	// -------------------------------------------------------------------------

	public CustomerItem findByName(String name) {
		return this.find((ci, idx) -> ci.getName().equals(name));
	}

	public CustomerItem findByBirthday(LocalDate date) {
		return this.find((ci, idx) -> ci.getBirthday().equals(date));
	}

	public CustomerItem findByPhoneNumber(String phoneNumber) {
		return this.find((ci, idx) -> ci.getPhoneNumber().equals(phoneNumber));
	}

	//

	public List<CustomerItem> findItemsByName(String name) {
		return this.findItems((ci, idx) -> ci.getName().contains(name));
	}

	public List<CustomerItem> findItemsByBirthday(LocalDate date) {
		return this.findItems((ci, idx) -> ci.getBirthday().equals(date));
	}

	public List<CustomerItem> findItemsByPhoneNumber(String phoneNumber) {
		return this.findItems((ci, idx) -> ci.getPhoneNumber().contains(phoneNumber));
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(CustomerItem item) {
		if (this.findByPhoneNumber(item.getPhoneNumber()) != null) {
			String msg = String.format("[CustomerManager.onAdd()]" +
					" You have same phone number(%s)!", item.getPhoneNumber());

			return msg;
		}

		return super.onAdd(item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id,
			CustomerItem oldItem, CustomerItem newItem) {
		return super.onUpdateById(id, oldItem, newItem);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) {
		return super.onDeleteById(id);
	}
}
