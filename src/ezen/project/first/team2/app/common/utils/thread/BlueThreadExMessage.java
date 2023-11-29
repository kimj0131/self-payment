////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_115500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils.thread;

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

	@Override
	public String toString() {
		return String.format("cmd: 0x%04X(%06d), param: %s", this.getCmd(), this.getCmd(), this.getParam());
	}
}
