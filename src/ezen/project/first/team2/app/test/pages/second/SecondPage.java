package ezen.project.first.team2.app.test.pages.second;

import java.awt.Dimension;

import javax.swing.JButton;

import ezen.project.first.team2.app.common.Page;
import ezen.project.first.team2.app.test.Main;
import ezen.project.first.team2.app.test.pages.second.views.FirstView;
import ezen.project.first.team2.app.test.pages.second.views.SecondView;

public class SecondPage extends Page {
	// 페이지 정보 정의
	private static final String TITLE = "두 번째 페이지";
	private static final Dimension SIZE = new Dimension(640, 360);

	// 뷰 번호 정의
	public static final int VIEW_NUM_FIRST = 0;
	public static final int VIEW_NUM_SECOND = 1;

	// 컨트롤 선언
	private JButton mSelect3rdPageBtn = new JButton();

	public SecondPage() {
		super(Main.PAGE_NUM_SECOND, TITLE, SIZE,
				Page.OPTION_CENTER_IN_SCREEN);
	}

	@Override
	protected void onInit() {
		System.out.println(this.getTitle() + " => onInit()");

		//
	}

	@Override
	protected void onAddViews() {
		System.out.println(this.getTitle() + " => onAddViews()");

		try {
			this.addView(new FirstView());
			this.addView(new SecondView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onAddEventListeners() {
		System.out.println(this.getTitle() + " => onAddEventListeners()");

		this.mSelect3rdPageBtn.addActionListener(e -> {
			try {
				// Main main = (Main) SecondPage.this.getStatusManager();
				// main.setSelectPageByNum(Main.PAGE_NUM_);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow() {
		System.out.println(this.getTitle() + " => onShow()");

		try {
			this.setSelectedViewByNum(VIEW_NUM_FIRST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onHide() {
		System.out.println(this.getTitle() + " => onHide()");

		//
	}
}
