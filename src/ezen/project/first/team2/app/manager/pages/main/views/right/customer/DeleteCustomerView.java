package ezen.project.first.team2.app.manager.pages.main.views.right.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxBtn;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class DeleteCustomerView extends View {

    JLabel mLabelInfo = new JLabel("고객 데이터 삭제");

    CustomerManager custMngr = CustomerManager.getInstance();

    // 검색, 결과용 패널
    JPanel mPanelSearchDelete = new JPanel();
    // 고객 검색용 패널
    JPanel mPanelSearch = new JPanel();
    // 검색 결과용 패널
    JPanel mPanelResult = new JPanel();
    // 삭제할 고객 정보 패널
    JPanel mPanelCustInfoDelete = new JPanel();

    // 검색 컴포넌트
    JComboBox<String> mComboBoxSearchProperty;
    String[] properties = { "고객명", "휴대폰번호" };
    JTextField mTextFieldSearch = new JTextField(10);
    JButton mBtnSearch = new JButton("검색");
    // 검색결과 컴포넌트
    JTable mTableResultList;
    JScrollPane mScroll;

    // 제거할 고객정보 확인 패널
    JPanel mPanelPanelInfo = new JPanel();
    JLabel mLablePanelInfo = new JLabel("■ 고객 정보");

    JPanel mPanelDelInfoIdName = new JPanel();
    JLabel mLabelDelCustId = new JLabel("■ 고객 번호 : ");
    JLabel mLabelDelCustName = new JLabel("■ 고객명 : ");

    JPanel mPanelDelInfoBirthPhone = new JPanel();
    JLabel mLabelDelCustBirthday = new JLabel("■ 생년월일 : ");
    JLabel mLabelDelCustPhoneNum = new JLabel("■ 휴대폰 번호 : ");

    JPanel mPanelDelInfoPointRemark = new JPanel();
    JLabel mLabelDelCustPoint = new JLabel("■ 보유 포인트 : ");
    JLabel mLabelDelCustRemark = new JLabel("■ 비고 : ");

    // 확정 버튼
    JPanel mPanelDelBtn = new JPanel();
    JButton mBtnDeleteComplete = new JButton("제거 확정");

    public DeleteCustomerView() {
        super(MainPage.VIEW_NUM_CUST_DELETE);
    }

    @Override
    protected void onInit() {
        // 테이블 설정
        try {
            Object[] mPropertyColumn = {
                    "고객번호", "가입일", "고객명", "생년월일", "휴대폰번호", "보유 포인트", "비고"
            };
            Object[][] mProdListRows = new Object[mPropertyColumn.length][10];

            DefaultTableModel model = new DefaultTableModel(mProdListRows, mPropertyColumn) {
                // 셀 내용 수정 불가
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };

            mTableResultList = new JTable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelSearchDelete.setLayout(new BorderLayout());
        this.mPanelResult.setLayout(new GridLayout(2, 1));
        this.mPanelCustInfoDelete.setLayout(new BoxLayout(
                mPanelCustInfoDelete, BoxLayout.Y_AXIS));

        this.mPanelDelInfoIdName.setLayout(new GridLayout(1, 2));
        this.mPanelDelInfoBirthPhone.setLayout(new GridLayout(1, 2));
        this.mPanelDelInfoPointRemark.setLayout(new GridLayout(1, 2));
    }

    @Override
    protected void onAddCtrls() {
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        // 테이블 설정
        this.mTableResultList.getTableHeader().setReorderingAllowed(false);
        // 스크롤 설정
        this.mScroll = new JScrollPane(mTableResultList);
        this.mScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mScroll.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 30, 30));
        // 콤보박스 설정
        this.mComboBoxSearchProperty = new JComboBox<String>(properties);

        // 확정버튼 설정
        this.mBtnDeleteComplete.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 고객정보 패널 성정
        this.mPanelDelInfoIdName.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));
        this.mPanelDelInfoBirthPhone.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));
        this.mPanelDelInfoPointRemark.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));

        // 페이지에 추가
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelSearchDelete, BorderLayout.CENTER);

        this.mPanelSearchDelete.add(mPanelSearch, BorderLayout.NORTH);
        this.mPanelSearchDelete.add(mPanelResult, BorderLayout.CENTER);

        this.mPanelSearch.add(mComboBoxSearchProperty);
        this.mPanelSearch.add(mTextFieldSearch);
        this.mPanelSearch.add(mBtnSearch);

        this.mPanelResult.add(mScroll);
        this.mPanelResult.add(mPanelCustInfoDelete);

        this.mPanelCustInfoDelete.add(mPanelPanelInfo);
        this.mPanelPanelInfo.add(mLablePanelInfo);

        this.mPanelCustInfoDelete.add(mPanelDelInfoIdName);
        this.mPanelDelInfoIdName.add(mLabelDelCustId);
        this.mPanelDelInfoIdName.add(mLabelDelCustName);

        this.mPanelCustInfoDelete.add(mPanelDelInfoBirthPhone);
        this.mPanelDelInfoBirthPhone.add(mLabelDelCustBirthday);
        this.mPanelDelInfoBirthPhone.add(mLabelDelCustPhoneNum);

        this.mPanelCustInfoDelete.add(mPanelDelInfoPointRemark);
        this.mPanelDelInfoPointRemark.add(mLabelDelCustPoint);
        this.mPanelDelInfoPointRemark.add(mLabelDelCustRemark);

        this.mPanelCustInfoDelete.add(mPanelDelBtn);
        this.mPanelDelBtn.add(mBtnDeleteComplete);
    }

    @Override
    protected void onAddEventListeners() {
        mTextFieldSearch.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mPanelSearch.getRootPane().setDefaultButton(mBtnSearch);
                }
            }
        });

        mTableResultList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = mTableResultList.getSelectedRow();

                    int idColumn = 0;
                    int findId = (int) mTableResultList.getValueAt(row, idColumn);

                    try {
                        CustomerItem findedItem = custMngr.findById(findId);

                        searchItemAddTextField(findedItem);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        ActionListener listener = e -> {
            JButton btn = (JButton) e.getSource();

            if (btn == this.mBtnSearch) { // 상품검색
                String property = mComboBoxSearchProperty.getSelectedItem().toString();

                switch (property) {
                    case "고객명":
                        try {
                            String searchText = mTextFieldSearch.getText();

                            List<CustomerItem> custItemList = custMngr.findItemsByName(searchText);

                            searchItemAddtable(custItemList);

                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
                            // e1.printStackTrace();
                        }
                        break;

                    case "휴대폰번호":
                        try {
                            String searchText = mTextFieldSearch.getText();

                            List<CustomerItem> custItemList = custMngr.findItemsByPhoneNumber(searchText);

                            searchItemAddtable(custItemList);
                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
                            // e1.printStackTrace();
                        }
                        break;
                }
            } else if (btn == this.mBtnDeleteComplete) {
                try {
                    UiUtils.showMsgBox("정말 삭제 하시겠습니까?", "경고", MsgBoxType.Warn, MsgBoxBtn.YesNo);

                    String findIdStr = mLabelDelCustId.getText().substring(10);
                    int findId = Integer.valueOf(findIdStr);

                    custMngr.deleteById(findId);

                    // 삭제를 진행한 아이템 row만 테이블에서 제거
                    DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
                    for (int row = 0; row < mTableResultList.getRowCount(); row++) {
                        if ((int) mTableResultList.getValueAt(row, 0) == findId) {
                            // System.out.println("table id : " + (int) mTableResultList.getValueAt(row, 0)
                            // + ", row : " + row + ", findId : " + findId);
                            m.removeRow(row);
                            break;
                        }
                    }

                    // 완료되면 레이블을 지운다
                    initializeTextField();

                    UiUtils.showMsgBox("삭제 완료", "", MsgBoxType.Info);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };

        this.mBtnSearch.addActionListener(listener);
        this.mBtnDeleteComplete.addActionListener(listener);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[DeleteCustmerView.onShow()]");

        insertItemsIntoTable();
        initializeTextField();
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

        this.mLablePanelInfo.setFont(main.mFont2);
        this.mLabelDelCustBirthday.setFont(main.mFont2);
        this.mLabelDelCustId.setFont(main.mFont2);
        this.mLabelDelCustName.setFont(main.mFont2);
        this.mLabelDelCustPhoneNum.setFont(main.mFont2);
        this.mLabelDelCustPoint.setFont(main.mFont2);
        this.mLabelDelCustRemark.setFont(main.mFont2);

        this.mBtnDeleteComplete.setFont(main.mFont2);

        this.mTableResultList.setFont(main.mFont3);
    }

    // 고객목록 테이블에 추가하는 메소드
    private void insertItemsIntoTable() {
        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
        m.setRowCount(0);
        try {
            custMngr.iterate((info, idx) -> {
                m.addRow(new Object[] {
                        info.getId(), info.getJoinDate(),
                        info.getName(), info.getBirthday(),
                        info.getPhoneNumber(), info.getPoint(), info.getRemark()
                });

                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        mTableResultList.updateUI();
    }

    // 검색한 결과를 테이블에 추가
    private void searchItemAddtable(List<CustomerItem> custItemList) {

        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();

        m.setRowCount(0);
        for (CustomerItem info : custItemList) {

            m.addRow(new Object[] {
                    info.getId(), info.getJoinDate(),
                    info.getName(), info.getBirthday(),
                    info.getPhoneNumber(), info.getPoint(), info.getRemark()
            });
        }

        mTableResultList.updateUI();
    }

    // 검색창과 라벨을 초기화
    private void initializeTextField() {
        this.mTextFieldSearch.setText("");
        this.mLabelDelCustId.setText("■ 고객 번호 : ");
        this.mLabelDelCustName.setText("■ 고객명 : ");
        this.mLabelDelCustBirthday.setText("■ 생년월일 : ");
        this.mLabelDelCustPhoneNum.setText("■ 휴대폰 번호 : ");
        this.mLabelDelCustPoint.setText("■ 보유 포인트 : ");
        this.mLabelDelCustRemark.setText("■ 비고 : ");
    }

    // 검색한 결과를 라벨에 설정
    private void searchItemAddTextField(CustomerItem custItem) {
        initializeTextField();

        // UiUtils.showMsgBox(String.format(
        // "삭제할 항목은\n[ 고객번호[%s] 고객명[%s] ] 입니다\n",
        // custItem.getId(),
        // custItem.getName()), "");

        this.mLabelDelCustId.setText(
                mLabelDelCustId.getText() + custItem.getId());
        this.mLabelDelCustName.setText(
                mLabelDelCustName.getText() + custItem.getName());
        this.mLabelDelCustBirthday.setText(
                mLabelDelCustBirthday.getText() + custItem.getBirthdayStr());
        this.mLabelDelCustPhoneNum.setText(
                mLabelDelCustPhoneNum.getText() + custItem.getPhoneNumber());
        this.mLabelDelCustPoint.setText(
                mLabelDelCustPoint.getText() + custItem.getPoint());
        this.mLabelDelCustRemark.setText(
                mLabelDelCustRemark.getText() + custItem.getRemark());
    }

}