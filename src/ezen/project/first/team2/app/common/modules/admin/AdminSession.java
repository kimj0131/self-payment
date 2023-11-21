////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_094800] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.admin;

public class AdminSession {
	public enum LoginResult {
		Ok, InvalidId, InvalidPassword
	}

	private static AdminSession mInstance = null;

	private String mId = "";
	private String mPassword = "";
	private LoginResult mLoginResult = null;

	// 생성자
	private AdminSession() {
	}

	public static AdminSession getInstance() {
		if (AdminSession.mInstance == null) {
			AdminSession.mInstance = new AdminSession();
			AdminSession.mInstance.loadInfo();
		}

		return AdminSession.mInstance;
	}

	// 로그인
	public LoginResult login(String id, String password) {
		if (this.mId.equals(id) && this.mPassword.equals(password))
			this.mLoginResult = LoginResult.Ok;
		else if (!this.mId.equals(id))
			this.mLoginResult = LoginResult.InvalidId;
		else if (!this.mPassword.equals(password))
			this.mLoginResult = LoginResult.InvalidPassword;

		return this.mLoginResult;
	}

	// 로그아웃
	public void logout() {
		this.mLoginResult = null;
	}

	// 로그인 결과 얻기
	public LoginResult getLoginResult() {
		return this.mLoginResult;
	}

	// 로그인 상태 확인
	public boolean isLoginOk() {
		return this.mLoginResult == LoginResult.Ok;
	}

	public void loadInfo() {
		this.mId = "admin";
		this.mPassword = "qwer1234!@#$";
	}

	public void saveInfo(String id, String password) {
		if (!id.isEmpty()) {
			this.mId = id;
		}

		if (!password.isEmpty()) {
			this.mPassword = password;
		}
	}
}
