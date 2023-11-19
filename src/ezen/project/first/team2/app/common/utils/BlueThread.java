////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:2023119SUN_235300] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public class BlueThread {
	public static final int DEFAULT_SLEEP_IN_MILLIS = 10;

	public static interface Listener {
		// 호출 스레드(ex, 메인 스레드)에서 호출
		void onStart(BlueThread sender, Object param);

		// 워커 스레드에서 호출
		// return: true => continue, false => break
		boolean onRun(BlueThread sender, Object param);

		// 호출 스레드(ex, 메인 스레드)에서 호출
		// interrupted: true => 요청에 의한 종료, false => 스스로 종료
		void onStop(BlueThread sender, Object param, boolean interrupted);
	}

	private Thread mThread = null;
	private Listener mListener = null;
	private long mSleepInMillis = 0;

	private Object mParam = null;
	private final AtomicBoolean mIsRunning = new AtomicBoolean(false);

	// 생성자
	public BlueThread(Listener listener, Object param, int sleepInMillis) {
		this.mListener = listener;
		this.mParam = param;
		this.mSleepInMillis = sleepInMillis;
	}

	// 생성자
	public BlueThread(Listener listener, Object param) {
		this(listener, param, DEFAULT_SLEEP_IN_MILLIS);
	}

	// 스레드 시작
	public void start() throws Exception {
		if (this.isRunning()) {
			String msg = String.format(
					"[BlueThread.start()] " +
							"Thread is already running state!");
			throw new Exception(msg);
		}

		this.mThread = new Thread(() -> {
			this.mListener.onStart(this, this.mParam);

			while (mIsRunning.get()) {
				if (!this.mListener.onRun(this, this.mParam)) {
					this.mIsRunning.set(true);

					this.mListener.onStop(this, this.mParam, false);

					break;
				}

				SystemUtils.sleep(this.mSleepInMillis);
			}

			this.mIsRunning.set(false);
		});

		this.mIsRunning.set(true);
		this.mThread.start();
	}

	// 스레드 종료
	// 스레드 내에서 종료를 하려면 Listener.onRun() 메소드에서 false를 리턴한다.
	public void stop() throws Exception {
		if (!isRunning()) {
			String msg = String.format(
					"[BlueThread.stop()] " +
							"Thread is not running state!");
			throw new Exception(msg);
		}

		if (Thread.currentThread() == mThread) {
			String msg = String.format(
					"[BlueThread.stop()] " +
							"It can't stop its self!");
			throw new Exception(msg);
		}

		this.mIsRunning.set(false);

		this.mThread.join();
		this.mListener.onStop(this, mParam, true);
	}

	// 스레드 실행 여부 확인
	public boolean isRunning() {
		return this.mIsRunning.get();
	}

	// 스레드 종료 대기
	public void join() throws Exception {
		if (!isRunning()) {
			String msg = String.format(
					"[BlueThread.join()] " +
							"Thread is not running state!");
			throw new Exception(msg);
		}

		// 자기 자신이 wait를 하게 되면 무한 루프에 빠진다!
		if (Thread.currentThread() == this.mThread) {
			String msg = String.format(
					"[BlueThread.join()] " +
							"It can't join(wait) its self!");
			throw new Exception(msg);
		}

		this.mThread.join();
	}
}
