////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_124500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.manager;

import java.awt.Font;

import ezen.project.first.team2.app.common.framework.StatusManager;
import ezen.project.first.team2.app.common.pages.splash.SplashPage;
import ezen.project.first.team2.app.common.pages.splash.SplashPageParams;
import ezen.project.first.team2.app.common.pages.splash.views.MainView;
import ezen.project.first.team2.app.manager.pages.login.LoginPage;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class Main extends StatusManager {
	// 페이지 번호 정의 - 뷰에서도 사용하므로 public으로 선언
	public static final int PAGE_NUM_SPLASH = SplashPage.PAGE_NUM;
	public static final int PAGE_NUM_MAIN = 100;
	public static final int PAGE_NUM_LOGIN = 101;

	// 리소스
	public Font mFont0;
	public Font mFont1;
	public Font mFont2;
	public Font mFont3;

	// 초기화 작업 - DB 커넥션 등
	@Override
	protected void onInit() {
		System.out.println("[Main.onInit()]");
	}

	// 페이지 추가 작업
	@Override
	protected void onAddPages() {
		try {
			// 주석제거
			this.addPage(new SplashPage(this.getSplashPageParams()));
			this.addPage(new LoginPage());
			this.addPage(new MainPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이벤트 리스너 추가 작업
	@Override
	protected void onAddEventListeners() {
	}

	// 실행 작업 - 페이지 선택 등
	@Override
	protected void onRun() {
		try {
			// 주석제거
			this.setSelectedPageByNum(PAGE_NUM_SPLASH);
			// this.setSelectedPageByNum(PAGE_NUM_LOGIN);
			// this.setSelectedPageByNum(PAGE_NUM_MAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 종료 작업 - DB 디스커넥션 등
	@Override
	protected void onExit() {
		System.out.println("[Main.onExit()]");
	}

	private SplashPageParams getSplashPageParams() {
		SplashPageParams params = new SplashPageParams(new SplashPageParams.Listener() {
			@Override
			public void onConnectingDb(Object param) {
				Main main = (Main) param;
				SplashPage splashPage = (SplashPage) main.getPageByNum(SplashPage.PAGE_NUM);
				MainView mainView = (MainView) splashPage.getViewByNum(SplashPage.VIEW_NUM_MAIN);

				mainView.setLabel0Text("Initializing database...");
			}

			@Override
			public void onLoadResources(Object param, int resourceIndex, int resourceCount) {
				Main main = (Main) param;
				SplashPage splashPage = (SplashPage) main.getPageByNum(SplashPage.PAGE_NUM);
				MainView mainView = (MainView) splashPage.getViewByNum(SplashPage.VIEW_NUM_MAIN);

				final int rsrcIdx = resourceIndex;
				final int rsrcCnt = resourceCount;

				String s = String.format("rsrcIdx: %d, rsrcCnt: %d", rsrcIdx, rsrcCnt);
				System.out.println(s);
				mainView.setLabel0Text(s);

				switch (rsrcIdx) {
					case 0:
						main.mFont0 = new Font("견명조", Font.BOLD, 40);
						break;
					case 1:
						main.mFont1 = new Font("견명조", Font.PLAIN, 30);
						break;
					case 2:
						main.mFont2 = new Font("견명조", Font.BOLD, 18);
						break;
					case 3:
						main.mFont3 = new Font("견명조", Font.PLAIN, 14);
						break;
				}

			}

			@Override
			public void onCompleteResources(Object param) {
				Main main = (Main) param;
				main.performSetResources();

				try {
					Main.this.setSelectedPageByNum(Main.PAGE_NUM_LOGIN);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}, this, 4);

		return params;
	}

	public static void main(String[] args) {
		(new Main()).run();
	}
}
