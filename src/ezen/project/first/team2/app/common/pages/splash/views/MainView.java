package ezen.project.first.team2.app.common.pages.splash.views;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.pages.splash.SplashPage;

public class MainView extends View {
	private JLabel mLabel0 = new JLabel();

	public MainView() {
		super(SplashPage.VIEW_NUM_MAIN);
	}

	public void setLabel0Text(String text) {
		this.mLabel0.setText(text);
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

		System.out.println(this.getBounds());

		this.add(this.mLabel0);
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow(boolean firstTime) {
		// System.out.println("[MainView.onShow()]");
	}

	@Override
	protected void onHide() {
		// System.out.println("[MainView.onHide()]");
	}

	public static void setSelectedViewByNum(int viewNumMain) {
	}
}
