package ezen.project.first.team2.app.manager.pages.main.views.right.discount;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AdjustDiscountView extends View {

    JLabel mLabelInfo = new JLabel("가입고객대상 상품 할인금액 설정");

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

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);
    }
}