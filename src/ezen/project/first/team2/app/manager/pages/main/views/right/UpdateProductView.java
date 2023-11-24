package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class UpdateProductView extends View {
    DecimalFormat df = new DecimalFormat("###,###");

    JLabel mLabelInfo = new JLabel("상품 데이터 수정");

    ProductManagerMem prodMngr = ProductManagerMem.getInstance();

    // 검색, 결과용 패널
    JPanel mPanelSearchResult = new JPanel();
    // 검색용 패널
    JPanel mPanelSearch = new JPanel();
    // 검색결과용 패널
    JPanel mPanelResult = new JPanel();
    // 검색 컴포넌트
    JComboBox<String> mComboBoxSearchProperty;
    String[] properties = { "상품명", "상품코드" };
    JTextField mTextFieldSearch = new JTextField(10);
    JButton mBtnSearch = new JButton("검색");
    // 검색결과 컴포넌트
    JTable mTableResultList;
    JScrollPane mSroll;
    // 수정확정 버튼
    JButton mBtnUpdateComplete = new JButton("수정 확정");

    public UpdateProductView() {
        super(MainPage.VIEW_NUM_PROD_UPDATE);
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

            mTableResultList = new JTable(model);
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
        // 스크롤 설정
        this.mSroll = new JScrollPane(mTableResultList);
        this.mSroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mSroll.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 30, 30));
        // 콤보박스 설정
        this.mComboBoxSearchProperty = new JComboBox<String>(properties);

        // 확정버튼 설정
        this.mBtnUpdateComplete.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelSearchResult, BorderLayout.CENTER);
        this.mPanelSearchResult.add(mPanelSearch, BorderLayout.NORTH);
        this.mPanelSearchResult.add(mPanelResult, BorderLayout.CENTER);
        this.mPanelSearch.add(mComboBoxSearchProperty);
        this.mPanelSearch.add(mTextFieldSearch);
        this.mPanelSearch.add(mBtnSearch);
        this.mPanelResult.add(mSroll, BorderLayout.CENTER);
        this.mPanelResult.add(mBtnUpdateComplete, BorderLayout.SOUTH);
    }

    @Override
    protected void onAddEventListeners() {
        mTextFieldSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mPanelSearch.getRootPane().setDefaultButton(mBtnSearch);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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

                            ProductItem pi = prodMngr.findByName(searchText);

                            List<ProductItem> prodItemList = new ArrayList<>();
                            prodItemList.add(pi);

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
            }
        };

        this.mBtnSearch.addActionListener(listener);

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[ListProdStockView.onShow()]");

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

    @Override
    protected void onHide() {
        System.out.println("[ListProdStockView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont0);
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
}