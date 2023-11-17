package ezen.project.first.team2.app.common.z_test.pages.third.views.left;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.z_test.pages.third.ThirdPage;
import ezen.project.first.team2.app.common.z_test.pages.third.views.MainView;

public class LeftView extends View {
	private static final int PADDING = 10;

	JButton mFirstViewBtn = new JButton("첫 번째 뷰");
	JButton mSecondViewBtn = new JButton("두 번째 뷰");
	JButton mThirdViewBtn = new JButton("세 번째 뷰");
	JButton mFourthViewBtn = new JButton("네 번째 뷰");

	public LeftView() {
		super(ThirdPage.LEFT_VIEW_NUM_0);
	}

	@Override
	protected void onInit() {
		this.setBackground(Color.BLUE);
		// this.setPreferredSize(new Dimension(240, 0));
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mFirstViewBtn);
		this.add(this.mSecondViewBtn);
		this.add(this.mThirdViewBtn);
		this.add(this.mFourthViewBtn);
	}

	@Override
	protected void onAddEventListeners() {
		ActionListener listener = e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(ThirdPage.VIEW_NUM_MAIN);

				JButton btn = (JButton) e.getSource();
				if (btn == this.mFirstViewBtn) {
					mainView.setSelectedRightViewByNum(ThirdPage.RIGHT_VIEW_NUM_0);
				} else if (btn == this.mSecondViewBtn) {
					mainView.setSelectedRightViewByNum(ThirdPage.RIGHT_VIEW_NUM_1);
				} else if (btn == this.mThirdViewBtn) {
					mainView.setSelectedRightViewByNum(ThirdPage.RIGHT_VIEW_NUM_2);
				} else if (btn == this.mFourthViewBtn) {
					mainView.setSelectedRightViewByNum(ThirdPage.RIGHT_VIEW_NUM_3);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};

		this.mFirstViewBtn.addActionListener(listener);
		this.mSecondViewBtn.addActionListener(listener);
		this.mThirdViewBtn.addActionListener(listener);
		this.mFourthViewBtn.addActionListener(listener);
	}

	@Override
	protected void onShow() {
	}

	@Override
	protected void onHide() {
	}
}
