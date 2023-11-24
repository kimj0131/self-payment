package ezen.project.first.team2.app.manager.pages.main.views.right;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class DeleteProductView extends View {

    JLabel mLabelInfo = new JLabel("상품 데이터 삭제");

    public DeleteProductView() {
        super(MainPage.VIEW_NUM_PROD_DELETE);
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
        System.out.println("[DeleteProductView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[DeleteProductView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);
    }
}