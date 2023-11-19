////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_124500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.manager.pages.main.views;

import java.awt.GridLayout;

import ezen.project.first.team2.app.common.framework.DualView;
import ezen.project.first.team2.app.manager.pages.main.MainPage;
import ezen.project.first.team2.app.manager.pages.main.views.left.LeftView;
import ezen.project.first.team2.app.manager.pages.main.views.right.CustmerAdd;
import ezen.project.first.team2.app.manager.pages.main.views.right.CustmerDelete;
import ezen.project.first.team2.app.manager.pages.main.views.right.CustmerInfo;
import ezen.project.first.team2.app.manager.pages.main.views.right.CustmerUpdate;
import ezen.project.first.team2.app.manager.pages.main.views.right.ProductAdd;
import ezen.project.first.team2.app.manager.pages.main.views.right.ProductDelete;
import ezen.project.first.team2.app.manager.pages.main.views.right.ProductInfo;
import ezen.project.first.team2.app.manager.pages.main.views.right.ProductUpdate;

public class MainView extends DualView {
	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------

	// 생성자
	public MainView() {
		super(MainPage.VIEW_NUM_MAIN, 240);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
		this.setLayout(new GridLayout(1, 1, 10, 10));
	}

	// 뷰 추가
	@Override
	protected void onAddViews() {
		try {
			addView(new LeftView());

			addView(new CustmerInfo());
			addView(new CustmerAdd());
			addView(new CustmerUpdate());
			addView(new CustmerDelete());

			addView(new ProductInfo());
			addView(new ProductAdd());
			addView(new ProductUpdate());
			addView(new ProductDelete());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// // 컨트롤 추가
	// @Override
	// protected void onAddCtrls() {
	// this.mBtn0.setText("버튼0");

	// this.add(this.mBtn0);
	// }

	// // 이벤트 리스너 추가
	// @Override
	// protected void onAddEventListeners() {
	// this.mBtn0.addActionListener(e -> {
	// UiUtils.showMsgBox("버튼0", MainPage.TITLE);
	// });
	// }

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[MainView.onShow()]");

		try {
			MainView mainview = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
			mainview.setSelectedLeftViewByNum(MainPage.VIEW_NUM_LEFT);
			mainview.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_INFO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[MainView.onHide()]");
	}
}
