package ezen.project.first.team2.app.common.z_test.pages.second.views;

import java.awt.Color;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.z_test.pages.second.SecondPage;

public class FirstView extends View {
	JButton m2ndViewBtn = new JButton();

	public FirstView() {
		super(SecondPage.VIEW_NUM_FIRST);
	}

	@Override
	protected void onInit() {
		this.setBackground(Color.BLUE);
	}

	@Override
	protected void onSetLayout() {
	}

	@Override
	protected void onAddCtrls() {
		this.m2ndViewBtn.setText("두 번째 뷰");

		this.add(this.m2ndViewBtn);
	}

	@Override
	protected void onAddEventListeners() {
		this.m2ndViewBtn.addActionListener(e -> {
			try {
				SecondPage page = (SecondPage) FirstView.this.getPage();
				page.setSelectedViewByNum(SecondPage.VIEW_NUM_SECOND);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow() {
		System.out.println("FirstView.onShow()");
	}

	@Override
	protected void onHide() {
		System.out.println("FirstView.onHide()");
	}
}
