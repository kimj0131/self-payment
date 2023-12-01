////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_111000] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

public interface z_thread__DBConnectorListener {
	// --------------------------------------------------------------------------

	// 커넥션 중일 때
	public void onConnecting(z_thread__DBConnector sender);

	// 커넥션 되었을 때
	public void onConnected(z_thread__DBConnector sender);

	// 커넥션에 실패했을 때
	public void onConnectionFailure(z_thread__DBConnector sender, String reason);

	// 디스커넥션 되었을 때
	public void onDisconnected(z_thread__DBConnector sender);

	// --------------------------------------------------------------------------

}
