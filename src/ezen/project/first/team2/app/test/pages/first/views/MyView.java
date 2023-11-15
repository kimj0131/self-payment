package ezen.project.first.team2.app.test.pages.first.views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import ezen.project.first.team2.app.common.View;
import ezen.project.first.team2.app.test.Main;
import ezen.project.first.team2.app.test.pages.first.FirstPage;

public class MyView extends View {
	private static final Dimension SIZE = new Dimension(320, 240);
	private static final int PADDING = 10;

	JButton m2ndPageBtn = new JButton();

	public MyView() {
		super(FirstPage.VIEW_NUM_MY, SIZE);
	}

	@Override
	protected void onInit() {
		// this.setBackground(Color.BLUE);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout(1, 1, 10, 10));
	}

	@Override
	protected void onAddCtrls() {
		this.m2ndPageBtn.setText("두 번째 페이지");

		this.add(this.m2ndPageBtn);
	}

	@Override
	protected void onAddEventListeners() {
		this.m2ndPageBtn.addActionListener(e -> {
			try {
				// FirstPage page = (FirstPage)MyView.this.getPage();
				// Main main = (Main)page.getStatusManager();
				Main main = (Main) MyView.this.getStatusManager();
				main.setSelectPageByNum(Main.PAGE_NUM_SECOND);
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
