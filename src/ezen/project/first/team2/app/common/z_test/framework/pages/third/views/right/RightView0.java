package ezen.project.first.team2.app.common.z_test.framework.pages.third.views.right;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.z_test.framework.Main;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.ThirdPage;

public class RightView0 extends View {
	private static final int PADDING = 10;

	public RightView0() {
		super(ThirdPage.RIGHT_VIEW_NUM_0);
	}

	@Override
	protected void onInit() {
		//
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
	}

	@Override
	protected void onAddCtrls() {
		this.add(new JLabel("첫 번째 뷰"));
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[RightView0.onShow()]");
	}

	@Override
	protected void onHide() {
		System.out.println("[RightView0.onHide()]");
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		JLabel lbl = (JLabel) this.getComponents()[0];
		// lbl.setFont(main.mFont0);
		lbl.setIcon(main.mImg0);
	}
}
