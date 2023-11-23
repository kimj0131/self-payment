package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.base.ListActionAdapter;
import ezen.project.first.team2.app.common.modules.base.ListActionListener;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManagerMem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManagerMem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailItem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManagerMem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManagerMem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManagerMem;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView0_OrderList extends View {

	private static final int PADDING = 10;
	private final String[] TABLE_HEADER= {"상품id", "상품명", "수량", "가격"};
	
	GridBagConstraints mGbc;
	
	ProductManagerMem mProdMngr;
	ProductStocksManagerMem mProdStocksMngr;
	ProductDiscountsManagerMem mProdDiscntsMngr;
	ProductOrdersManagerMem mProdOrdersMngr;
	ProductOrderDetailsManagerMem mProdOrderDetailsMngr;

	JTable mTable;
	JScrollPane mScrolledTable;
	DefaultTableModel mTableModel;

	JTextArea mSumTextArea;
	JButton mBuyingButton;

	int mSumPrice;
	int mNewProductOrderID;

	public RightView0_OrderList() {
		super(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
		this.setTable();

		mProdMngr = ProductManagerMem.getInstance();
		mProdStocksMngr = ProductStocksManagerMem.getInstance();
		mProdDiscntsMngr = ProductDiscountsManagerMem.getInstance();
		mProdOrdersMngr = ProductOrdersManagerMem.getInstance();
		mProdOrderDetailsMngr = ProductOrderDetailsManagerMem.getInstance();
		
		mGbc = new GridBagConstraints();
		mSumTextArea = new JTextArea("합계\n");
		mBuyingButton = new JButton("결제하기");
		
		try {
			// 구매 내역(영수증) ID 발급
			mNewProductOrderID = mProdOrdersMngr.getNextID();
			// 구매 내역(영수증) 아이템 생성 => 비회원 + 사용할 포인트 0.
			var poi = new ProductOrderItem(mNewProductOrderID, LocalDateTime.now());
			mProdOrdersMngr.add(poi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setTable() {
		
		DefaultTableModel headerModel = new DefaultTableModel(TABLE_HEADER, 0);
		mTable = new JTable(headerModel);
		mTableModel = (DefaultTableModel) mTable.getModel();
		mScrolledTable = new JScrollPane(mTable);

//		mScrolledTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mTable.setShowVerticalLines(false); // 세로 줄 안보이게하기
		mTable.setShowHorizontalLines(false); // 가로 줄 안보이게하기

		mTable.getTableHeader().setReorderingAllowed(false); // 헤더 이동불가
		mTable.getTableHeader().setResizingAllowed(false); // 헤더 사이즈조절불가

//		mTable.setEnabled(false); // 셀 선택불가, 수정불가

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
		this.add(mBuyingButton, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		
		// 상세 구매 내역이 추가되면 해당 상품 재고 수량 감소
		// 해당 상품에 재고가 없더라도 일단 수량 감소
		mProdOrderDetailsMngr.setActionListener(new ListActionAdapter<ProductOrderDetailItem>() {
			@Override
			public void onAdded(ListManager<ProductOrderDetailItem> mngr, ProductOrderDetailItem item) {
				try {
					mProdStocksMngr.decQuantityByProdId(item.getProdId(), item.getQuantity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		mBuyingButton.addActionListener(e -> {
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
			//	번호 상품명 수량 가격
				try {
//					mProdMngr.iterate((item, idx) -> {
//						String[] row = new String[] {String.valueOf(item.getId()), item.getName(), "1", String.valueOf(item.getPrice())};
//						mTableModel.addRow(row);
//						return true;
//					});

					// 랜덤한 상품 하나 생성
					int randomId = (int) (Math.random() * 35);
					var productItem = mProdMngr.findById(randomId);
					
					// 생성된 상품을 상세 구매내역에 넣기
					var pdi = mProdDiscntsMngr.getItemByProdId(productItem.getId());
					var prodOrderDetailItem = new ProductOrderDetailItem(-1, mNewProductOrderID, productItem.getId(), pdi.getId(), 1);
					mProdOrderDetailsMngr.add(prodOrderDetailItem);
					
					String[] row = new String[] {String.valueOf(productItem.getId()), productItem.getName(), "1", String.valueOf(productItem.getPrice())};
					mTableModel.addRow(row);
					
					
					
					// 구매내역과 상세구매내역 변화를 보기위한 콘솔 출력
					try {
						System.out.println("구매내역");
						mProdOrdersMngr.iterate((item, idx) -> {
							System.out.println("  " + item);
							return true;
						});
						
						System.out.println("상세구매내역");
						mProdOrderDetailsMngr.iterate((item, idx) -> {
							System.out.println("  " + item);
							return true;
						});
						
						System.out.println("상품재고");
						mProdStocksMngr.iterate((item, idx) -> {
							System.out.println("  " + item);
							return true;
						});
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					//-------------------------------------------
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
//		mTableModel.addTableModelListener(e -> {
//			
//			int currChangedCulmn = e.getColumn();
//			// 셀 내용이 바뀐게 없다면 리턴
//			if (currChangedCulmn == -1) {
//				System.out.println("셀 내용이 바뀐게 없음");
//				return;
//			}
//
//			int currChangedRow = e.getFirstRow();
//
//			try {
//				String selectedItemId = (String) mTableModel.getValueAt(currChangedRow, 0);
//
//				ProductItem selectedItem = mProdMngr.findById(Integer.valueOf(selectedItemId));
//				System.out.println("변경 전 아이템의 이름 >> " + selectedItem.getName());
//
//				// "번호", "상품명", "수량", "가격"
//				if (currChangedCulmn == 1) {
//					selectedItem.setName((String) mTableModel.getValueAt(currChangedRow, currChangedCulmn));
//					System.out.println("변경 후 아이템의 이름 >> " + selectedItem.getName());
//				}
//
//			} catch (NumberFormatException e1) {
//				e1.printStackTrace();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

}
