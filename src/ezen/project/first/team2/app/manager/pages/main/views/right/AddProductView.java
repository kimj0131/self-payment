package ezen.project.first.team2.app.manager.pages.main.views.right;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AddProductView extends View {

    JLabel mLabelInfo = new JLabel("상품 추가뷰 초기화면입니다");

    public AddProductView() {
        super(MainPage.VIEW_NUM_PROD_ADD);
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
        System.out.println("[AddProductView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[AddProductView.onHide()]");
    }
}