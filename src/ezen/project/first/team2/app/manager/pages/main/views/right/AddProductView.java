package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.time.LocalDate;

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
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
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

    JPanel mPanelAddDesc = new JPanel();
    JLabel mLabelAddProdDesc = new JLabel("비 고");
    JTextField mTextFieldAddProdDesc = new JTextField(20);

    // 상품 등록버튼
    JPanel mPanelAddBtn = new JPanel();
    JButton mBtnAddProdComplete = new JButton("상품 등록");

    //
    // [테스트] 상품정보 자동기입버튼
    JButton mBtnAddTextFieldProd = new JButton("[테스트]자동기입");
    //

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
            Object[][] mProdListRows = new Object[mPropertyColumn.length][10];

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
        this.mPanelList.setLayout(new BorderLayout());
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
        this.mScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mScroll.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 30, 30));
        // 콤보박스
        String[] Types = { "과자", "라면", "주류", "과일", "채소" };
        this.mComboBoxCodeType = new JComboBox<String>(Types);
        // 상품 번호, 상품 코드, 등록 날짜 접근 비활성화
        this.mTextFieldAddProdId.setEnabled(false);
        this.mTextFieldAddProdCode.setEnabled(false);

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
        this.mPanelAdd.add(mPanelAddDesc);

        this.mPanelAddIdCode.add(mLabelAddProdId);
        this.mPanelAddIdCode.add(mTextFieldAddProdId);
        this.mPanelAddIdCode.add(mLabelAddProdCode);
        this.mPanelAddIdCode.add(mComboBoxCodeType);
        this.mPanelAddIdCode.add(mTextFieldAddProdCode);

        this.mPanelAddNamePrice.add(mLabelAddProdName);
        this.mPanelAddNamePrice.add(mTextFieldAddProdName);
        this.mPanelAddNamePrice.add(mLabelAddProdPrice);
        this.mPanelAddNamePrice.add(mTextFieldAddProdPrice);

        this.mPanelAddDesc.add(mLabelAddProdDesc);
        this.mPanelAddDesc.add(mTextFieldAddProdDesc);

        this.mPanelAdd.add(mPanelAddBtn);
        this.mPanelAddBtn.add(mBtnAddProdComplete);
        //
        this.mPanelAddBtn.add(mBtnAddTextFieldProd);
        //
    }

    @Override
    protected void onAddEventListeners() {

        mComboBoxCodeType.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Object type = e.getItem();

                try {
                    int nextSerial = prodMngr.getCount() + 1;
                    mTextFieldAddProdCode.setText(String.format("%s%03d",
                            getProductCodeType(String.valueOf(type)), nextSerial));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        ActionListener listener = e -> {

            // 테스트버튼
            if (e.getSource() == mBtnAddTextFieldProd) {
                this.setTestProdValue();
            }
            if (mTextFieldAddProdCode.getText().length() > 0 &&
                    mTextFieldAddProdName.getText().length() > 0 &&
                    mTextFieldAddProdPrice.getText().length() > 0) {

                if (e.getSource() == mBtnAddProdComplete) {
                    this.setProdValue();
                    try {
                        this.mTextFieldAddProdId.setText(String.valueOf(prodMngr.getNextID()));
                        // 테이블 갱신
                        insertItemTable();
                        // 텍스트필드 초기화
                        allTextFieldInitialize();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                // 미입력항목 있을 시 경고
                UiUtils.showMsgBox("비고를 제외하고 모두 입력해야 합니다.",
                        "", MsgBoxType.Warn);
            }

        };

        // 추가완료 버튼 활성
        this.mBtnAddProdComplete.addActionListener(listener);

        // 테스트버튼 추가
        this.mBtnAddTextFieldProd.addActionListener(listener);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AddProductView.onShow()]");

        // 텍스트필드 비워놓기
        allTextFieldInitialize();

        // 상품목록을 테이블에 추가
        insertItemTable();
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

    // textField에 작성한 내용울 추가
    private void setProdValue() {
        ProductItem productItem = new ProductItem();

        try {
            int prodId = prodMngr.getNextID();
            ProductCode prodCode = new ProductCode(mTextFieldAddProdCode.getText());
            LocalDate prodRegDate = LocalDate.now();
            String prodName = mTextFieldAddProdName.getText();
            int prodPrice = Integer.valueOf(mTextFieldAddProdPrice.getText());
            String prodDesc = mTextFieldAddProdDesc.getText();

            productItem.setValues(prodId, prodCode, prodRegDate,
                    prodName, prodPrice, prodDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            prodMngr.add(productItem);
            System.out.println("prod Add");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 상품코드 타입 받아오기
    private String getProductCodeType(String type) {
        switch (type) {
            case "과자":
                return ProductCode.typeToStr(ProductCode.Type.Snack);
            case "라면":
                return ProductCode.typeToStr(ProductCode.Type.Ramen);
            case "주류":
                return ProductCode.typeToStr(ProductCode.Type.Drink);
            case "과일":
                return ProductCode.typeToStr(ProductCode.Type.Fruit);
            case "채소":
                return ProductCode.typeToStr(ProductCode.Type.Vegetable);
        }
        return "";
    }

    // 상품목록을 테이블에 추가하는 메소드
    private void insertItemTable() {
        DefaultTableModel m = (DefaultTableModel) mTableProdList.getModel();
        m.setRowCount(0);
        try {
            prodMngr.iterate((info, idx) -> {
                m.addRow(new Object[] {
                        info.getId(), info.getProdCodeStr(),
                        info.getName(), df.format(info.getPrice()),
                        info.getRegDateStr(), info.getDesc()
                });
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTableProdList.updateUI();
    }

    // 텍스트 필드 비우기
    private void allTextFieldInitialize() {
        // this.mTextFieldAddProdId.setText("");
        this.mTextFieldAddProdCode.setText("");
        this.mTextFieldAddProdName.setText("");
        this.mTextFieldAddProdPrice.setText("");
        this.mTextFieldAddProdDesc.setText("");
    }

    private void setTestProdValue() {
        try {
            int nextId = prodMngr.getNextID();
            this.mTextFieldAddProdId.setText(String.valueOf(nextId));

            int nextSerial = prodMngr.getCount() + 1;
            mTextFieldAddProdCode.setText(String.format("%s%03d",
                    "S", nextSerial));

            this.mTextFieldAddProdName.setText("새우깡");
            int rdPrice = (int) (Math.random() * 1000 + 1000);
            this.mTextFieldAddProdPrice.setText(String.valueOf(rdPrice));
            this.mTextFieldAddProdDesc.setText("새우로 만든 스낵");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}