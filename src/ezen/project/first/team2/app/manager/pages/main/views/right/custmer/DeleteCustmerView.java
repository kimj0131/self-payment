package ezen.project.first.team2.app.manager.pages.main.views.right.custmer;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class DeleteCustmerView extends View {

    JLabel mLabelInfo = new JLabel("고객 데이터 삭제");

    public DeleteCustmerView() {
        super(MainPage.VIEW_NUM_CUST_DELETE);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {

    }

    @Override
    protected void onAddCtrls() {

    }

    @Override
    protected void onAddEventListeners() {
        this.add(mLabelInfo);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[DeleteCustmerView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[DeleteCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);
    }
}