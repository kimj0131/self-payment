package ezen.project.first.team2.app.manager.pages.main.views.left;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;
import ezen.project.first.team2.app.manager.pages.main.views.MainView;

public class LeftView extends View {

    // 상단 로고 라벨
    JLabel mLabelLogo = new JLabel("## Management System ##");
    // 하단 조작 패널
    JPanel mPanelCtr = new JPanel();
    // 하단 패널 분할

    // 회원관리
    JLabel mLabelCustManage = new JLabel("　　■ 고객 관리");
    JButton mBtnCustList = new JButton("　　　고객 조회　　　");
    JButton mBtnCustAdd = new JButton("　　　고객 추가　　　");
    JButton mBtnCustUpdate = new JButton("　　　고객 수정　　　");
    JButton mBtnCustDelete = new JButton("　　　고객 삭제　　　");
    // 상품관리
    JLabel mLabelProductManage = new JLabel("　　■ 상품 관리");
    JButton mBtnProdList = new JButton("　　　상품 조회　　　");
    JButton mBtnProdAdd = new JButton("　　　상품 추가　　　");
    JButton mBtnProdUpdate = new JButton("　　　상품 수정　　　");
    JButton mBtnProdDelete = new JButton("　　　상품 삭제　　　");
    // 재고관리
    JLabel mLabelProdStockManage = new JLabel("　　■ 상품 재고 관리");
    JButton mBtnProdStockList = new JButton("　　　재고 조회　　　");
    JButton mBtnProdStockAdjust = new JButton("　　　재고 조정　　　");
    // 할인등록
    JLabel mLabelDiscountManage = new JLabel("　　■ 상품 할인");
    JButton mBtnDiscountAdjust = new JButton("　상품 할인금액 조정　");

    public LeftView() {
        super(MainPage.VIEW_NUM_LEFT);
    }

    @Override
    protected void onInit() {
        // this.setBackground(Color.DARK_GRAY);
        this.mPanelCtr.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelCtr.setLayout(new BoxLayout(mPanelCtr, BoxLayout.PAGE_AXIS));
        // this.mPanelCtr.setLayout(new GridLayout(10, 1));
    }

    @Override
    protected void onAddCtrls() {
        // 컴포넌트 가운데 정렬
        this.mLabelCustManage.setHorizontalAlignment(JLabel.CENTER);
        this.mBtnCustList.setHorizontalAlignment(JButton.CENTER);

        this.add(mLabelLogo, BorderLayout.NORTH);
        this.add(mPanelCtr, BorderLayout.CENTER);

        // 회원
        this.mPanelCtr.add(mLabelCustManage);
        this.mPanelCtr.add(mBtnCustList);
        this.mPanelCtr.add(mBtnCustAdd);
        this.mPanelCtr.add(mBtnCustUpdate);
        this.mPanelCtr.add(mBtnCustDelete);
        // 여백삽입 (박스레이아웃)
        this.mPanelCtr.add(Box.createVerticalStrut(30));
        // 상품
        this.mPanelCtr.add(mLabelProductManage);
        this.mPanelCtr.add(mBtnProdList);
        this.mPanelCtr.add(mBtnProdAdd);
        this.mPanelCtr.add(mBtnProdUpdate);
        this.mPanelCtr.add(mBtnProdDelete);
        // 여백삽입
        this.mPanelCtr.add(Box.createVerticalStrut(30));
        // 재고
        this.mPanelCtr.add(mLabelProdStockManage);
        this.mPanelCtr.add(mBtnProdStockList);
        this.mPanelCtr.add(mBtnProdStockAdjust);
        // 여백
        this.mPanelCtr.add(Box.createVerticalStrut(30));
        // 할인
        this.mPanelCtr.add(mLabelDiscountManage);
        this.mPanelCtr.add(mBtnDiscountAdjust);
    }

    @Override
    protected void onAddEventListeners() {
        ActionListener listener = e -> {
            try {
                MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);

                JButton btn = (JButton) e.getSource();
                if (btn == this.mBtnCustList) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_LIST);
                } else if (btn == this.mBtnCustAdd) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_ADD);
                } else if (btn == this.mBtnCustUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_UPDATE);
                } else if (btn == this.mBtnCustDelete) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_DELETE);
                } else if (btn == this.mBtnProdList) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_LIST);
                } else if (btn == this.mBtnProdAdd) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_ADD);
                } else if (btn == this.mBtnProdUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_UPDATE);
                } else if (btn == this.mBtnProdDelete) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_DELETE);
                } else if (btn == this.mBtnProdStockList) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_STOCK_LIST);
                } else if (btn == this.mBtnProdStockAdjust) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_STOCK_ADJUST);
                } else if (btn == this.mBtnDiscountAdjust) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_DISCOUNT_ADJUST);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        this.mBtnCustList.addActionListener(listener);
        this.mBtnCustAdd.addActionListener(listener);
        this.mBtnCustUpdate.addActionListener(listener);
        this.mBtnCustDelete.addActionListener(listener);
        this.mBtnProdList.addActionListener(listener);
        this.mBtnProdAdd.addActionListener(listener);
        this.mBtnProdUpdate.addActionListener(listener);
        this.mBtnProdDelete.addActionListener(listener);
        this.mBtnProdStockList.addActionListener(listener);
        this.mBtnProdStockAdjust.addActionListener(listener);
        this.mBtnDiscountAdjust.addActionListener(listener);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[LeftView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[LeftView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();
        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont2);

        for (int i = 0; i < this.mPanelCtr.getComponents().length; i++) {
            JComponent lb2 = (JComponent) this.mPanelCtr.getComponents()[i];
            lb2.setFont(main.mFont2);
        }

    }
}
