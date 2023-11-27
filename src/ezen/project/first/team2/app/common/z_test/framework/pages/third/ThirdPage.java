package ezen.project.first.team2.app.common.z_test.framework.pages.third;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.common.z_test.framework.Main;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.MainView;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.popup.MyPopupView;

public class ThirdPage extends Page {
	// 페이지 정보 정의
	private static final String TITLE = "세 번째 페이지";
	private static final Dimension SIZE = new Dimension(1280, 720);

	// 뷰 번호 정의
	public static final int VIEW_NUM_MAIN = 0;
	public static final int LEFT_VIEW_NUM_0 = 100;
	public static final int RIGHT_VIEW_NUM_0 = 200;
	public static final int RIGHT_VIEW_NUM_1 = 201;
	public static final int RIGHT_VIEW_NUM_2 = 202;
	public static final int RIGHT_VIEW_NUM_3 = 203;

	// 팝업 뷰 번호 정의
	public static final int POPUP_VIEW_NUM_MY = 1000;

	public ThirdPage() {
		super(Main.PAGE_NUM_THIRD, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN * 1 |
						Page.OPTION_BORDERLESS * 0 |
						Page.OPTION_FULL_SCREEN * 0 |
						Page.OPTION_FIXED_SIZE * 1);
	}

	@Override
	protected void onInit() {
		System.out.println(this.getTitle() + " => onInit()");
	}

	@Override
	protected void onAddViews() {
		System.out.println(this.getTitle() + " => onAddViews()");

		try {
			this.addView(new MainView());

			this.addView(new MyPopupView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onAddEventListeners() {
		System.out.println(this.getTitle() + " => onAddEventListeners()");
	}

	@Override
	protected void onShow(boolean firstTime) {
		System.out.println(this.getTitle() + " => onShow()");

		try {
			this.setSelectedViewByNum(ThirdPage.VIEW_NUM_MAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onHide() {
		System.out.println(this.getTitle() + " => onHide()");
	}
}
