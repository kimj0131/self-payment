////////////////////////////////////////////////////////////////////////////////
// [SGLEE:20231112SUN_233800] Created
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.launcher;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.Page;
import ezen.project.first.team2.app.common.StatusManager;

public class Main extends StatusManager {
	public static final int PAGE_NUM_SPLASH = 1000;
	public static final int PAGE_NUM_MAIN = 2000;

	@Override
	protected void onInit() {
		try {
			this.addPage(new SplashPage());
			this.addPage(new MainPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
	}

	@Override
	protected void onRun() {
		this.selectPageByNum(PAGE_NUM_SPLASH);
	}

	public static void main(String[] args) {
		// (new Main()).run();

		// Class<Page> p = MyPage;
	}

	static void f() {

	}
}

class MyPage extends Page {
	public MyPage() {
		super(1, "A", new Dimension(100, 100));
	}

	public void method() {
		System.out.println("메소드~");
	}
}