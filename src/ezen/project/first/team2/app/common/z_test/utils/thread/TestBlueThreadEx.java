package ezen.project.first.team2.app.common.z_test.utils.thread;

import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.common.utils.thread.BlueThread;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadEx;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadExListener;
import ezen.project.first.team2.app.common.utils.thread.BlueThreadExMessage;

public class TestBlueThreadEx {
	public static void main(String[] args) {
		System.out.println("[main] start");

		final int CMD_STR = 0;
		final int CMD_INT = 1;
		final int CMD_BOOL = 2;
		final int CMD_OBJ = 3;

		try {
			var t = new BlueThreadEx(new BlueThreadExListener() {

				@Override
				public boolean onStart(BlueThread sender, Object param) {
					System.out.println("[worker] start");

					return true;
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
					// System.out.println(msg.toString());
					switch (msg.getCmd()) {
						case CMD_STR:
							System.out.printf("CMD_STR => %s \n", msg.getParam());
							break;

						case CMD_INT:
							System.out.printf("CMD_INT => %d \n", msg.getParam());
							break;

						case CMD_BOOL:
							System.out.printf("CMD_BOOL => %s \n", msg.getParam());
							break;

						case CMD_OBJ:
							System.out.printf("CMD_OBJ => %s \n", msg.getParam());
							break;
					}
				}

			});

			t.start();

			SystemUtils.sleep(1 * 1000);

			t.sendMessage(new BlueThreadExMessage(CMD_STR, "String"));
			SystemUtils.sleep(200);
			t.sendMessage(new BlueThreadExMessage(CMD_INT, 1234));
			SystemUtils.sleep(200);
			t.sendMessage(new BlueThreadExMessage(CMD_BOOL, true));
			SystemUtils.sleep(200);
			t.sendMessage(new BlueThreadExMessage(CMD_OBJ, new Object() {
				@Override
				public String toString() {
					return "Object.toString()";
				}
			}));

			SystemUtils.sleep(2000);

			t.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("[main] end");
	}
}
