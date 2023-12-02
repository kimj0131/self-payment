package ezen.project.first.team2.app.common.pages.splash.views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.pages.splash.SplashPage;

public class MainView extends View {
	private JLabel mLabel0 = new JLabel();
	private JLabel mImgLabel = new JLabel();

	public MainView() {
		super(SplashPage.VIEW_NUM_MAIN);
	}

	public void setLabel0Text(String text) {
		this.mLabel0.setText(text);
	}

	@Override
	protected void onInit() {
		var icon = new ImageIcon("resources/images/SplashImage copy.jpg");
		this.mImgLabel.setIcon(icon);
	}

	@Override
	protected void onSetLayout() {
		setLayout(null);
	}

	@Override
	protected void onAddCtrls() {
		this.mLabel0.setText("Initializing...");

		final int LABEL_HEIGHT = 30;

		JLayeredPane p = new JLayeredPane();
		p.setBounds(this.getBounds());
		this.mLabel0.setBounds(10, this.getHeight() - LABEL_HEIGHT, this.getWidth(), LABEL_HEIGHT);
		this.mImgLabel.setBounds(this.getBounds());
		p.add(this.mImgLabel, JLayeredPane.DEFAULT_LAYER);
		p.add(this.mLabel0, JLayeredPane.POPUP_LAYER);

		this.add(p);
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
