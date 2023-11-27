package ezen.project.first.team2.app.manager.pages.main.views.right.stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AdjustStockView extends View {

    DecimalFormat df = new DecimalFormat("###,###");

    ProductManagerMem prodMngr = ProductManagerMem.getInstance();
    ProductStocksManagerMem prodStMngr = ProductStocksManagerMem.getInstance();

    JLabel mLabelInfo = new JLabel("상품 재고 조정");
    // 검색, 결과용 패널
    JPanel mPanelSearchResult = new JPanel();
    // 검색용 패널
    JPanel mPanelSearch = new JPanel();
    // 검색결과용 패널
    JPanel mPanelResult = new JPanel();
    // 검색 컴포넌트
    JComboBox<String> mComboBoxSearchProperty;
    JTextField mTextFieldSearch = new JTextField(10);
    JButton mBtnSearch = new JButton("검색");
    // 검색결과 컴포넌트
    JTable mTableResultList;
    JScrollPane mSroll;
    // 조정확정 버튼
    JButton mBtnAdjustComplete = new JButton("조정 확정");

    public AdjustStockView() {
        super(MainPage.VIEW_NUM_STOCK_ADJUST);
    }

    @Override
    protected void onInit() {
        // 테이블 설정
        try {
            Object[] mPropertyColumn = {
                    "상품번호", "상품코드", "상품명", "가격", "현재 재고", "실제 재고"
            };
            Object[][] mProdListRows = new Object[mPropertyColumn.length][prodMngr.getCount()];

            DefaultTableModel model = new DefaultTableModel(mProdListRows, mPropertyColumn) {
                // '실재고'컬럼 외 내용 수정 불가
                boolean[] canEdit = new boolean[] {
                        false, false, false, false, false, true
                };

                @Override
                public boolean isCellEditable(int row, int column) {
                    return canEdit[column];
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
        this.mPanelSearchResult.setLayout(new BorderLayout());
        this.mPanelResult.setLayout(new BorderLayout());
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
                BorderFactory.createEmptyBorder(10, 30, 30, 30));
        // 콤보박스 설정
        String[] properties = { "상품명", "상품코드" };
        this.mComboBoxSearchProperty = new JComboBox<String>(properties);

        // 확정버튼 설정
        this.mBtnAdjustComplete.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelSearchResult, BorderLayout.CENTER);
        this.mPanelSearchResult.add(mPanelSearch, BorderLayout.NORTH);
        this.mPanelSearchResult.add(mPanelResult, BorderLayout.CENTER);
        this.mPanelSearch.add(mComboBoxSearchProperty);
        this.mPanelSearch.add(mTextFieldSearch);
        this.mPanelSearch.add(mBtnSearch);
        this.mPanelResult.add(mSroll, BorderLayout.CENTER);
        this.mPanelResult.add(mBtnAdjustComplete, BorderLayout.SOUTH);
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
                    // int col = mTableResultList.getSelectedColumn();

                    int editColumn = 5;
                    mTableResultList.changeSelection(row, editColumn, false, false);

                    int idColumn = 0;
                    int findId = (int) mTableResultList.getValueAt(row, idColumn);
                    DefaultTableModel m = (DefaultTableModel) mTableResultList.getModel();
                    m.isCellEditable(row, idColumn);

                    try {
                        UiUtils.showMsgBox(String.format(
                                "수정할 항목은\n[ 상품코드[%s] 상품명[%s] ] 입니다\n",
                                prodMngr.findById(findId).getProdCodeStr(),
                                prodMngr.findById(findId).getName()), "");

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }

        });

        ActionListener listener = e -> {
            if (e.getSource() == mBtnSearch) {
                System.out.println("Pressed Search Button!");

                String property = mComboBoxSearchProperty.getSelectedItem().toString();
                System.out.println("Property : " + property);

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
                            // 상품 아이템 찾기
                            ProductCode prodCode = new ProductCode(searchText);
                            ProductItem prodItem = prodMngr.findByProductCode(prodCode);

                            List<ProductItem> prodItemList = new ArrayList<>();
                            prodItemList.add(prodItem);

                            searchItemAddtable(prodItemList);

                        } catch (Exception e1) {
                            System.out.println("[findByName()]No Search Result");
                            UiUtils.showMsgBox("검색결과가 없습니다", "");
                            // e1.printStackTrace();
                        }
                        break;
                }
            }
        };
        this.mBtnSearch.addActionListener(listener);
    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AdjustStockView.onShow()]");

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

    @Override
    protected void onHide() {
        System.out.println("[AdjustStockView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        mBtnAdjustComplete.setFont(main.mFont2);
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
            });
        }
    }
}