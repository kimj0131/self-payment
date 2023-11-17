package ezen.project.first.team2.app.common.pages.splash.views;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.pages.splash.SplashPage;

public class MainView extends View {
	private JLabel mLabel0 = new JLabel();

	public MainView() {
		super(SplashPage.VIEW_NUM_MAIN);
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onSetLayout() {
	}

	@Override
	protected void onAddCtrls() {
		this.mLabel0.setText("스플래시 페이지");

		this.add(this.mLabel0);
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
