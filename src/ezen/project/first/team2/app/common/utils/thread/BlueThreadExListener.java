////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_115100] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils.thread;

public interface BlueThreadExListener extends BlueThreadListener {
	public void onReceviedMessage(BlueThreadEx sender, Object param, BlueThreadExMessage msg);
}