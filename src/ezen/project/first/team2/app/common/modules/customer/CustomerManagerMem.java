////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_105900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.customer;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class CustomerManagerMem extends ListManagerMem<CustomerInfo> {
	// -------------------------------------------------------------------------

	private static CustomerManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	private CustomerManagerMem() {
	}

	public static CustomerManagerMem getInstance() {
		if (CustomerManagerMem.mInstance == null) {
			CustomerManagerMem.mInstance = new CustomerManagerMem();
		}

		return CustomerManagerMem.mInstance;
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(CustomerInfo info) {
		return super.onAdd(info);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id, CustomerInfo info) {
		return super.onUpdateById(id, info);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) {
		return super.onDeleteById(id);
	}
}
