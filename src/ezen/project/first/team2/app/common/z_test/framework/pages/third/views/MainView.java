package ezen.project.first.team2.app.common.z_test.framework.pages.third.views;

import java.awt.GridLayout;

import ezen.project.first.team2.app.common.framework.DualView;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.ThirdPage;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.left.LeftView;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.right.RightView0;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.right.RightView1;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.right.RightView2;
import ezen.project.first.team2.app.common.z_test.framework.pages.third.views.right.RightView3;

public class MainView extends DualView {
	// private static final int PADDING = 10;

	public MainView() {
		super(ThirdPage.VIEW_NUM_MAIN, 240);
	}

	@Override
	protected void onInit() {
		// [SGLEE:20231116THU_120400] 반드시 호출해야한다!
		super.onInit();

		// this.setBackground(Color.BLUE);
	}

	@Override
	protected void onSetLayout() {
		// this.setBorder(BorderFactory.createEmptyBorder(
		// PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout(1, 1, 10, 10));
	}

	@Override
	protected void onAddViews() {
		try {
			// Left View
			addView(new LeftView());

			// Right View
			addView(new RightView0());
			addView(new RightView1());
			addView(new RightView2());
			addView(new RightView3());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onShow() {
		System.out.println("MainView.onShow()");

		try {
			MainView mainView = (MainView) this.getPage().getViewByNum(ThirdPage.VIEW_NUM_MAIN);
			mainView.setSelectedLeftViewByNum(ThirdPage.LEFT_VIEW_NUM_0);
			mainView.setSelectedRightViewByNum(ThirdPage.RIGHT_VIEW_NUM_0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onHide() {
		System.out.println("MainView.onHide()");

		//
	}
}
