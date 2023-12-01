////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_140700] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.payment.pages.main.views;

import java.awt.GridLayout;

import ezen.project.first.team2.app.common.framework.DualView;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.left.LeftView0_OrderList;
import ezen.project.first.team2.app.payment.pages.main.views.left.LeftView1_CheckMember;
import ezen.project.first.team2.app.payment.pages.main.views.left.LeftView2_PointInfo;
import ezen.project.first.team2.app.payment.pages.main.views.left.LeftView3_Payment;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView0_OrderList;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView1_CheckMember;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView2_PointInfo;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView3_Payment;

public class MainView extends DualView {
	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------

	// 생성자
	public MainView() {
		super(MainPage.VIEW_NUM_MAIN, 420);
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

	@Override
	protected void onAddViews() {
		try {
			// Left View
			addView(new LeftView0_OrderList());
			addView(new LeftView1_CheckMember());
			addView(new LeftView2_PointInfo());
			addView(new LeftView3_Payment());

			// Right View
			addView(new RightView0_OrderList());
			addView(new RightView1_CheckMember());
			addView(new RightView2_PointInfo());
			addView(new RightView3_Payment());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[MainView.onShow()]");
		System.out.println("MainView -> db에서 정보를 가져옵니다");

		try {
			MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
			mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
			mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// db에서 정보 가져오기
		var custMngr = CustomerManager.getInstance();
		var prodMngr = ProductManager.getInstance();
		var prodStocksMngr = ProductStocksManager.getInstance();
		var prodDiscntsMngr = ProductDiscountsManager.getInstance();
		
		try {
			custMngr.doSelectQuery();
			prodMngr.doSelectQuery();
			prodStocksMngr.doSelectQuery();
			prodDiscntsMngr.doSelectQuery();
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
