package ezen.project.first.team2.app.payment.pages.stanby;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.stanby.views.StanbyView;

public class StanbyPage extends Page {

	public static final String TITLE = "스텐바이 페이지";
	public static final Dimension SIZE = new Dimension(640, 360);

	public static final int VIEW_NUM_STANBY = 0;

	public StanbyPage() {
		super(Main.PAGE_NUM_STANBY, TITLE, SIZE,
				OPTION_CENTER_IN_SCREEN |
						OPTION_VISIBLE * 0 |
						OPTION_BORDERLESS * 0 |
						OPTION_FULL_SCREEN * 0 |
						OPTION_FIXED_SIZE * 0);
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onAddViews() {
		try {
			this.addView(new StanbyView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onAddEventListeners() {
	}

	@Override
	protected void onShow(boolean firstTime) {
		try {
			this.setSelectedViewByNum(StanbyPage.VIEW_NUM_STANBY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onHide() {
	}

}
