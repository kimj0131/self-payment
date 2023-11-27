package ezen.project.first.team2.app.common.z_test.framework.pages.second.views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.z_test.framework.Main;
import ezen.project.first.team2.app.common.z_test.framework.pages.second.SecondPage;

public class SecondView extends View {
	JButton m1stViewBtn = new JButton();
	JButton m1stPageBtn = new JButton();

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
		this.m1stPageBtn.setText("첫 번째 페이지");

		this.add(this.m1stViewBtn);
		this.add(this.m1stPageBtn);
	}

	@Override
	protected void onAddEventListeners() {
		ActionListener listener = e -> {
			var btn = (JButton) e.getSource();
			try {
				if (btn == m1stViewBtn) {
					SecondPage page = (SecondPage) SecondView.this.getPage();
					page.setSelectedViewByNum(SecondPage.VIEW_NUM_FIRST);
				} else if (btn == m1stPageBtn) {
					var main = (Main) getStatusManager();
					main.setSelectedPageByNum(Main.PAGE_NUM_FIRST);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};

		this.m1stViewBtn.addActionListener(listener);
		this.m1stPageBtn.addActionListener(listener);
	}

	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("SecondView.onShow()");
	}

	@Override
	protected void onHide() {
		System.out.println("SecondView.onHide()");
	}
}
