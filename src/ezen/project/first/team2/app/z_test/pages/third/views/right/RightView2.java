package ezen.project.first.team2.app.z_test.pages.third.views.right;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import ezen.project.first.team2.app.framework.View;
import ezen.project.first.team2.app.z_test.pages.third.ThirdPage;

public class RightView2 extends View {
	private static final int PADDING = 10;

	public RightView2() {
		super(ThirdPage.RIGHT_VIEW_NUM_2);
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
		this.add(new JLabel("세 번째 뷰"));
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow() {
		System.out.println("[RightView2.onShow()]");
	}

	@Override
	protected void onHide() {
		System.out.println("[RightView2.onHide()]");
	}
}
