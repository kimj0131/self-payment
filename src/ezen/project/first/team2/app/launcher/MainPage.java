package ezen.project.first.team2.app.launcher;

import java.awt.Dimension;

import ezen.project.first.team2.app.framework.Page;
import ezen.project.first.team2.app.launcher.views.MainView;

public class MainPage extends Page {
	public static final String TITLE = "셀프 결제 시스템 런처";
	public static final Dimension SIZE = new Dimension(640, 140);

	public static final int VIEW_NUM_MAIN = 0;

	public MainPage() {
		super(Main.PAGE_NUM_MAIN, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN | Page.OPTION_FIXED_SIZE * 0);
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onAddViews() {
		try {
			this.addView(new MainView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow() {
		try {
			this.setSelectedViewByNum(MainPage.VIEW_NUM_MAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onHide() {
	}
}
