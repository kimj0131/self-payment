package ezen.project.first.team2.app.common.z_test.framework;

import ezen.project.first.team2.app.common.framework.StatusManager;
import ezen.project.first.team2.app.common.z_test.framework.pages.first.FirstPage;
import ezen.project.first.team2.app.common.z_test.framework.pages.second.SecondPage;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.ThirdPage;

public class Main extends StatusManager {
	// 페이지 번호 정의
	public static final int PAGE_NUM_FIRST = 0;
	public static final int PAGE_NUM_SECOND = 1;
	public static final int PAGE_NUM_THIRD = 2;

	// 초기화 작업 - DB 커넥션 등
	@Override
	protected void onInit() {
		System.out.println("onInit()");
	}

	// 페이지 추가 작업
	@Override
	protected void onAddPages() {
		System.out.println("onAddPages()");

		try {
			this.addPage(new FirstPage());
			this.addPage(new SecondPage());
			this.addPage(new ThirdPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이벤트 리스너 추가 작업
	@Override
	protected void onAddEventListeners() {
		System.out.println("onAddEventListeners()");

		//
	}

	// 실행 작업 - 페이지 선택 등
	@Override
	protected void onRun() {
		System.out.println("onRun()");

		try {
			this.setSelectedPageByNum(Main.PAGE_NUM_FIRST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 종료 작업 - DB 디스커넥션 등
	@Override
	protected void onExit() {
		System.out.println("onExit()");

		//
	}

	public static void main(String[] args) {
		(new Main()).run();
	}
}