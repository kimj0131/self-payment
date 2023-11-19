package ezen.project.first.team2.app.manager.pages.main.views.right;

import ezen.project.first.team2.app.manager.pages.main.MainPage;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;

public class RightView extends View {

    JLabel mLabelInfo = new JLabel("오른쪽 뷰 초기화면입니다");

    public RightView() {
        super(MainPage.VIEW_NUM_RIGHT);
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
        System.out.println("[RightView0.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[RightView0.onHide()]");
    }
}
