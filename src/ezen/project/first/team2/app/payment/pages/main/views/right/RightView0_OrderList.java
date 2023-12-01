package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView0_OrderList extends View {

	private static final int PADDING = 20;

	private static final String SUM_TITLE_LABEL_TEXT = "합  계";
	private static final String[] TABLE_HEADER= {"상품코드", "상품명", "수량", "가격"};
	private static final String BUYING_BTN_TEXT = "결제하기";

	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);

	// Table Header
	private static final Font TABLE_HEADER_FONT = new Font("맑은 고딕", Font.BOLD, 17);
	private static final Color TABLE_HEADER_FONT_COLOR = new Color(54, 70, 81);

	// Table row
	private static final Font TABLE_FONT = new Font("맑은 고딕", Font.PLAIN, 17);

	// Buying Button
	private static final Font BUYING_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color BUYING_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color BUYING_BTN_ACTIVETED_COLOR = new Color(79, 175, 86);
	private static final Color BUYING_BTN_DEACTIVETED_COLOR = new Color(103, 121, 133);
	
	// Sum Title Label
	private static final Font SUM_TITLE_LABEL_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color SUM_TITLE_LABEL_COLOR = new Color(225, 239, 248);
	
	// Sum TextField
	private static final Font SUM_TF_FONT = new Font("맑은 고딕", Font.PLAIN, 27);
	private static final Color SUM_TF_COLOR = new Color(225, 239, 248);
	
	// 결제가 모두 완료		-> 구매내역 새로 만들어야함 / RightView3에서 결제가 모두 완료되면 onHide에서 true로 바꿈
	// 결제가 완료되지 않음	-> 이전 단계로 갔을때는 onShow에서 구매내역을 새로 만들지 않아야 함
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
		
		mSum_title_label = new JLabel(SUM_TITLE_LABEL_TEXT);
		mSum_tf = new JTextField();

		mBuying_btn = new JButton(BUYING_BTN_TEXT);
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();

		mProdMngr = ProductManager.getInstance();
		mProdOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;

	}


	
	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		this.setTable();
		
		//
		mSum_title_label.setBorder(BorderFactory.createEmptyBorder(
				10, 10, 0, 10));
		mSum_title_label.setOpaque(true);
		mSum_title_label.setBackground(SUM_TITLE_LABEL_COLOR);
		mSum_title_label.setFont(SUM_TITLE_LABEL_FONT);
		mSum_title_label.setHorizontalAlignment(JLabel.RIGHT);
		
		//
		mSum_tf.setBackground(SUM_TF_COLOR);
		mSum_tf.setFont(SUM_TF_FONT);
		mSum_tf.setHorizontalAlignment(JTextField.RIGHT);
		mSum_tf.setBorder(BorderFactory.createEmptyBorder(
				0, 10, 0, 10));
		
		//
		mBuying_btn.setFont(BUYING_BTN_FONT);
		mBuying_btn.setForeground(BUYING_BTN_FONT_COLOR);
		mBuying_btn.setBackground(BUYING_BTN_DEACTIVETED_COLOR);
		mBuying_btn.setEnabled(false);
	}
	
	// 물품을 추가하면 버튼 활성화
	public void activateButton() {
		mBuying_btn.setBackground(BUYING_BTN_ACTIVETED_COLOR);
		mBuying_btn.setEnabled(true);
	}
	
	// 결제 완료하고 나서 버튼 비활성화
	public void deactivateButton() {
		mBuying_btn.setBackground(BUYING_BTN_DEACTIVETED_COLOR);
		mBuying_btn.setEnabled(false);
	}
	
	private void setTable() {

		DefaultTableModel headerModel = new DefaultTableModel(TABLE_HEADER, 0);
		mTable = new JTable(headerModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				
				JComponent cp = (JComponent) super.prepareRenderer(renderer, row, column);
				
				if (row % 2 == 0) {
					cp.setBackground(BACKGROUND_COLOR);
				} else {
					cp.setBackground(Color.WHITE);
				}
				return cp;
			}
		};
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

		mTable.getColumn(TABLE_HEADER[0]).setPreferredWidth(5);
		mTable.getColumn(TABLE_HEADER[0]).setCellRenderer(center);

		mTable.getColumn(TABLE_HEADER[1]).setPreferredWidth(150);
		mTable.getColumn(TABLE_HEADER[1]).setCellRenderer(center);
		
		mTable.getColumn(TABLE_HEADER[2]).setPreferredWidth(5);
		mTable.getColumn(TABLE_HEADER[2]).setCellRenderer(center);

		mTable.getColumn(TABLE_HEADER[3]).setPreferredWidth(90);
		mTable.getColumn(TABLE_HEADER[3]).setCellRenderer(center);


		// 디자인 관련 설정
		JTableHeader header = mTable.getTableHeader();
		header.setFont(TABLE_HEADER_FONT);
		header.setForeground(TABLE_HEADER_FONT_COLOR);
		header.setBackground(Color.WHITE);
		header.setPreferredSize(new Dimension(10, 40)); // 헤더 높이 설정
		
		mTable.setFont(TABLE_FONT);
		mTable.setRowHeight(35);

	}

	@Override
	protected void onAddCtrls() {

		mSum_tf.setEditable(false);

		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 0.5;

		mGbc.gridheight = 3;
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mScrolledTable, mGbc);

		mGbc.gridheight = 1;
		mGbc.insets = new Insets(0, PADDING, 0, 0);
		
		mGbc.anchor = GridBagConstraints.NORTH;
		mGbc.weightx = 0.1;
		mGbc.weighty = 0.05;
		mGbc.gridx = 1;
		mGbc.gridy = 0;
		this.add(mSum_title_label, mGbc);
		
		mGbc.fill = GridBagConstraints.HORIZONTAL;
		mGbc.weighty = 1;
		mGbc.gridx = 1;
		mGbc.gridy = 1;
		this.add(mSum_tf, mGbc);
		
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weighty = 0.05;
		mGbc.gridx = 1;
		mGbc.gridy = 2;
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
