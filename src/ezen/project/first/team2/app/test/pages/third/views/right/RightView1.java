package ezen.project.first.team2.app.test.pages.third.views.right;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.View;
import ezen.project.first.team2.app.test.pages.third.ThirdPage;

public class RightView1 extends View {
	private static final int PADDING = 10;

	public RightView1() {
		super(ThirdPage.RIGHT_VIEW_NUM_1);
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
		this.add(new JLabel("두 번째 뷰"));
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow() {
		System.out.println("[RightView1.onShow()]");
	}

	@Override
	protected void onHide() {
		System.out.println("[RightView1.onHide()]");
	}
}
