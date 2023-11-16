////////////////////////////////////////////////////////////////////////////////
// [SGLEE:20231112SUN_233800] Created
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.launcher;

import ezen.project.first.team2.app.common.StatusManager;

public class Main extends StatusManager {
	public static final int PAGE_NUM_SPLASH = 1;
	public static final int PAGE_NUM_MAIN = 2;

	@Override
	protected void onInit() {
		//
	}

	@Override
	protected void onAddPages() {
		try {
			this.addPage(new SplashPage());
			this.addPage(new MainPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onRun() {
		try {
			this.setSelectedPageByNum(PAGE_NUM_SPLASH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onExit() {
		System.out.println("프로그램이 종료되었습니다.");
	}

	public static void main(String[] args) {
		(new Main()).run();
	}
}