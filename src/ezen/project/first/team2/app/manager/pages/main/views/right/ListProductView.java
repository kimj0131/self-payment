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
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class ListProductView extends View {
    ProductManagerMem prodMngr = ProductManagerMem.getInstance();

    JLabel mLabelInfo = new JLabel("상품 조회뷰 초기화면입니다");

    // 상품리스트를 넣을 패널 생성
    JPanel mPanelProdList = new JPanel();
    // 상품리스트 테이블
    JTable mTableProdList;
    // 스크롤 삽입
    JScrollPane mSroll;

    public ListProductView() {
        super(MainPage.VIEW_NUM_PROD_LIST);
    }

    @Override
    protected void onInit() {
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
        this.mPanelProdList.setLayout(new BorderLayout());

    }

    @Override
    protected void onAddCtrls() {
        this.mTableProdList.getTableHeader().setReorderingAllowed(false);

        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        this.mSroll = new JScrollPane(mTableProdList);
        this.mSroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mSroll.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelProdList, BorderLayout.CENTER);
        this.mPanelProdList.add(mSroll, BorderLayout.CENTER);
    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[ListProductView.onShow()]");

        DefaultTableModel m = (DefaultTableModel) mTableProdList.getModel();
        m.setRowCount(0);
        try {
            prodMngr.iterate((info, idx) -> {
                m.addRow(new Object[] {
                        info.getId(), info.getProdCodeStr(),
                        info.getName(), info.getPrice(),
                        info.getRegDateStr(), info.getDesc()
                });
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTableProdList.updateUI();
    }

    @Override
    protected void onHide() {
        System.out.println("[ListProductView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont2);
    }
}