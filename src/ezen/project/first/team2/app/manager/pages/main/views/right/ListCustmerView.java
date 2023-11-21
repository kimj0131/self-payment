package ezen.project.first.team2.app.manager.pages.main.views.right;

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

    JLabel mLabelInfo = new JLabel("회원 조회뷰 초기화면입니다");

    // 고객 리스트를 테이블로 출력
    JTable mCustLisTable;
    // 고객리스트를 출력할 패널생성
    JPanel mPanelAttributeList = new JPanel();
    // 스크롤 삽입
    JScrollPane mScroll;

    public ListCustmerView() {
        super(MainPage.VIEW_NUM_CUST_LIST);
    }

    @Override
    protected void onInit() {

        try {
            Object[] mAttributesColumn = {
                    "회원 번호", "가입일", "이름", "생년월일", "전화번호", "비고" };
            Object[][] mCustListRows = new Object[mAttributesColumn.length][custMngr.getCount()];

            DefaultTableModel model = new DefaultTableModel(mCustListRows, mAttributesColumn) {
                // 셀의 내용을 수정하지 못하게 설정
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mCustLisTable = new JTable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelAttributeList.setLayout(new BorderLayout());
    }

    @Override
    protected void onAddCtrls() {

        // 열 이동불가
        this.mCustLisTable.getTableHeader().setReorderingAllowed(false);
        // 열 크기조절불가
        // this.mCustLisTable.getTableHeader().setResizingAllowed(false);
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        this.mScroll = new JScrollPane(mCustLisTable);
        this.mScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // this.mAttributes.setBorder(
        // BorderFactory.createEmptyBorder(0, 30, 0, 30));

        this.mScroll.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelAttributeList, BorderLayout.CENTER);
        this.mPanelAttributeList.add(mScroll, BorderLayout.CENTER);
    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[ListCustmerView.onShow()]");

        CustomerManagerMem custMngr = CustomerManagerMem.getInstance();

        DefaultTableModel m = (DefaultTableModel) mCustLisTable.getModel();
        m.setRowCount(0);
        try {
            custMngr.iterate((info, idx) -> {
                try {

                    // m.insertRow(idx, new Object[] { info.getId(), info.getJoinDate(),
                    m.addRow(new Object[] { info.getId(), info.getJoinDate(),
                            info.getName(), info.getBirthday(),
                            info.getPhoneNumber(), info.getRemark() });

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        mCustLisTable.updateUI();

    }
    // mCustmerList.append(String.format("%06d\t%s\t%s\t%s\t%s\t %s\n",
    // info.getId(), info.getJoinDate(), info.getName(),
    // info.getBirthday(), info.getPhoneNumber(), info.getRemark()));

    @Override
    protected void onHide() {
        System.out.println("[ListCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont2);

    }

}