package ezen.project.first.team2.app.manager.pages.main.views.right.custmer;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class ListCustmerView extends View {
    CustomerManagerMem custMngr = CustomerManagerMem.getInstance();

    JLabel mLabelInfo = new JLabel("고객 조회");

    // 고객리스트를 넣을 패널생성
    JPanel mPanelCustList = new JPanel();
    // 고객리스트 테이블
    JTable mTableCustList;
    // 스크롤 삽입
    JScrollPane mScroll;

    public ListCustmerView() {
        super(MainPage.VIEW_NUM_CUST_LIST);
    }

    @Override
    protected void onInit() {

        try {
            Object[] mPropertyColumn = {
                    "고객번호", "가입일", "고객명", "생년월일", "전화번호", "비고"
            };
            Object[][] mCustListRows = new Object[mPropertyColumn.length][custMngr.getCount()];

            DefaultTableModel model = new DefaultTableModel(mCustListRows, mPropertyColumn) {
                // 셀의 내용을 수정하지 못하게 설정
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mTableCustList = new JTable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelCustList.setLayout(new BorderLayout());
    }

    @Override
    protected void onAddCtrls() {

        // 열 이동불가
        this.mTableCustList.getTableHeader().setReorderingAllowed(false);

        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        this.mScroll = new JScrollPane(mTableCustList);
        this.mScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mScroll.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelCustList, BorderLayout.CENTER);
        this.mPanelCustList.add(mScroll, BorderLayout.CENTER);
    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[ListCustmerView.onShow()]");

        DefaultTableModel m = (DefaultTableModel) mTableCustList.getModel();
        m.setRowCount(0);
        try {
            custMngr.iterate((info, idx) -> {
                m.addRow(new Object[] {
                        info.getId(), info.getJoinDate(),
                        info.getName(), info.getBirthday(),
                        info.getPhoneNumber(), info.getRemark()
                });

                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        mTableCustList.updateUI();

    }

    @Override
    protected void onHide() {
        System.out.println("[ListCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont0);
    }

}