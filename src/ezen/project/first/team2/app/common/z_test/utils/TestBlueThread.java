////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_001600] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.z_test.utils;

import ezen.project.first.team2.app.common.utils.BlueThread;

public class TestBlueThread {
	public static void main(String[] args) {
		System.out.println("[main] start");

		int[] nums = { 1000, 2000, 3000, 4000 };

		// 스레드 객체 생성
		BlueThread t = new BlueThread(new BlueThread.Listener() {
			private int mIndex = 0;

			@Override
			public void onStart(BlueThread sender, Object param) {
				System.out.println("[worker.onStart()] ");

				// int[] nums = (int[]) param;
				// for (int i = 0; i < nums.length; i++) {
				// nums[i] = (i + 1) * 1000;
				// }
			}

			@Override
			public boolean onRun(BlueThread sender, Object param) {
				int[] nums = (int[]) param;
				System.out.printf("[worker.onRun()]  " +
						"index:%d, value:%d \n", this.mIndex, nums[this.mIndex]);

				// 데이터 수정
				nums[this.mIndex] += 10;

				return ++this.mIndex < nums.length;
			}

			@Override
			public void onStop(BlueThread sender, Object param, boolean interrupted) {
				System.out.printf("[worker.onStop()] interrupted:%s \n", interrupted);
			}
		}, 1 * 1000, nums);

		// 스레드 시작
		try {
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 10초 뒤 t 스레드 종료
		// SystemUtils.sleep(10 * 1000);
		// try {
		// t.stop();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 스레드가 종료될 때까지 기다린다
		try {
			t.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.printf("nums => ");
		for (int num : nums) {
			System.out.printf("%d ", num);
		}
		System.out.println();

		System.out.println("[main] end");
	}
}
