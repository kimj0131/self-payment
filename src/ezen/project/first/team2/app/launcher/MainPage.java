package ezen.project.first.team2.app.launcher;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.Page;

public class MainPage extends Page {
	private static final String TITLE = "셀프 결제 시스템 런처";
	private static final Dimension SIZE = new Dimension(640, 140);

	public MainPage() {
		super(Main.PAGE_NUM_MAIN, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN | Page.OPTION_FIXED_SIZE * 0);
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onAddViews() {
		//
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow() {
	}

	@Override
	protected void onHide() {
	}
}
