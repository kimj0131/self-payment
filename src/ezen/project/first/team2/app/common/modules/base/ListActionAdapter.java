////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231122WED_142400]
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.util.List;

public class ListActionAdapter<T extends ListItem> implements ListActionListener<T> {

	@Override
	public void onAdded(ListManager<T> mngr, T item) {
	}

	@Override
	public void onUpdated(ListManager<T> mngr, T oldItem, T newItem) {
	}

	@Override
	public void onDeleted(ListManager<T> mngr, T item) {
	}

	@Override
	public void onDeleteItems(ListManager<T> mngr, List<Integer> idList) {
	}

	@Override
	public void onDeletedItems(ListManager<T> mngr, List<Integer> idList) {
	}

	@Override
	public void onDeleteAllItems(ListManager<T> mngr) {
	}

	@Override
	public void onDeletedAllItems(ListManager<T> mngr) {
	}

}
