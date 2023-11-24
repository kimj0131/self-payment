package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class RightDefaultView extends View {

    JLabel mLabelInfo = new JLabel("◆Management System◆");

    public RightDefaultView() {
        super(MainPage.VIEW_NUM_RIGHT);
    }

    @Override
    protected void onInit() {
        mLabelInfo.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void onAddCtrls() {
        this.add(mLabelInfo, BorderLayout.CENTER);
    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[RightDefaultView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[RightDefaultView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont0);
    }
}
