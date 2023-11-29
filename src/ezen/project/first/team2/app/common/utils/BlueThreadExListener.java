////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_115100] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils;

public interface BlueThreadExListener extends BlueThread.Listener {
	public void onReceviedMessage(BlueThreadEx sender, Object param, BlueThreadExMessage msg);
}