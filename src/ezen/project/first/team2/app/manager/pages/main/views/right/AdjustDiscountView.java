package ezen.project.first.team2.app.manager.pages.main.views.right;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AdjustDiscountView extends View {

    JLabel mLabelInfo = new JLabel("할인금액 조정뷰 초기화면입니다");

    public AdjustDiscountView() {
        super(MainPage.VIEW_NUM_DISCOUNT_ADJUST);
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
        System.out.println("[AdjustDiscountView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[AdjustDiscountView.onHide()]");
    }
}