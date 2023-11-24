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
	protected String onAdd(T item) throws Exception {
		this.mList.add(item);

		return "";
	}

	@Override
	protected String onUpdateById(int id, T oldItem, T newItem) throws Exception {
		for (var item : this.mList) {
			if (item.getId() == id) {
				item.setValuesFrom(newItem);

				return "";
			}
		}

		return "";
	}

	@Override
	protected String onDeleteById(int id) throws Exception {
		this.mList.removeIf(item -> item.getId() == id);

		return "";
	}

	@Override
	protected String onDeleteItems(Iterator<T> iterator) throws Exception {
		// int idx = 0;
		// for (var i : this.mList) {
		// if (iterator.onGetItem(i, idx++))
		// this.deleteById(i.getId());
		// }

		int idx = 0;
		List<Integer> ids = new ArrayList<>();
		for (var i : this.mList) {
			if (iterator.onGetItem(i, idx))
				ids.add(idx++);
		}

		for (var id : ids) {
			this.deleteById(id);
		}

		return "";
	}

	@Override
	protected boolean onIsDuplicatedId(int id) {
		for (var item : this.mList) {
			if (item.getId() == id)
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
		int i = 0;
		for (var item : this.mList) {
			if (!iterator.onGetItem(item, i++))
				break;
		}
	}

	@Override
	protected T onFind(Iterator<T> iterator) {
		int i = 0;
		for (var item : this.mList) {
			if (iterator.onGetItem(item, i++))
				return item;
		}

		return null;
	}

	@Override
	protected List<T> onFindItems(Iterator<T> iterator) {
		List<T> items = new ArrayList<>();

		int i = 0;
		for (var item : this.mList) {
			if (iterator.onGetItem(item, i++))
				items.add(item);
		}

		return items;
	}

	@Override
	protected T onFindById(int id) {
		for (var item : this.mList) {
			if (item.getId() == id)
				return item;
		}

		return null;
	}
}
