package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView0_OrderList extends View {

	private static final int PADDING = 10;
	private final String[] TABLE_HEADER= {"번호", "상품명", "수량", "가격" };
	
	GridBagConstraints mGbc;
	ProductManagerMem mProdMngr;

	JTable mTable;
	JScrollPane mScrolledTable;
	DefaultTableModel mTableModel;

	JTextArea mSumTextArea;
	JButton mButton0;

	int mSumPrice;

	public RightView0_OrderList() {
		super(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
		
		this.setFocusable(true);
		
//		테이블 초기화-----------------------------------------------------------------------------
		
		DefaultTableModel headerModel = new DefaultTableModel(TABLE_HEADER, 0);
		mTable = new JTable(headerModel);
		mTableModel = (DefaultTableModel) mTable.getModel();
		mScrolledTable = new JScrollPane(mTable);

//		mScrolledTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mTable.setShowVerticalLines(false); // 세로 줄 안보이게하기
		mTable.setShowHorizontalLines(false); // 가로 줄 안보이게하기

		mTable.getTableHeader().setReorderingAllowed(false); // 헤더 이동불가
		mTable.getTableHeader().setResizingAllowed(false); // 헤더 사이즈조절불가

		mTable.setEnabled(false); // 셀 선택불가, 수정불가

		mTable.getParent().setBackground(Color.WHITE); // 테이블 배경색
		
		// 셀크기 설정, 텍스트 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(JLabel.RIGHT);

		mTable.getColumn(TABLE_HEADER[0]).setPreferredWidth(10);
		mTable.getColumn(TABLE_HEADER[0]).setCellRenderer(center);
		
		mTable.getColumn(TABLE_HEADER[1]).setPreferredWidth(150);
		mTable.getColumn(TABLE_HEADER[1]).setCellRenderer(center);
		
		mTable.getColumn(TABLE_HEADER[2]).setPreferredWidth(10);
		mTable.getColumn(TABLE_HEADER[2]).setCellRenderer(center);
		
		mTable.getColumn(TABLE_HEADER[3]).setPreferredWidth(90);
		mTable.getColumn(TABLE_HEADER[3]).setCellRenderer(center);

		// ---------------------------------------------------------------------------------------

		mGbc = new GridBagConstraints();
		mProdMngr = ProductManagerMem.getInstance();
		
		mSumTextArea = new JTextArea("합계\n");
		mButton0 = new JButton("결제하기");
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
	}

	@Override
	protected void onAddCtrls() {
		
		mSumTextArea.setEditable(false);

		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 0.5;
		mGbc.weighty = 3;

		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mScrolledTable, mGbc);

		mGbc.weightx = 0.1;
		mGbc.gridx = 1;
		mGbc.gridy = 0;
		this.add(mSumTextArea, mGbc);

		mGbc.weighty = 0.1;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		mGbc.gridwidth = 2;
		this.add(mButton0, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mButton0.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		mSumTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int ran = (int) (Math.random() * ProductItem.getPredefinedProductData().length);
				boolean isSetValue = false;

				try {
					ProductItem item = ProductItem.getPredefinedProductData()[ran];
					
					if (mTable.getRowCount() > 0) {
						for (int i = 0; i <= mTable.getRowCount() - 1; ++i) {
							if (mTable.getValueAt(i, 0).equals(item.getProdCodeStr())) {
								Integer inPutValue = Integer.valueOf(mTable.getValueAt(i, 2).toString());
								mTable.setValueAt(inPutValue + 1, i, 2);
								isSetValue = true;
							}
						}
					}
					if (!isSetValue) {
						mTableModel.addRow(new String[] { item.getProdCodeStr(), item.getName(), "1",
								String.valueOf(item.getPrice()) });
						isSetValue = false;
					}
					
//					mProdMngr.add(item);
					mSumPrice += item.getPrice();
					mSumTextArea.setText("합계\n" + mSumPrice + "원");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});

	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

}
