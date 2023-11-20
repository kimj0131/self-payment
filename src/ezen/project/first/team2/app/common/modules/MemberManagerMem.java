////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_105900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules;

import java.util.ArrayList;
import java.util.List;

public class MemberManagerMem extends MemberManager {
	// -------------------------------------------------------------------------

	private static MemberManagerMem mInstance = null;

	private List<MemberInfo> mMemberList = new ArrayList<>();

	// -------------------------------------------------------------------------

	private MemberManagerMem() {
	}

	public static MemberManagerMem getInstance() {
		if (MemberManagerMem.mInstance == null) {
			MemberManagerMem.mInstance = new MemberManagerMem();
		}

		return MemberManagerMem.mInstance;
	}

	// -------------------------------------------------------------------------

	@Override
	protected void onInit() {
		System.out.println("[MembermanagerMem.onInit()]");
	}

	@Override
	protected void onDeinit() {
		System.out.println("[MembermanagerMem.onDeinit()]");
	}

	@Override
	protected void onAdd(MemberInfo info) {
		this.mMemberList.add(info);
	}

	@Override
	protected void onDeleteById(int id) {
		this.mMemberList.removeIf(info -> info.getId() == id);
	}

	@Override
	protected void onUpdateById(int id, MemberInfo info) {
		for (MemberInfo _info : this.mMemberList) {
			if (_info.getId() == id) {
				_info.setValuesFrom(info);

				return;
			}
		}
	}

	@Override
	protected boolean onIsValidId(int id) {
		// [SGLEE:20231120MON_112400] containsInfo() 이런 메소드 없나?
		for (MemberInfo info : this.mMemberList) {
			if (info.getId() == id)
				return true;
		}

		return false;
	}

	@Override
	protected int onGetCount() {
		return this.mMemberList.size();
	}

	@Override
	protected void onIterate(Iterator iterator) {
		for (MemberInfo info : this.mMemberList) {
			if (!iterator.onGetItem(info))
				break;
		}
	}
}
