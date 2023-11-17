package ezen.project.first.team2.app.manager.pages.main.views.left;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.manager.pages.main.MainPage;
import ezen.project.first.team2.app.manager.pages.main.views.MainView;

public class LeftView extends View {

    // 상단 로고 라벨
    JLabel mLabelLogo = new JLabel("##  Logo Title  ##");
    // 하단 조작 패널
    JPanel mCtrPanel = new JPanel();
    // 회원관리
    JLabel mCustmorLabel = new JLabel("■ 회원 관리");
    JButton mCustInfo = new JButton("회원 조회");
    JButton mCustAdd = new JButton("회원 추가");
    JButton mCustUpdate = new JButton("회원 수정");
    JButton mCustDelete = new JButton("회원 삭제");
    // 상품관리
    JLabel mProductLabel = new JLabel("■ 상품 관리");
    JButton mProdInfo = new JButton("상품 조회");
    JButton mProdAdd = new JButton("상품 추가");
    JButton mProdUpdate = new JButton("상품 수정");
    JButton mProdDelete = new JButton("상품 삭제");

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
    }

    @Override
    protected void onAddCtrls() {
        this.add(mLabelLogo, BorderLayout.NORTH);
        this.add(mCtrPanel, BorderLayout.CENTER);

        // 회원
        this.mCtrPanel.add(mCustmorLabel);
        this.mCtrPanel.add(mCustInfo);
        this.mCtrPanel.add(mCustAdd);
        this.mCtrPanel.add(mCustUpdate);
        this.mCtrPanel.add(mCustDelete);
        // 상품
        this.mCtrPanel.add(mProductLabel);
        this.mCtrPanel.add(mProdInfo);
        this.mCtrPanel.add(mProdAdd);
        this.mCtrPanel.add(mProdUpdate);
        this.mCtrPanel.add(mProdDelete);
    }

    @Override
    protected void onAddEventListeners() {
        ActionListener listener = e -> {
            try {
                MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);

                JButton btn = (JButton) e.getSource();
                if (btn == this.mCustInfo) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_INFO);
                } else if (btn == this.mCustAdd) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_ADD);
                } else if (btn == this.mCustUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_UPDATE);
                } else if (btn == this.mCustDelete) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_CUST_DELETE);
                } else if (btn == this.mProdInfo) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_INFO);
                } else if (btn == this.mProdAdd) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_ADD);
                } else if (btn == this.mProdUpdate) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_UPDATE);
                } else if (btn == this.mProdDelete) {
                    mainView.setSelectedRightViewByNum(MainPage.VIEW_NUM_PROD_DELETE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        this.mCustInfo.addActionListener(listener);
        this.mCustAdd.addActionListener(listener);
        this.mCustUpdate.addActionListener(listener);
        this.mCustDelete.addActionListener(listener);
        this.mProdInfo.addActionListener(listener);
        this.mProdAdd.addActionListener(listener);
        this.mProdUpdate.addActionListener(listener);
        this.mProdDelete.addActionListener(listener);
    }

    @Override
    protected void onShow() {
    }

    @Override
    protected void onHide() {
    }
}
