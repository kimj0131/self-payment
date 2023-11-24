////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231122WED_142000]
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

public interface ListActionListener<T extends ListItem> {

	public void onInitialized(ListManager<T> mngr);

	public void onDeinitializing(ListManager<T> mngr);

	// --------------------------------------------------------------------------

	public void onAdded(ListManager<T> mngr, T item);

	public void onUpdated(ListManager<T> mngr, T oldItem, T newItem);

	public void onDeleted(ListManager<T> mngr, T item);
}
