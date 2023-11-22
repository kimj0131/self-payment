////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_105900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.customer;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class CustomerManagerMem extends ListManagerMem<CustomerItem>
		implements CustomerManagerFind {
	// -------------------------------------------------------------------------

	private static CustomerManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private CustomerManagerMem() {
	}

	// 인스턴스 얻기
	public static CustomerManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new CustomerManagerMem();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	@Override
	public CustomerItem findByName(String name) throws Exception {
		return this.find((ci, idx) -> ci.getName().equals(name));
	}

	@Override
	public List<CustomerItem> findByBirthday(LocalDate date) throws Exception {
		return this.findItems((ci, idx) -> ci.getBirthday().equals(date));
	}

	@Override
	public CustomerItem findByPhoneNumber(String phoneNumber) throws Exception {
		return this.find((ci, idx) -> ci.getPhoneNumber().equals(phoneNumber));
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(CustomerItem item) throws Exception {
		if (this.findByPhoneNumber(item.getPhoneNumber()) != null) {
			String msg = String.format("[CustomerManagerMem.onAdd()]" +
					" You have same phone number(%s)!", item.getPhoneNumber());

			return msg;
		}

		return super.onAdd(item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id, CustomerItem info) throws Exception {
		return super.onUpdateById(id, info);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) throws Exception {
		return super.onDeleteById(id);
	}
}
