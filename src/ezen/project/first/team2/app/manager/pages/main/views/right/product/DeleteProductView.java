package ezen.project.first.team2.app.manager.pages.main.views.right.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class DeleteProductView extends View {

    DecimalFormat df = new DecimalFormat("###,###");

    JLabel mLabelInfo = new JLabel("상품 데이터 삭제");

    ProductManager prodMngr = ProductManager.getInstance();

    // 검색, 결과용 패널
    JPanel mPanelSearchDelete = new JPanel();
    // 상품검색용 패널
    JPanel mPanelSearch = new JPanel();
    // 검색결과용 패널
    JPanel mPanelResult = new JPanel();
    // 삭제할 상품 정보 패널
    JPanel mPanelProdInfoDelete = new JPanel();

    // 검색 컴포넌트
    JComboBox<String> mComboBoxSearchProperty;
    String[] properties = { "상품명", "상품코드" };
    JTextField mTextFieldSearch = new JTextField(10);
    JButton mBtnSearch = new JButton("상품검색");
    // 검색결과 컴포넌트
    JTable mTableResultList;
    JScrollPane mScroll;

    // 제거할 상품정보 확인패널
    JPanel mPanelPanelInfo = new JPanel();
    JLabel mLabelPanelInfo = new JLabel("■ 상품 정보");

    JPanel mPanelDelInfoIdCodeName = new JPanel();
    JLabel mLabelDelProdId = new JLabel("■ 상품 번호 : ");
    JLabel mLabelDelProdCode = new JLabel("■ 상품 코드 : ");
    JLabel mLabelDelProdName = new JLabel("■ 상품명 : ");

    JPanel mPanelDelInfoPriceRegDateDesc = new JPanel();
    JLabel mLabelDelProdPrice = new JLabel("■ 가격 : ");
    JLabel mLabelDelProdRegDate = new JLabel("■ 등록일 : ");
    JLabel mLabelDelProdDesc = new JLabel("■ 비고 : ");

    // 확정 버튼
    JPanel mPanelDelBtn = new JPanel();
    JButton mBtnDeleteComplete = new JButton("제거 확정");

    public DeleteProductView() {
        super(MainPage.VIEW_NUM_PROD_DELETE);
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
        this.mPanelProdInfoDelete.setLayout(new BoxLayout(
                mPanelProdInfoDelete, BoxLayout.Y_AXIS));

        this.mPanelDelInfoIdCodeName.setLayout(new GridLayout(1, 3));
        this.mPanelDelInfoPriceRegDateDesc.setLayout(new GridLayout(1, 3));
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
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 상품정보 패널 설정
        this.mPanelDelInfoIdCodeName.setBorder(
                BorderFactory.createEmptyBorder(20, 200, 20, 200));
        this.mPanelDelInfoPriceRegDateDesc.setBorder(
                BorderFactory.createEmptyBorder(20, 200, 20, 200));
        // 페이지에 추가
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelSearchDelete, BorderLayout.CENTER);

        this.mPanelSearchDelete.add(mPanelSearch, BorderLayout.NORTH);
        this.mPanelSearchDelete.add(mPanelResult, BorderLayout.CENTER);

        this.mPanelSearch.add(mComboBoxSearchProperty);
        this.mPanelSearch.add(mTextFieldSearch);
        this.mPanelSearch.add(mBtnSearch);

        this.mPanelResult.add(mScroll);
        this.mPanelResult.add(mPanelProdInfoDelete);

        this.mPanelProdInfoDelete.add(mPanelPanelInfo);
        this.mPanelPanelInfo.add(mLabelPanelInfo);

        this.mPanelProdInfoDelete.add(mPanelDelInfoIdCodeName);
        this.mPanelDelInfoIdCodeName.add(mLabelDelProdId);
        this.mPanelDelInfoIdCodeName.add(mLabelDelProdCode);
        this.mPanelDelInfoIdCodeName.add(mLabelDelProdName);

        this.mPanelProdInfoDelete.add(mPanelDelInfoPriceRegDateDesc);
        this.mPanelDelInfoPriceRegDateDesc.add(mLabelDelProdPrice);
        this.mPanelDelInfoPriceRegDateDesc.add(mLabelDelProdRegDate);
        this.mPanelDelInfoPriceRegDateDesc.add(mLabelDelProdDesc);

        this.mPanelProdInfoDelete.add(mPanelDelBtn);
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
                        ProductItem findedItem = prodMngr.findById(findId);

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
                    case "상품명":
                        try {
                            String searchText = mTextFieldSearch.getText();

                            List<ProductItem> prodItemList = prodMngr.findByName(searchText);

                            searchItemAddtable(prodItemList);

                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
                            // e1.printStackTrace();
                        }
                        break;

                    case "상품코드":
                        try {
                            String searchText = mTextFieldSearch.getText();

                            // 검색어 앞자리 대문자화
                            char prodType = searchText.charAt(0);
                            StringBuilder sb = new StringBuilder(searchText);
                            if (prodType >= 'a' && prodType <= 'z') {
                                sb.setCharAt(0, (char) (prodType - 32));
                                searchText = sb.toString();
                            }
                            List<ProductItem> prodItemList = new ArrayList<>();

                            // 리스트에 추가
                            ProductCode prodCode = new ProductCode(searchText);
                            ProductItem prodItem = prodMngr.findByProductCode(prodCode);
                            prodItemList.add(prodItem);

                            searchItemAddtable(prodItemList);

                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
                            // e1.printStackTrace();
                        }
                        break;
                }
            } else if (btn == mBtnDeleteComplete) {
                try {
                    String findIdStr = mLabelDelProdId.getText().substring(10);
                    int findId = Integer.valueOf(findIdStr);

                    prodMngr.deleteById(findId);
                    UiUtils.showMsgBox("제거 완료", "", MsgBoxType.Warn);
                    // 테이블 갱신
                    insertItemTable();
                    // 완료되면 레이블을 지운다
                    setLaberInitialize();
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
        System.out.println("[DeleteProductView.onShow()]");

        // 상품목록을 테이블에 추가
        insertItemTable();
    }

    @Override
    protected void onHide() {
        System.out.println("[DeleteProductView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);
    }

    // 검색한 결과를 테이블에 추가
    private void searchItemAddtable(List<ProductItem> prodItemList) {

        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();

        m.setRowCount(0);

        for (ProductItem pi : prodItemList) {
            m.addRow(new Object[] {
                    pi.getId(), pi.getProdCodeStr(),
                    pi.getName(), df.format(pi.getPrice()),
                    pi.getRegDateStr(), pi.getDesc()
            });
        }
    }

    // 검색한 결과를 라벨에 설정
    private void searchItemAddTextField(ProductItem prodItem) {
        setLaberInitialize();

        UiUtils.showMsgBox(String.format(
                "삭제할 항목은\n[ 상품코드[%s] 상품명[%s] ] 입니다\n",
                prodItem.getProdCodeStr(),
                prodItem.getName()), "");

        this.mLabelDelProdId.setText(
                mLabelDelProdId.getText() + prodItem.getId());
        this.mLabelDelProdCode.setText(
                mLabelDelProdCode.getText() + prodItem.getProdCodeStr());
        this.mLabelDelProdName.setText(
                mLabelDelProdName.getText() + prodItem.getName());
        this.mLabelDelProdPrice.setText(
                mLabelDelProdPrice.getText() + prodItem.getPrice());
        this.mLabelDelProdRegDate.setText(
                mLabelDelProdRegDate.getText() + prodItem.getRegDate());
        this.mLabelDelProdDesc.setText(
                mLabelDelProdDesc.getText() + prodItem.getDesc());
    }

    // 라벨을 초기화
    private void setLaberInitialize() {
        this.mLabelDelProdId.setText("■ 상품 번호 : ");
        this.mLabelDelProdCode.setText("■ 상품 코드 : ");
        this.mLabelDelProdName.setText("■ 상품명 : ");
        this.mLabelDelProdPrice.setText("■ 가격 : ");
        this.mLabelDelProdRegDate.setText("■ 등록일 : ");
        this.mLabelDelProdDesc.setText("■ 비고 : ");
    }

    // 상품목록을 테이블에 추가하는 메소드
    private void insertItemTable() {
        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
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
        mTableResultList.updateUI();
    }

}