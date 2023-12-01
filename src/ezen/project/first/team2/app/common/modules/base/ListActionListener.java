////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231122WED_142000]
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.util.List;

public interface ListActionListener<T extends ListItem> {

	// 아이템이 추가되었을 때
	public void onAdded(ListManager<T> mngr, T item);

	// 아이템이 업데이트되었을 때
	public void onUpdated(ListManager<T> mngr, T oldItem, T newItem);

	// 아이템이 삭제되었을 때
	public void onDeleted(ListManager<T> mngr, T item);

	// 아이템들이 삭제될 때 (삭제 전)
	public void onDeleteItems(ListManager<T> mngr, List<Integer> idList);

	// 아이템들이 삭제되었을 때 (삭제 후)
	public void onDeletedItems(ListManager<T> mngr, List<Integer> idList);

	// 모든 아이템들이 삭제될 때 (삭제 전)
	public void onDeleteAllItems(ListManager<T> mngr);

	// 모든 아이템들이 삭제되었을 때 (삭제 후)
	public void onDeletedAllItems(ListManager<T> mngr);
}
