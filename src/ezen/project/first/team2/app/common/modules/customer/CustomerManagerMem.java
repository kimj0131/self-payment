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
}
