////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_111000] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

public interface DBConnectorListener {
	// --------------------------------------------------------------------------

	// 커넥션 중일 때
	public void onConnecting(DBConnector sender);

	// 커넥션 되었을 때
	public void onConnected(DBConnector sender);

	// 커넥션에 실패했을 때
	public void onConnectionFailure(DBConnector sender, String reason);

	// 디스커넥션 되었을 때
	public void onDisconnected(DBConnector sender);

	// --------------------------------------------------------------------------

}
