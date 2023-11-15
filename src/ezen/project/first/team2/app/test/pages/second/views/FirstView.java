package ezen.project.first.team2.app.test.pages.second.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.View;
import ezen.project.first.team2.app.test.pages.second.SecondPage;

public class FirstView extends View {
	private static final Dimension SIZE = new Dimension(320, 240);

	JButton m2ndViewBtn = new JButton();

	public FirstView() {
		super(SecondPage.VIEW_NUM_FIRST, SIZE);
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
				page.setSelectViewByNum(SecondPage.VIEW_NUM_SECOND);
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
