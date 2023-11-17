package ezen.project.first.team2.app.manager.pages.main.views.right;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class CustmerAdd extends View {

    JLabel mLabelInfo = new JLabel("회원 추가뷰 초기화면입니다");

    public CustmerAdd() {
        super(MainPage.VIEW_NUM_CUST_ADD);
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
    protected void onShow() {
        System.out.println("[RightView0.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[RightView0.onHide()]");
    }
}