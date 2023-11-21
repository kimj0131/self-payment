package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class ListProdStockView extends View {

    JLabel mLabelInfo = new JLabel("재고 조회뷰 초기화면입니다");

    public ListProdStockView() {
        super(MainPage.VIEW_NUM_STOCK_LIST);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void onAddCtrls() {

        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        this.add(mLabelInfo, BorderLayout.NORTH);

    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[ListProdStockView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[ListProdStockView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont2);
    }

}