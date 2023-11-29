////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_115500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils;

public class BlueThreadExMessage {

	private int mCmd;
	private Object mParam;

	public BlueThreadExMessage(int cmd, Object param) {
		this.mCmd = cmd;
		this.mParam = param;
	}

	public int getCmd() {
		return this.mCmd;
	}

	public Object getParam() {
		return this.mParam;
	}
}
