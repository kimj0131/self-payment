package ezen.project.first.team2.app.payment.pages.stanby.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.stanby.StanbyPage;

public class StanbyView extends View {
	private JLabel mLabel0 = new JLabel("화면을 터치해 주세요");

	public StanbyView() {
		super(StanbyPage.VIEW_NUM_STANBY);
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onSetLayout() {

	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mLabel0);
	}

	@Override
	protected void onAddEventListeners() {
		// 화면 터치시 메인 페이지로
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					getStatusManager().setSelectedPageByNum(Main.PAGE_NUM_MAIN);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

}
