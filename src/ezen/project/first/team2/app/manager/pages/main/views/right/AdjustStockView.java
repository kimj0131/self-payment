package ezen.project.first.team2.app.manager.pages.main.views.right;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AdjustStockView extends View {

    JLabel mLabelInfo = new JLabel("재고 조정뷰 초기화면입니다");

    public AdjustStockView() {
        super(MainPage.VIEW_NUM_STOCK_UPDATE);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {

    }

    @Override
    protected void onAddCtrls() {
        this.add(mLabelInfo);

    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[UpdateProdStockView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[UpdateProdStockView.onHide()]");
    }
}