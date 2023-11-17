package ezen.project.first.team2.app.test.pages.second.views;

import java.awt.Color;

import javax.swing.JButton;

import ezen.project.first.team2.app.framework.View;
import ezen.project.first.team2.app.test.pages.second.SecondPage;

public class SecondView extends View {
	JButton m1stViewBtn = new JButton();

	public SecondView() {
		super(SecondPage.VIEW_NUM_SECOND);
	}

	@Override
	protected void onInit() {
		this.setBackground(Color.GREEN);
	}

	@Override
	protected void onSetLayout() {
	}

	@Override
	protected void onAddCtrls() {
		this.m1stViewBtn.setText("첫 번째 뷰");

		this.add(this.m1stViewBtn);
	}

	@Override
	protected void onAddEventListeners() {
		this.m1stViewBtn.addActionListener(e -> {
			try {
				SecondPage page = (SecondPage) SecondView.this.getPage();
				page.setSelectedViewByNum(SecondPage.VIEW_NUM_FIRST);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow() {
		System.out.println("SecondView.onShow()");
	}

	@Override
	protected void onHide() {
		System.out.println("SecondView.onHide()");
	}
}
