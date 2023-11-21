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
    JLabel mLabelLogo = new JLabel("##  Logo Title  ##");
    // 하단 조작 패널
    JPanel mCtrPanel = new JPanel();
    // 하단 패널 분할

    // 회원관리
    JLabel mCustmorLabel = new JLabel("   ■ 회원 관리    ");
    JButton mCustList = new JButton("   회원 조회    ");
    JButton mCustAdd = new JButton("   회원 추가    ");
    JButton mCustUpdate = new JButton("   회원 수정    ");
    JButton mCustDelete = new JButton("   회원 삭제    ");
    // 상품관리
    JLabel mProductLabel = new JLabel("   ■ 상품 관리    ");
    JButton mProdList = new JButton("   상품 조회    ");
    JButton mProdAdd = new JButton("   상품 추가    ");
    JButton mProdUpdate = new JButton("   상품 수정    ");
    JButton mProdDelete = new JButton("   상품 삭제    ");
    // 재고관리
    JLabel mProdStockLabel = new JLabel("   ■ 재고 관리    ");
    JButton mProdStockList = new JButton("   재고 조회    ");
    JButton mProdStockUpdate = new JButton("   재고 조정    ");

    public LeftView() {
        super(MainPage.VIEW_NUM_LEFT);
    }

    @Override
    protected void onInit() {
        // this.setBackground(Color.DARK_GRAY);
        this.mCtrPanel.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mCtrPanel.setLayout(new BoxLayout(mCtrPanel, BoxLayout.PAGE_AXIS));
        // this.mCtrPanel.setLayout(new GridLayout(10, 1));
    }

    @Override
    protected void onAddCtrls() {
        // 컴포넌트 가운데 정렬
        this.mCustmorLabel.setHorizontalAlignment(JLabel.CENTER);
        this.mCustList.setHorizontalAlignment(JButton.CENTER);

        this.add(mLabelLogo, BorderLayout.NORTH);
        this.add(mCtrPanel, BorderLayout.CENTER);

        // 회원
        this.mCtrPanel.add(mCustmorLabel);
        this.mCtrPanel.add(mCustList);
        this.mCtrPanel.add(mCustAdd);
        this.mCtrPanel.add(mCustUpdate);
        this.mCtrPanel.add(mCustDelete);
        // 여백삽입 (박스레이아웃)
        this.mCtrPanel.add(Box.createVerticalStrut(50));
        // 상품
        this.mCtrPanel.add(mProductLabel);
        this.mCtrPanel.add(mProdList);
        this.mCtrPanel.add(mProdAdd);
        this.mCtrPanel.add(mProdUpdate);
        this.mCtrPanel.add(mProdDelete);
        // 여백삽입
        this.mCtrPanel.add(Box.createVerticalStrut(50));
        // 재고
        this.mCtrPanel.add(mProdStockLabel);
        this.mCtrPanel.add(mProdStockList);
        this.mCtrPanel.add(mProdStockUpdate);
    }

    @Override
    protected void onAddEventListeners() {
        ActionListener listener = e -> {
            try {
                MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);

                JButton btn = (JButton) e.getSource();
                if (btn == this.mCustList) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_LIST);
                } else if (btn == this.mCustAdd) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_ADD);
                } else if (btn == this.mCustUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_UPDATE);
                } else if (btn == this.mCustDelete) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_DELETE);
                } else if (btn == this.mProdList) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_LIST);
                } else if (btn == this.mProdAdd) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_ADD);
                } else if (btn == this.mProdUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_UPDATE);
                } else if (btn == this.mProdDelete) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_DELETE);
                } else if (btn == this.mProdStockList) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_STOCK_LIST);
                } else if (btn == this.mProdStockUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_STOCK_UPDATE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        this.mCustList.addActionListener(listener);
        this.mCustAdd.addActionListener(listener);
        this.mCustUpdate.addActionListener(listener);
        this.mCustDelete.addActionListener(listener);
        this.mProdList.addActionListener(listener);
        this.mProdAdd.addActionListener(listener);
        this.mProdUpdate.addActionListener(listener);
        this.mProdDelete.addActionListener(listener);
        this.mProdStockList.addActionListener(listener);
        this.mProdStockUpdate.addActionListener(listener);
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

        for (int i = 0; i < this.mCtrPanel.getComponents().length; i++) {
            JComponent lb2 = (JComponent) this.mCtrPanel.getComponents()[i];
            lb2.setFont(main.mFont2);
        }

    }
}
