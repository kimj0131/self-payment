////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231122WED_142400]
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

public class ListActionAdapter<T extends ListItem> implements ListActionListener<T> {

	@Override
	public void onInitialized(ListManager<T> mngr) {
	}

	@Override
	public void onDeinitializing(ListManager<T> mngr) {
	}

	// --------------------------------------------------------------------------

	@Override
	public void onAdded(ListManager<T> mngr, T item) {
	}

	@Override
	public void onUpdated(ListManager<T> mngr, T oldItem, T newItem) {
	}

	@Override
	public void onDeleted(ListManager<T> mngr, T item) {
	}

}
