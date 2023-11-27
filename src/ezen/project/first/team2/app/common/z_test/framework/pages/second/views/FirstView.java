package ezen.project.first.team2.app.common.z_test.framework.pages.second.views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.z_test.framework.Main;
import ezen.project.first.team2.app.common.z_test.framework.pages.second.SecondPage;

public class FirstView extends View {
	JButton m2ndViewBtn = new JButton();
	JButton m1stPageBtn = new JButton();

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
		this.m1stPageBtn.setText("첫 번째 페이지");

		this.add(this.m2ndViewBtn);
		this.add(this.m1stPageBtn);
	}

	@Override
	protected void onAddEventListeners() {
		ActionListener listener = e -> {
			var btn = (JButton) e.getSource();
			try {
				if (btn == m2ndViewBtn) {
					SecondPage page = (SecondPage) FirstView.this.getPage();
					page.setSelectedViewByNum(SecondPage.VIEW_NUM_SECOND);
				} else if (btn == m1stPageBtn) {
					var main = (Main) getStatusManager();
					main.setSelectedPageByNum(Main.PAGE_NUM_FIRST);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};

		this.m2ndViewBtn.addActionListener(listener);
		this.m1stPageBtn.addActionListener(listener);
	}

	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("FirstView.onShow()");
	}

	@Override
	protected void onHide() {
		System.out.println("FirstView.onHide()");
	}
}
