////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_111000] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

public interface DBTableActionListener {
	// --------------------------------------------------------------------------

	// 커넥션 중일 때
	public void onConnecting(DBTable table);

	// 커넥션 되었을 때
	public void onConnected(DBTable table);

	// 커넥션에 실패했을 때
	public void onConnectionFailure(DBTable table, String reason);

	// 디스커넥션 되었을 때
	public void onDisconnected();

	// --------------------------------------------------------------------------

}
