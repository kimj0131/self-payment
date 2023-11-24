package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AddProductView extends View {
    DecimalFormat df = new DecimalFormat("###,###");

    JLabel mLabelInfo = new JLabel("상품 데이터 추가");

    ProductManagerMem prodMngr = ProductManagerMem.getInstance();

    // 상품 리스트, 상품 추가용 패널
    JPanel mPanelListAdd = new JPanel();
    // 상품 리스트 패널
    JPanel mPanelList = new JPanel();
    // 상품 추가 패널
    JPanel mPanelAdd = new JPanel();

    // 상품 추가 컴포넌트
    JLabel mLabelPanelInfo = new JLabel("추가할 상품 데이터 입력");

    JPanel mPanelAddIdCode = new JPanel();
    JLabel mLabelAddProdId = new JLabel("상품 번호");
    JTextField mTextFieldAddProdId = new JTextField(5);
    JPanel mPanelAddCode = new JPanel();
    JComboBox<String> mComboBoxCodeType;

    JLabel mLabelAddProdCode = new JLabel("상품 코드");
    JTextField mTextFieldAddProdCode = new JTextField(5);

    JPanel mPanelAddNamePrice = new JPanel();
    JLabel mLabelAddProdName = new JLabel("상품 이름");
    JTextField mTextFieldAddProdName = new JTextField(10);
    JLabel mLabelAddProdPrice = new JLabel("상품 가격");
    JTextField mTextFieldAddProdPrice = new JTextField(10);

    JPanel mPanelAddRegDateDesc = new JPanel();
    JLabel mLabelAddProdRegDate = new JLabel("입고 날짜");
    DateFormat format = new SimpleDateFormat("yyyuMMdd");
    JFormattedTextField mTextFieldAddProdRegDate = new JFormattedTextField(format);
    JLabel mLabelAddProdDesc = new JLabel("비 고");
    JTextField mTextFieldAddProdDesc = new JTextField(20);

    JButton mBtnAddProdComplete = new JButton("상품 추가");

    // 상품 리스트 테이블
    JTable mTableProdList;
    JScrollPane mScroll;

    public AddProductView() {
        super(MainPage.VIEW_NUM_PROD_ADD);
    }

    @Override
    protected void onInit() {

        // 테이블 설정
        try {
            Object[] mPropertyColumn = {
                    "상품번호", "상품코드", "상품명", "가격", "등록일", "설명"
            };
            Object[][] mProdListRows = new Object[mPropertyColumn.length][prodMngr.getCount()];

            DefaultTableModel model = new DefaultTableModel(mProdListRows, mPropertyColumn) {
                // 셀 내용 수정 불가
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };

            mTableProdList = new JTable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelListAdd.setLayout(new GridLayout(2, 1));
        this.mPanelAdd.setLayout(new BoxLayout(mPanelAdd, BoxLayout.Y_AXIS));
    }

    @Override
    protected void onAddCtrls() {
        // 컴포넌트 설정
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);
        // 테이블
        this.mTableProdList.getTableHeader().setReorderingAllowed(false);
        // 스크롤 설정
        this.mScroll = new JScrollPane(mTableProdList);
        this.mScroll.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 30, 30));
        // 콤보박스
        String[] Types = { "과자", "라면", "주류", "과일", "채소" };
        this.mComboBoxCodeType = new JComboBox<String>(Types);
        // 상품 번호, 상품 코드 접근 비활성화
        this.mTextFieldAddProdId.setEnabled(false);
        this.mTextFieldAddProdCode.setEnabled(false);

        this.mTextFieldAddProdRegDate.setColumns(10);
        try {
            // 상품번호는 자동으로 다음번호를 받는다.
            this.mTextFieldAddProdId.setText(String.valueOf(prodMngr.getNextID()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 컴포넌트 추가
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelListAdd, BorderLayout.CENTER);

        this.mPanelListAdd.add(mPanelList);
        this.mPanelListAdd.add(mPanelAdd);
        this.mPanelList.add(mScroll);

        // 상품추가 패널
        this.mPanelAdd.add(mLabelPanelInfo);
        this.mPanelAdd.add(mPanelAddIdCode);
        this.mPanelAdd.add(mPanelAddNamePrice);
        this.mPanelAdd.add(mPanelAddRegDateDesc);

        this.mPanelAddIdCode.add(mLabelAddProdId);
        this.mPanelAddIdCode.add(mTextFieldAddProdId);
        this.mPanelAddIdCode.add(mLabelAddProdCode);
        this.mPanelAddIdCode.add(mComboBoxCodeType);
        this.mPanelAddIdCode.add(mTextFieldAddProdCode);

        this.mPanelAddNamePrice.add(mLabelAddProdName);
        this.mPanelAddNamePrice.add(mTextFieldAddProdName);
        this.mPanelAddNamePrice.add(mLabelAddProdPrice);
        this.mPanelAddNamePrice.add(mTextFieldAddProdPrice);

        this.mPanelAddRegDateDesc.add(mLabelAddProdRegDate);
        this.mPanelAddRegDateDesc.add(mTextFieldAddProdRegDate);
        this.mPanelAddRegDateDesc.add(mLabelAddProdDesc);
        this.mPanelAddRegDateDesc.add(mTextFieldAddProdDesc);

        this.mPanelAdd.add(mBtnAddProdComplete);
    }

    @Override
    protected void onAddEventListeners() {

        ActionListener listener = e -> {
            if (e.getSource() == mComboBoxCodeType) {
                mComboBoxCodeType.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // 상품코드는 타입을 확인한 후 다음시리얼을 받는다.
                        int nextSerial;
                        try {
                            nextSerial = prodMngr.getCount();
                            mTextFieldAddProdCode.setText(getProductCodeType() + nextSerial);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
        };

        this.mComboBoxCodeType.addActionListener(listener);

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AddProductView.onShow()]");
    }

    @Override
    protected void onHide() {
        System.out.println("[AddProductView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);
    }

    // 상품코드 타입 받아오기
    private String getProductCodeType() {

        String type = this.mComboBoxCodeType.getSelectedItem().toString();

        switch (type) {
            case "과자":
                return "S";
            case "라면":
                return "R";
            case "주류":
                return "D";
            case "과일":
                return "F";
            case "채소":
                return "V";
        }
        return "";
    }
}