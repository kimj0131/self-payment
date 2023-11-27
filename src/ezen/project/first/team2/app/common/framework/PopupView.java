package ezen.project.first.team2.app.common.framework;

import java.awt.Dimension;

public class PopupView extends View {
	// -------------------------------------------------------------------------

	public PopupView(int num) {
		super(num);
	}

	public PopupView(int num, Dimension size) {
		super(num, size);
	}

	// -------------------------------------------------------------------------

	@Override
	protected void onInit() {
		super.onInit();

		setVisible(false);
	}

	// -------------------------------------------------------------------------

	@Override
	public void performShow() {
		super.performShow();

		this.setVisible(true);
	}

	@Override
	public void performHide() {
		super.performHide();

		this.setVisible(false);
	}

	public void performClose() {
		var page = this.getPage();
		var dimmedView = page.getDimmedView();

		dimmedView.setVisible(false);
		this.performHide();
	}
}
