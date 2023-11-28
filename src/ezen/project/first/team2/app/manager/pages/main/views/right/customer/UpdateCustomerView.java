package ezen.project.first.team2.app.manager.pages.main.views.right.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class UpdateCustomerView extends View {

    CustomerManager custMngr = CustomerManager.getInstance();

    JLabel mLabelInfo = new JLabel("고객 데이터 수정");

    // 검색, 결과, 수정용 패널
    JPanel mPanelSearchUpdate = new JPanel();
    // 고객 검색용 패널
    JPanel mPanelSearch = new JPanel();
    // 검색결과용 패널
    JPanel mPanelResult = new JPanel();
    // 고객정보 수정패널
    JPanel mPanelInformationUpdate = new JPanel();

    // 검색 컴포넌트
    JComboBox<String> mComboBoxSearchProperty;
    String[] properties = { "고객명", "휴대폰번호" };
    JTextField mTextFieldSearch = new JTextField(10);
    JButton mBtnSearch = new JButton("검색");
    // 검색결과 컴포넌트
    JTable mTableResultList;
    JScrollPane mScroll;

    // 고객정보 수정 컴포넌트
    JPanel mPanelPanelInfo = new JPanel();
    JLabel mLabelPanelInfo = new JLabel("<html>상단 리스트에서 더블클릭<br><center>■ 고객 정보</center></html>");

    JPanel mPanelUpdateIdName = new JPanel();
    JLabel mLabelUpdateCustId = new JLabel("고객 번호");
    JTextField mTextFieldUpdateCustId = new JTextField(5);
    JLabel mLabelUpdateCustName = new JLabel("고객명");
    JTextField mTextFieldUpdateCustName = new JTextField(10);

    JPanel mPanelUpdateBirthPhone = new JPanel();
    JLabel mLabelUpdateBirthday = new JLabel("생년월일 : ");
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    JFormattedTextField mTextFieldUpdateBirthday = new JFormattedTextField(format);
    JLabel mLabelUpdatePhoneNum = new JLabel("휴대폰번호 : ");
    JTextField mTextFieldUpdatePhoneNum = new JTextField(10);

    JPanel mPanelUpdatePointRemark = new JPanel();
    JLabel mLabelUpdatePoing = new JLabel("포인트 : ");

    JLabel mLabelUpdateRemark = new JLabel("비 고 : ");
    JTextField mTextFieldUpdateRemark = new JTextField(20);

    JPanel mPanelUpdateBtn = new JPanel();
    JButton mBtnUpdateComplete = new JButton("수정 확정");

    public UpdateCustomerView() {
        super(MainPage.VIEW_NUM_CUST_UPDATE);
    }

    @Override
    protected void onInit() {

        try {
            Object[] mPropertyColumn = {
                    "고객번호", "가입일", "고객명", "생년월일", "휴대폰번호", "비고" };
            Object[][] mCustListRows = new Object[mPropertyColumn.length][custMngr.getCount()];

            DefaultTableModel model = new DefaultTableModel(mCustListRows, mPropertyColumn) {
                // 셀의 내용을 수정하지 못하게 설정
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mTableResultList = new JTable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelSearchUpdate.setLayout(new BorderLayout());
        this.mPanelResult.setLayout(new GridLayout(2, 1));
        this.mPanelInformationUpdate.setLayout(new BoxLayout(
                mPanelInformationUpdate, BoxLayout.Y_AXIS));

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
        this.mBtnUpdateComplete.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // 비활성화할 텍스트필드
        this.mTextFieldUpdateCustId.setEnabled(false);

        this.mTextFieldUpdateBirthday.setColumns(10);

        this.mPanelInformationUpdate.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelSearchUpdate);

        this.mPanelSearchUpdate.add(mPanelSearch, BorderLayout.NORTH);
        this.mPanelSearchUpdate.add(mPanelResult, BorderLayout.CENTER);

        this.mPanelSearch.add(mComboBoxSearchProperty);
        this.mPanelSearch.add(mTextFieldSearch);
        this.mPanelSearch.add(mBtnSearch);

        this.mPanelResult.add(mScroll);
        this.mPanelResult.add(mPanelInformationUpdate);

        // 고객정보 수정 패널
        this.mPanelInformationUpdate.add(mPanelPanelInfo);
        this.mPanelPanelInfo.add(mLabelPanelInfo);

        this.mPanelInformationUpdate.add(mPanelUpdateIdName);
        this.mPanelInformationUpdate.add(mPanelUpdateBirthPhone);
        this.mPanelInformationUpdate.add(mPanelUpdatePointRemark);

        this.mPanelUpdateIdName.add(mLabelUpdateCustId);
        this.mPanelUpdateIdName.add(mTextFieldUpdateCustId);
        this.mPanelUpdateIdName.add(mLabelUpdateCustName);
        this.mPanelUpdateIdName.add(mTextFieldUpdateCustName);

        this.mPanelUpdateBirthPhone.add(mLabelUpdateBirthday);
        this.mPanelUpdateBirthPhone.add(mTextFieldUpdateBirthday);
        this.mPanelUpdateBirthPhone.add(mLabelUpdatePhoneNum);
        this.mPanelUpdateBirthPhone.add(mTextFieldUpdatePhoneNum);

        this.mPanelUpdatePointRemark.add(mLabelUpdateRemark);
        this.mPanelUpdatePointRemark.add(mTextFieldUpdateRemark);

        this.mPanelInformationUpdate.add(mPanelUpdateBtn);
        this.mPanelUpdateBtn.add(mBtnUpdateComplete);
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
            } else if (btn == mBtnUpdateComplete) {
                try {
                    int updateId = Integer.valueOf(mTextFieldUpdateCustId.getText());

                    CustomerItem updateItem = custMngr.findById(updateId);
                    updateItem.setName(mTextFieldUpdateCustName.getText());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDate updateDate = LocalDate.parse(mTextFieldUpdateBirthday.getName(), formatter);
                    updateItem.setBirthday(updateDate);
                    updateItem.setPhoneNumber(mTextFieldUpdatePhoneNum.getText());
                    updateItem.setRemark(mTextFieldUpdateRemark.getText());

                    UiUtils.showMsgBox("수정 완료", "");

                    // 테이블 갱신
                    insertItemTable();
                    // 완료되면 레이블을 지운다
                    allTextFieldInitialize();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };

        this.mBtnSearch.addActionListener(listener);
        this.mBtnUpdateComplete.addActionListener(listener);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[ListCustmerView.onShow()]");

        // 텍스트필드 비워놓기
        allTextFieldInitialize();

        insertItemTable();
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

    // 검색한 결과를 텍스트 필드에 추가
    private void searchItemAddTextField(CustomerItem custItem) {

        UiUtils.showMsgBox(String.format(
                "수정할 항목은\n[ 고객번호[%s] 고객명[%s] ] 입니다\n",
                custItem.getId(),
                custItem.getName()), "");

        this.mTextFieldUpdateCustId.setText(String.valueOf(custItem.getId()));
        this.mTextFieldUpdateCustName.setText(custItem.getName());
        this.mTextFieldUpdateBirthday.setText(custItem.getBirthdayStr());
        this.mTextFieldUpdatePhoneNum.setText(custItem.getPhoneNumber());
        this.mTextFieldUpdateRemark.setText(custItem.getRemark());
    }

    // 고객목록을 테이블에 추가하는 메소드
    private void insertItemTable() {
        CustomerManager custMngr = CustomerManager.getInstance();

        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
        m.setRowCount(0);
        try {
            custMngr.iterate((info, idx) -> {
                try {

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

        mTableResultList.updateUI();
    }

    // 검색한 결과를 테이블에 추가
    private void searchItemAddtable(List<CustomerItem> custItemList) {

        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
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

        mTableResultList.updateUI();
    }

    // 텍스트 필드 비우기
    private void allTextFieldInitialize() {
        this.mTextFieldUpdateCustId.setText("");
        this.mTextFieldUpdateCustName.setText("");
        this.mTextFieldUpdateBirthday.setText("");
        this.mTextFieldUpdatePhoneNum.setText("");
        this.mTextFieldUpdateRemark.setText("");
    }

}