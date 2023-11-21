////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_105900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagerMem extends ListManager<CustomerInfo> {
	// -------------------------------------------------------------------------

	private static CustomerManagerMem mInstance = null;

	private List<CustomerInfo> mCustList = new ArrayList<>();

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

	@Override
	protected void onInit() {
		System.out.println("[CustomerManagerMem.onInit()]");
	}

	@Override
	protected void onDeinit() {
		System.out.println("[CustomerManagerMem.onDeinit()]");
	}

	@Override
	protected int onGetNextID() {
		// [SGLEE:20231121TUE_140900] Collections.max() API를 사용하면 될 것 같기도 한데..
		int maxId = -1;
		for (var i : this.mCustList) {
			if (i.getId() > maxId)
				maxId = i.getId();
		}

		return maxId + 1;
	}

	@Override
	protected void onAdd(CustomerInfo info) {
		this.mCustList.add(info);
	}

	@Override
	protected void onDeleteById(int id) {
		this.mCustList.removeIf(info -> info.getId() == id);
	}

	@Override
	protected void onUpdateById(int id, CustomerInfo info) {
		for (var _info : this.mCustList) {
			if (_info.getId() == id) {
				_info.setValuesFrom(info);

				return;
			}
		}
	}

	@Override
	protected boolean onIsDuplicatedId(int id) {
		for (var info : this.mCustList) {
			if (info.getId() == id)
				return true;
		}

		return false;
	}

	@Override
	protected int onGetCount() {
		return this.mCustList.size();
	}

	@Override
	protected void onIterate(Iterator<CustomerInfo> iterator) {
		for (var info : this.mCustList) {
			if (!iterator.onGetItem(info))
				break;
		}
	}
}
