package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView0_OrderList extends View {

	private static final int PADDING = 10;
	private final String[] TABLE_HEADER = { "상품번호", "상품명", "상품개수", "상품가격" };

	GridBagConstraints gbc = new GridBagConstraints();

	JTable mTable;
	JScrollPane mScrolledTable;
	DefaultTableModel mTableModel;
	// JTextArea mTextArea0 = new JTextArea("상품번호\t상품명\t상품개수\t상품가격\n");
	// JScrollPane mTextArea0_Scoll = new JScrollPane(mTextArea0);

	JTextArea mTextArea1;
	JButton mButton0;

	public RightView0_OrderList() {
		super(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);

		// 테이블
		// 초기화-----------------------------------------------------------------------------

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

		// 텍스트 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(JLabel.RIGHT);

		mTable.getColumn(TABLE_HEADER[0]).setCellRenderer(center);
		mTable.getColumn(TABLE_HEADER[1]).setCellRenderer(center);
		mTable.getColumn(TABLE_HEADER[2]).setCellRenderer(center);
		mTable.getColumn(TABLE_HEADER[3]).setCellRenderer(center);

		// ---------------------------------------------------------------------------------------

		mTextArea1 = new JTextArea("합계\n");
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
		// mTextArea0.setEditable(false);
		mTextArea1.setEditable(false);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 3;

		gbc.gridx = 0;
		gbc.gridy = 0;

		this.add(mScrolledTable, gbc);
		// this.add(mTextArea0_Scoll, gbc);

		gbc.weightx = 0.1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(mTextArea1, gbc);

		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		this.add(mButton0, gbc);
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

		mScrolledTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("마우스눌림");
				String[] record = { "1", "빼빼로", "1개", "3000" };
				String[] record1 = { "1", "빼빼로", "1개", "3000" };
				String[] record2 = { "1", "빼빼로", "1개", "3000" };
				String[] record3 = { "1", "빼빼로", "1개", "3000" };
				mTableModel.addRow(record);
				mTableModel.addRow(record1);
				mTableModel.addRow(record2);
				mTableModel.addRow(record3);
			}
		});

		mScrolledTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("키보드 눌림");
				String[] record = { "1", "빼빼로", "1개", "3000" };
				String[] record1 = { "1", "빼빼로", "1개", "3000" };
				String[] record2 = { "1", "빼빼로", "1개", "3000" };
				String[] record3 = { "1", "빼빼로", "1개", "3000" };
				mTableModel.addRow(record);
				mTableModel.addRow(record1);
				mTableModel.addRow(record2);
				mTableModel.addRow(record3);
			}
		});

		// mTextArea0.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// // mTextArea0.setText(mTextArea0.getText() + "1\t바나나킥\t1\t1500\n");
		//
		// ProductItem pi = ProductItem.getPredefinedProductData()[0];
		// mTextArea0.setText(pi.toString());
		//
		// }
		// });
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

}
