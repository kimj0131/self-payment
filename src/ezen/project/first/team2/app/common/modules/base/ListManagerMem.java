////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231121TUE_142900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.util.ArrayList;
import java.util.List;

public class ListManagerMem<T extends ListItem> extends ListManager<T> {
	// -------------------------------------------------------------------------

	private List<T> mList = new ArrayList<>();

	// -------------------------------------------------------------------------

	// 생성자
	public ListManagerMem() {
	}

	// -------------------------------------------------------------------------

	@Override
	protected void onInit() {
	}

	@Override
	protected void onDeinit() {
	}

	@Override
	protected int onGetNextID() {
		// [SGLEE:20231121TUE_140900] Collections.max() API를 사용하면 될 것 같기도 한데..
		int maxId = -1;
		for (var i : this.mList) {
			if (i.getId() > maxId)
				maxId = i.getId();
		}

		return maxId + 1;
	}

	@Override
	protected void onAdd(T info) {
		this.mList.add(info);
	}

	@Override
	protected void onUpdateById(int id, T info) {
		for (var _info : this.mList) {
			if (_info.getId() == id) {
				_info.setValuesFrom(info);

				return;
			}
		}
	}

	@Override
	protected void onDeleteById(int id) {
		this.mList.removeIf(info -> info.getId() == id);
	}

	@Override
	protected boolean onIsDuplicatedId(int id) {
		for (var info : this.mList) {
			if (info.getId() == id)
				return true;
		}

		return false;
	}

	@Override
	protected int onGetCount() {
		return this.mList.size();
	}

	@Override
	protected void onIterate(Iterator<T> iterator) {
		for (var info : this.mList) {
			if (!iterator.onGetItem(info))
				break;
		}
	}

	@Override
	protected T onFind(Iterator<T> iterator) {
		for (var info : this.mList) {
			if (iterator.onGetItem(info))
				return info;
		}

		return null;
	}
}
