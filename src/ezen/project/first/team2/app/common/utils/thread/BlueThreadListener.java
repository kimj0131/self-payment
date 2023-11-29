package ezen.project.first.team2.app.common.utils.thread;

public interface BlueThreadListener {
	// 호출 스레드(ex, 메인 스레드)에서 호출
	boolean onStart(BlueThread sender, Object param);

	// 워커 스레드에서 호출
	// return: true => continue, false => break
	boolean onRun(BlueThread sender, Object param);

	// 호출 스레드(ex, 메인 스레드)에서 호출
	// interrupted: true => 요청에 의한 종료, false => 스스로 종료
	void onStop(BlueThread sender, Object param, boolean interrupted);
}
