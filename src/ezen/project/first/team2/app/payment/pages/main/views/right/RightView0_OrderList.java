package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView0_OrderList extends View {

	private static final int PADDING = 20;

	private static final String SUM_TITLE_LABEL_TEXT = "합계";
	private static final String[] TABLE_HEADER = { "상품코드", "상품명", "수량", "가격" };
	private static final String BUYING_BTN_TEXT = "결제하기";

	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	private static final Color TABLE_HEADER_FONT_COLOR = new Color(0, 0, 28);

	private static final Font TABLE_HEADER_FONT = new Font("맑은 고딕", Font.BOLD, 17);
	private static final Font TABLE_FONT = new Font("맑은 고딕", Font.PLAIN, 17);

	// 결제가 모두 완료 -> 구매내역 새로 만들어야함 / RightView3에서 결제가 모두 완료되면 onHide에서 true로 바꿈
	// 결제가 완료되지 않음 -> 이전 단계로 갔을때는 onShow에서 구매내역을 새로 만들지 않아야 함
	public static boolean RECEIPT_ISSUANCE = false;

	// 구매리스트 테이블 관련 변수
	private JTable mTable;
	private JScrollPane mScrolledTable;
	private DefaultTableModel mTableModel;
	//

	private JLabel mSum_title_label;
	private JTextField mSum_tf;
	private JButton mBuying_btn;

	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;

	//
	private ProductManager mProdMngr;
	private ProductOrderDetailsManager mProdOrderDetailsMngr;

	private ProductPurchasing mProdPurchasing;
	//

	public RightView0_OrderList() {
		super(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {

		setBackground(BACKGROUND_COLOR);
		this.setTable();

		mSum_title_label = new JLabel(SUM_TITLE_LABEL_TEXT);
		mSum_tf = new JTextField();

		mBuying_btn = new JButton(BUYING_BTN_TEXT);

		mGbc = new GridBagConstraints();

		mProdMngr = ProductManager.getInstance();
		mProdOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;

		// 디자인 관련 설정
		mTable.getTableHeader().setFont(TABLE_HEADER_FONT);
		mTable.getTableHeader().setForeground(TABLE_HEADER_FONT_COLOR);
		mTable.setFont(TABLE_FONT);
		mTable.setRowHeight(30);

		mTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
	}

	private void setTable() {

		DefaultTableModel headerModel = new DefaultTableModel(TABLE_HEADER, 0);
		mTable = new JTable(headerModel);
		mTableModel = (DefaultTableModel) mTable.getModel();
		mScrolledTable = new JScrollPane(mTable);

		// mScrolledTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());

		mSum_title_label.setOpaque(true);
		mSum_title_label.setBackground(Color.WHITE);
	}

	@Override
	protected void onAddCtrls() {

		mSum_tf.setEditable(false);

		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 0.5;
		mGbc.weighty = 3;

		mGbc.gridheight = 2;
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mScrolledTable, mGbc);

		mGbc.weightx = 0.1;
		mGbc.weighty = 0.1;
		mGbc.gridheight = 1;
		mGbc.gridx = 1;
		mGbc.gridy = 0;
		this.add(mSum_title_label, mGbc);

		mGbc.weighty = 3;
		mGbc.gridx = 1;
		mGbc.gridy = 1;
		this.add(mSum_tf, mGbc);

		mGbc.weighty = 0.1;
		mGbc.gridx = 0;
		mGbc.gridy = 2;
		mGbc.gridwidth = 2;
		this.add(mBuying_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {

		mBuying_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mSum_tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				try {
					// 테이블 초기화 작업
					mTableModel.setNumRows(0);

					// 랜덤한 상품 하나 생성
					int randomId = (int) (Math.random() * 35);
					var productItem = mProdMngr.findById(randomId);
					System.out.println("추가한 상품\n " + productItem);

					// 생성된 상품을 상세 구매내역에 넣기
					mProdPurchasing._2_addProduct(productItem.getId(), 1);

					// 테이블에 추가
					mProdOrderDetailsMngr.iterate((item, idx) -> {
						try {
							if (item.getProdOrderId() == mProdPurchasing.getProdOrderId()) {

								String prodCode = item.getProdItem().getProdCodeStr();
								String prodName = mProdMngr.findById(item.getProdId()).getName();
								String prodQty = String.valueOf(item.getQuantity());
								int prodPrice = mProdMngr.findById(item.getProdId()).getPrice() * item.getQuantity();

								String[] row = new String[] { prodCode, prodName, prodQty,
										UnitUtils.numToCurrencyStr(prodPrice) };

								mTableModel.addRow(row);

							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						return true;
					});

					// 실시간으로 합계 구해서 표시
					ProductOrderItem prodOrderItem = mProdPurchasing.getProdOrderItem();
					mSum_tf.setText(UnitUtils.numToCurrencyStr(prodOrderItem.getOrgTotalPrice()));

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		// mTableModel.addTableModelListener(e -> {
		//
		// int currChangedCulmn = e.getColumn();
		// // 셀 내용이 바뀐게 없다면 리턴
		// if (currChangedCulmn == -1) {
		// System.out.println("셀 내용이 바뀐게 없음");
		// return;
		// }
		//
		// int currChangedRow = e.getFirstRow();
		//
		// try {
		// String selectedItemId = (String) mTableModel.getValueAt(currChangedRow, 0);
		//
		// ProductItem selectedItem =
		// mProdMngr.findById(Integer.valueOf(selectedItemId));
		// System.out.println("변경 전 아이템의 이름 >> " + selectedItem.getName());
		//
		// // "번호", "상품명", "수량", "가격"
		// if (currChangedCulmn == 1) {
		// selectedItem.setName((String) mTableModel.getValueAt(currChangedRow,
		// currChangedCulmn));
		// System.out.println("변경 후 아이템의 이름 >> " + selectedItem.getName());
		// }
		//
		// } catch (NumberFormatException e1) {
		// e1.printStackTrace();
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// });
	}

	@Override
	protected void onShow(boolean firstTime) {

		if (!RECEIPT_ISSUANCE) {
			System.out.println("rightview0_onShow - 영수증이 발급 되었습니다");

			RECEIPT_ISSUANCE = true;

			try {
				mProdPurchasing._1_makeProdOrder();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void onHide() {
		// 구매내역과 상세구매내역 변화를 보기위한 콘솔 출력
		// try {
		// System.out.println("구매내역");
		// ProductOrdersManager.getInstance().iterate((item, idx) -> {
		// System.out.println(" " + item);
		// return true;
		// });
		//
		// System.out.println("상세구매내역");
		// ProductOrderDetailsManager.getInstance().iterate((item, idx) -> {
		// System.out.println(" " + item);
		// return true;
		// });
		//
		// System.out.println("상품재고");
		// ProductStocksManager.getInstance().iterate((item, idx) -> {
		// System.out.println(" " + item);
		// return true;
		// });
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// -------------------------------------------
	}

	// 결제 완료 된 경우 테이블 초기화 하기 위해 mTableModel이 필요할듯..?
	public DefaultTableModel get_mTableModel() {
		return mTableModel;
	}

	// 결제 완료 된 경우 합계 초기화 하기 위해 mSum_tf가 필요할듯..?
	public JTextField get_mSum_tf() {
		return mSum_tf;
	}

}

class MyTableCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component cell = getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (!isSelected) {
			if (row % 2 == 0) {
				cell.setBackground(Color.WHITE);
			} else {
				cell.setBackground(Color.BLUE);
			}
		}
		return cell;
	}
}
