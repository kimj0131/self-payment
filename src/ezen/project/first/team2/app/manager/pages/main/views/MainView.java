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
import ezen.project.first.team2.app.manager.pages.main.views.right.product.AddProductView;
import ezen.project.first.team2.app.manager.pages.main.views.right.product.DeleteProductView;
import ezen.project.first.team2.app.manager.pages.main.views.right.product.ListProductView;
import ezen.project.first.team2.app.manager.pages.main.views.right.product.UpdateProductView;
import ezen.project.first.team2.app.manager.pages.main.views.right.stock.AdjustStockView;
import ezen.project.first.team2.app.manager.pages.main.views.right.stock.ListStockView;
import ezen.project.first.team2.app.manager.pages.main.views.right.RightDefaultView;
import ezen.project.first.team2.app.manager.pages.main.views.right.customer.AddCustomerView;
import ezen.project.first.team2.app.manager.pages.main.views.right.customer.DeleteCustomerView;
import ezen.project.first.team2.app.manager.pages.main.views.right.customer.ListCustomerView;
import ezen.project.first.team2.app.manager.pages.main.views.right.customer.UpdateCustomerView;
import ezen.project.first.team2.app.manager.pages.main.views.right.discount.AdjustDiscountView;

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
			addView(new RightDefaultView());

			addView(new ListCustomerView());
			addView(new AddCustomerView());
			addView(new UpdateCustomerView());
			addView(new DeleteCustomerView());

			addView(new ListProductView());
			addView(new AddProductView());
			addView(new UpdateProductView());
			addView(new DeleteProductView());

			addView(new ListStockView());
			addView(new AdjustStockView());

			addView(new AdjustDiscountView());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[MainView.onShow()]");

		try {
			MainView mainview = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
			mainview.setSelectedLeftViewByNum(MainPage.VIEW_NUM_LEFT);
			mainview.setSelectedRightViewByNum(MainPage.VIEW_NUM_RIGHT);
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
