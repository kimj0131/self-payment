////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_140700] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.payment.pages.main;

import java.awt.Dimension;

import ezen.project.first.team2.app.common.framework.Page;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class MainPage extends Page {
	// -------------------------------------------------------------------------

	// 페이지 정보 상수 정의

	public static final String TITLE = "결제 프로그램";
	public static final Dimension SIZE = new Dimension(640, 360);

	// 뷰 번호 정의
	public static final int VIEW_NUM_MAIN = 0;
	
	// Left
	public static final int LEFT_VIEW_ORDER_LIST_NUM = 100;
	public static final int LEFT_VIEW_CHECK_MEMBER_NUM = 101;
	public static final int LEFT_VIEW_POINT_INFO_NUM = 102;
	public static final int LEFT_VIEW_PAYMENT_NUM = 103;
	
	// Right
	public static final int RIGHT_VIEW_ORDER_LIST_NUM = 200;
	public static final int RIGHT_VIEW_CHECK_MEMBER_NUM = 201;
	public static final int RIGHT_VIEW_POINT_INFO_NUM = 202;
	public static final int RIGHT_VIEW_PAYMENT_NUM = 203;
	
	// 생성자
	public MainPage() {
		super(Main.PAGE_NUM_MAIN, TITLE, SIZE,
				OPTION_CENTER_IN_SCREEN |
						OPTION_VISIBLE * 0 |
						OPTION_BORDERLESS * 0 |
						OPTION_FULL_SCREEN * 0 |
						OPTION_FIXED_SIZE * 0);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();

	}

	// 뷰 추가
	@Override
	protected void onAddViews() {
		try {
			this.addView(new MainView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
	}

	// 페이지가 표시될 때
	@Override
	protected void onShow() {
		System.out.println("[MainPage.onShow()]");

		try {
			this.setSelectedViewByNum(MainPage.VIEW_NUM_MAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 페이지가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[MainPage.onHide()]");
	}
}



































