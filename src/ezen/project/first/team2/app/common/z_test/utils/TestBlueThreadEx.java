package ezen.project.first.team2.app.common.z_test.utils;

import ezen.project.first.team2.app.common.utils.BlueThread;
import ezen.project.first.team2.app.common.utils.BlueThreadEx;
import ezen.project.first.team2.app.common.utils.BlueThreadExListener;
import ezen.project.first.team2.app.common.utils.BlueThreadExMessage;
import ezen.project.first.team2.app.common.utils.SystemUtils;

public class TestBlueThreadEx {
	public static void main(String[] args) {
		System.out.println("[main] start");

		try {
			var t = new BlueThreadEx(new BlueThreadExListener() {

				@Override
				public void onStart(BlueThread sender, Object param) {
					System.out.println("[worker] start");
				}

				@Override
				public boolean onRun(BlueThread sender, Object param) {
					return true;
				}

				@Override
				public void onStop(BlueThread sender, Object param, boolean interrupted) {
					System.out.println("[worker] end");
				}

				@Override
				public void onReceviedMessage(BlueThreadEx sender, Object param, BlueThreadExMessage msg) {
					System.out.println(msg.toString());
				}

			});

			t.start();

			SystemUtils.sleep(1 * 1000);

			t.sendMessage(new BlueThreadExMessage(100, "메시지"));

			t.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("[main] end");
	}
}
