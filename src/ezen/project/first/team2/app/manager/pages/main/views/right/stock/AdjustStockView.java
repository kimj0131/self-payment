package ezen.project.first.team2.app.manager.pages.main.views.right.stock;

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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AdjustStockView extends View {

    DecimalFormat df = new DecimalFormat("###,###");

    ProductManager prodMngr = ProductManager.getInstance();
    ProductStocksManager prodStMngr = ProductStocksManager.getInstance();

    JLabel mLabelInfo = new JLabel("상품 재고 조정");
    // 검색, 결과용 패널
    JPanel mPanelSearchResult = new JPanel();
    // 검색용 패널
    JPanel mPanelSearch = new JPanel();
    // 검색결과용 패널
    JPanel mPanelResult = new JPanel();
    // 재고조정 패널
    JPanel mPanelStockAdjust = new JPanel();

    // 검색 컴포넌트
    JComboBox<String> mComboBoxSearchProperty;
    String[] properties = { "상품명", "상품코드" };
    JTextField mTextFieldSearch = new JTextField(10);
    JButton mBtnSearch = new JButton("검색");
    // 검색결과 컴포넌트
    JTable mTableResultList;
    JScrollPane mSroll;

    // 재고조정 컴포넌트
    JPanel mPanelPanelInfo = new JPanel();
    JLabel mLabelPanelInfo_1 = new JLabel("상단 리스트에서 더블클릭");
    JLabel mLabelPanelInfo_2 = new JLabel("■ 상품 정보");

    JPanel mPanelProductIdCode = new JPanel();
    JPanel mPanelProductId = new JPanel();
    JLabel mLabelProductId = new JLabel("상품 번호");
    JTextField mTextFieldProductId = new JTextField(10);
    JPanel mPanelProductCode = new JPanel();
    JLabel mLabelProductCode = new JLabel("상품 코드");
    JTextField mTextFieldProductCode = new JTextField(10);

    JPanel mPanelProductNamePrice = new JPanel();
    JPanel mPanelProductName = new JPanel();
    JLabel mLabelProductName = new JLabel("상품명");
    JTextField mTextFieldProductName = new JTextField(10);
    JPanel mPanelProductPrice = new JPanel();
    JLabel mLabelProductPrice = new JLabel("상품 가격");
    JTextField mTextFieldProductPrice = new JTextField(10);

    JPanel mPanelAdjustCurrentActual = new JPanel();
    JPanel mPanelAdjustCurrent = new JPanel();
    JLabel mLabelAdjustCurrent = new JLabel("현재고");
    JTextField mTextFieldAdjustCurrent = new JTextField(5);
    JPanel mPanelAdjustActual = new JPanel();
    JLabel mLabelAdjustActual = new JLabel("실재고");
    JTextField mTextFieldAdjustActual = new JTextField(5);

    // 조정확정 버튼
    JPanel mPanelAdjustBtn = new JPanel();
    JButton mBtnAdjustComplete = new JButton("재고 조정 확정");

    public AdjustStockView() {
        super(MainPage.VIEW_NUM_STOCK_ADJUST);
    }

    @Override
    protected void onInit() {
        // 테이블 설정
        try {
            Object[] mPropertyColumn = {
                    "상품번호", "상품코드", "상품명", "가격", "현재 재고"
            };
            Object[][] mProdListRows = new Object[mPropertyColumn.length][prodMngr.getCount()];

            DefaultTableModel model = new DefaultTableModel(mProdListRows, mPropertyColumn) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                };

            };

            this.mTableResultList = new JTable(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelPanelInfo.setLayout(new GridLayout(2, 1));
        this.mPanelSearchResult.setLayout(new BorderLayout());
        this.mPanelResult.setLayout(new GridLayout(2, 1));
        this.mPanelStockAdjust.setLayout(new BoxLayout(
                mPanelStockAdjust, BoxLayout.Y_AXIS));
    }

    @Override
    protected void onAddCtrls() {
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        // 테이블 설정
        this.mTableResultList.getTableHeader().setReorderingAllowed(false);
        mTableResultList.setRowSelectionAllowed(false); // 선택하면 셀 하나만 선택되게 설정
        mTableResultList.setCellSelectionEnabled(true);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel tcm = this.mTableResultList.getColumnModel();
        tcm.getColumn(0).setMaxWidth(60);
        tcm.getColumn(0).setCellRenderer(dtcr);
        tcm.getColumn(1).setMaxWidth(100);
        tcm.getColumn(1).setCellRenderer(dtcr);

        // 스크롤 설정
        this.mSroll = new JScrollPane(mTableResultList);
        this.mSroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mSroll.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 10, 30));
        // 콤보박스 설정
        this.mComboBoxSearchProperty = new JComboBox<String>(properties);

        // 비활성화할 텍스트필드
        this.mTextFieldProductId.setEditable(false);
        this.mTextFieldProductCode.setEditable(false);
        this.mTextFieldProductName.setEditable(false);
        this.mTextFieldProductPrice.setEditable(false);
        this.mTextFieldAdjustCurrent.setEditable(false);

        // 라벨설정
        this.mLabelPanelInfo_1.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelPanelInfo_2.setHorizontalAlignment(JLabel.CENTER);

        // 패널 설정
        this.mPanelStockAdjust.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20));

        this.mPanelProductIdCode.setBorder(
                BorderFactory.createEmptyBorder(5, 200, 5, 200));
        this.mPanelProductNamePrice.setBorder(
                BorderFactory.createEmptyBorder(5, 200, 5, 200));
        this.mPanelAdjustCurrentActual.setBorder(
                BorderFactory.createEmptyBorder(5, 200, 5, 200));

        // 버튼 설정
        this.mBtnAdjustComplete.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelSearchResult, BorderLayout.CENTER);
        this.mPanelSearchResult.add(mPanelSearch, BorderLayout.NORTH);
        this.mPanelSearchResult.add(mPanelResult, BorderLayout.CENTER);

        this.mPanelSearch.add(mComboBoxSearchProperty);
        this.mPanelSearch.add(mTextFieldSearch);
        this.mPanelSearch.add(mBtnSearch);

        this.mPanelResult.add(mSroll);
        this.mPanelResult.add(mPanelStockAdjust);

        this.mPanelStockAdjust.add(mPanelPanelInfo);
        this.mPanelPanelInfo.add(mLabelPanelInfo_1);
        this.mPanelPanelInfo.add(mLabelPanelInfo_2);

        this.mPanelStockAdjust.add(mPanelProductIdCode);
        this.mPanelStockAdjust.add(mPanelProductNamePrice);
        this.mPanelStockAdjust.add(mPanelAdjustCurrentActual);

        this.mPanelProductIdCode.add(mPanelProductId);
        this.mPanelProductId.add(mLabelProductId);
        this.mPanelProductId.add(mTextFieldProductId);
        this.mPanelProductIdCode.add(mPanelProductCode);
        this.mPanelProductCode.add(mLabelProductCode);
        this.mPanelProductCode.add(mTextFieldProductCode);

        this.mPanelProductNamePrice.add(mPanelProductName);
        this.mPanelProductName.add(mLabelProductName);
        this.mPanelProductName.add(mTextFieldProductName);
        this.mPanelProductNamePrice.add(mPanelProductPrice);
        this.mPanelProductPrice.add(mLabelProductPrice);
        this.mPanelProductPrice.add(mTextFieldProductPrice);

        this.mPanelAdjustCurrentActual.add(mPanelAdjustCurrent);
        this.mPanelAdjustCurrent.add(mLabelAdjustCurrent);
        this.mPanelAdjustCurrent.add(mTextFieldAdjustCurrent);
        this.mPanelAdjustCurrentActual.add(mPanelAdjustActual);
        this.mPanelAdjustActual.add(mLabelAdjustActual);
        this.mPanelAdjustActual.add(mTextFieldAdjustActual);

        this.mPanelStockAdjust.add(mPanelAdjustBtn);
        this.mPanelAdjustBtn.add(mBtnAdjustComplete);

    }

    @Override
    protected void onAddEventListeners() {

        // 텍스트필드에서 엔터 누르면 버튼이 눌림
        mTextFieldSearch.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mPanelSearch.getRootPane().setDefaultButton(mBtnSearch);
                }
            }
        });

        //
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

            if (btn == this.mBtnSearch) {

                String property = mComboBoxSearchProperty.getSelectedItem().toString();

                switch (property) {
                    case "상품명":
                        try {
                            String searchText = mTextFieldSearch.getText();

                            // 상품 아이템 얻기
                            List<ProductItem> prodItemList = prodMngr.findByName(searchText);

                            searchItemAddtable(prodItemList);

                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
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
                            // 상품 아이템 찾기
                            ProductCode prodCode = new ProductCode(searchText);
                            ProductItem prodItem = prodMngr.findByProductCode(prodCode);

                            List<ProductItem> prodItemList = new ArrayList<>();
                            prodItemList.add(prodItem);

                            searchItemAddtable(prodItemList);

                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
                        }
                        break;
                }
            } else if (btn == mBtnAdjustComplete) {
                int adjustId = Integer.valueOf(this.mTextFieldProductId.getText());

                ProductItem findItem = prodMngr.findById(adjustId);

                ProductStockItem adjustItem = prodStMngr.findById(adjustId);
                adjustItem.setQuantity(Integer.valueOf(mTextFieldAdjustActual.getText()));

                DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
                for (int row = 0; row < mTableResultList.getRowCount(); row++) {
                    if ((int) mTableResultList.getValueAt(row, 0) == adjustId) {

                        m.removeRow(row);

                        Object[] item = { findItem.getId(), findItem.getProdCodeStr(),
                                findItem.getName(), df.format(findItem.getPrice()),
                                adjustItem.getQuantity() };
                        m.insertRow(row, item);

                        break;
                    }
                }
                UiUtils.showMsgBox("재고 조정 완료", "");

                // 완료되면 필드를 지운다
                initializeTextField();

            }
        };
        this.mBtnSearch.addActionListener(listener);
        this.mBtnAdjustComplete.addActionListener(listener);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AdjustStockView.onShow()]");

        initializeTextField();

        insertItemsIntoTable();
    }

    @Override
    protected void onHide() {
        System.out.println("[AdjustStockView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);

        this.mLabelAdjustActual.setFont(main.mFont2);
        this.mLabelAdjustCurrent.setFont(main.mFont2);
        this.mLabelPanelInfo_1.setFont(main.mFont2);
        this.mLabelPanelInfo_2.setFont(main.mFont2);
        this.mLabelProductCode.setFont(main.mFont2);
        this.mLabelProductId.setFont(main.mFont2);
        this.mLabelProductName.setFont(main.mFont2);
        this.mLabelProductPrice.setFont(main.mFont2);

        this.mTableResultList.setFont(main.mFont3);

        this.mBtnAdjustComplete.setFont(main.mFont2);
    }

    // 검색한 결과를 테이블에 추가
    private void searchItemAddtable(List<ProductItem> prodItemList) {

        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();

        m.setRowCount(0);

        try {
            for (ProductItem pi : prodItemList) {
                m.addRow(new Object[] {
                        pi.getId(), pi.getProdCodeStr(),
                        pi.getName(), df.format(pi.getPrice()),
                        prodStMngr.getQuantityByProdId(pi.getId())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 검색한 결과를 텍스트 필드에 추가
    private void searchItemAddTextField(ProductItem prodItem) {

        UiUtils.showMsgBox(String.format(
                "수정할 항목은\n[ 상품코드[%s] 상품명[%s] ] 입니다\n",
                prodItem.getProdCodeStr(),
                prodItem.getName()), "");

        try {
            this.mTextFieldProductId.setText(String.valueOf(prodItem.getId()));
            this.mTextFieldProductCode.setText(prodItem.getProdCodeStr());
            this.mTextFieldProductName.setText(prodItem.getName());
            this.mTextFieldProductPrice.setText(String.valueOf(prodItem.getPrice()));
            this.mTextFieldAdjustCurrent.setText(
                    String.valueOf(prodStMngr.getQuantityByProdId(prodItem.getId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 텍스트 필드 비우기
    private void initializeTextField() {
        this.mTextFieldProductId.setText("");
        this.mTextFieldProductCode.setText("");
        this.mTextFieldProductName.setText("");
        this.mTextFieldProductPrice.setText("");
        this.mTextFieldAdjustCurrent.setText("");
        this.mTextFieldAdjustActual.setText("");
    }

    // 상품목록을 테이블에 추가하는 메소드
    private void insertItemsIntoTable() {
        DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
        m.setRowCount(0);
        try {
            prodMngr.iterate((info, idx) -> {
                try {
                    m.addRow(new Object[] {
                            info.getId(), info.getProdCodeStr(),
                            info.getName(), df.format(info.getPrice()),
                            // 현재고 불러오기
                            prodStMngr.getQuantityByProdId(info.getId())

                    });
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
}