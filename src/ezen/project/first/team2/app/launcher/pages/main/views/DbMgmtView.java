package ezen.project.first.team2.app.launcher.pages.main.views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.base.ListActionAdapter;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountItem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStockItem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.launcher.Main;
import ezen.project.first.team2.app.launcher.pages.main.MainPage;

public class DbMgmtView extends View {
	// -------------------------------------------------------------------------

	private enum TableName {
		Customers, Products, // ProductStocks, ProductDiscounts,
		ProductOrders, ProductOrderDetails
	}

	private enum CmdType {
		Create, Drop, Truncate, ListRecordset
	}

	class BtnInfo {
		private TableName mTableName;
		private CmdType mCmdType;

		public BtnInfo(TableName tblname, CmdType cmdType) {
			this.mTableName = tblname;
			this.mCmdType = cmdType;
		}

		public TableName getTableName() {
			return this.mTableName;
		}

		public CmdType getCmdType() {
			return this.mCmdType;
		}

		@Override
		public String toString() {
			return String.format("%s, %s", this.getTableName(), this.getCmdType());
		}

		@Override
		public boolean equals(Object arg0) {
			var that = (BtnInfo) arg0;

			return this.getTableName() == that.getTableName() &&
					this.getCmdType() == that.getCmdType();
		}
	}

	// -------------------------------------------------------------------------

	// 생성자
	public DbMgmtView() {
		super(MainPage.VIEW_NUM_DB_MGMT);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
		GridLayout grid = new GridLayout(0, 1 + CmdType.values().length, 10, 10);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(grid);
	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {
		ActionListener listener = e -> {
			var custMngr = CustomerManager.getInstance();
			var prodMngr = ProductManager.getInstance();
			var prodStocksMngr = ProductStocksManager.getInstance();
			var prodDiscntsMngr = ProductDiscountsManager.getInstance();
			var prodOrdersMngr = ProductOrdersManager.getInstance();
			var prodOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

			try {
				int result = JOptionPane.showConfirmDialog(null, "continue?", MainPage.TITLE,
						JOptionPane.YES_NO_OPTION);
				if (result != JOptionPane.YES_OPTION)
					return;

				var btnInfo = (BtnInfo) ((JButton) e.getSource()).getClientProperty("info");
				var tblName = btnInfo.getTableName();
				var cmdType = btnInfo.getCmdType();
				// ---------------------------------------------------
				if (tblName == TableName.Customers) {
					switch (cmdType) {
						case Create:
							custMngr.createTable();
							for (var ci : CustomerItem.getPredefinedData()) {
								custMngr.add(ci);
							}
							custMngr.iterate((item, idx) -> {
								try {
									custMngr.doInsertQuery(item);
									SystemUtils.sleep(100);
								} catch (Exception ex) {
									ex.printStackTrace();
								}

								return true;
							});
							break;

						case Drop:
							custMngr.dropTable();
							break;

						case Truncate:
							custMngr.truncateTable();
							break;

						case ListRecordset:
							custMngr.iterate();
							break;
					}
				}
				// ---------------------------------------------------
				else if (tblName == TableName.Products) {
					switch (cmdType) {
						case Create:
							prodMngr.createTable();
							prodStocksMngr.createTable();
							prodDiscntsMngr.createTable();

							prodMngr.setActionListener(new ListActionAdapter<ProductItem>() {
								@Override
								public void onAdded(ListManager<ProductItem> mngr, ProductItem item) {
									try {
										// [상품 재고 관리자]에 상품 추가
										var psi = new ProductStockItem(item.getId());
										prodStocksMngr.add(psi);
										prodStocksMngr.doInsertQuery(psi);

										// [상품 할인 관리자]에 상품 추가
										var pdi = new ProductDiscountItem(item.getId());
										prodDiscntsMngr.add(pdi);
										prodDiscntsMngr.doInsertQuery(pdi);

									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});

							for (var ci : ProductItem.getPredefinedProductData()) {
								prodMngr.add(ci);
							}

							prodMngr.iterate((item, idx) -> {
								try {
									prodMngr.doInsertQuery(item);
									SystemUtils.sleep(100);
								} catch (Exception ex) {
									ex.printStackTrace();
								}

								return true;
							});
							break;

						case Drop:
							prodMngr.dropTable();
							prodStocksMngr.dropTable();
							prodDiscntsMngr.dropTable();
							break;

						case Truncate:
							prodMngr.truncateTable();
							prodStocksMngr.truncateTable();
							prodDiscntsMngr.truncateTable();
							break;

						case ListRecordset:
							prodMngr.iterate();
							break;
					}
				}
				// ---------------------------------------------------
				else if (tblName == TableName.ProductOrders) {
					switch (cmdType) {
						case Create:
							prodOrdersMngr.createTable();
							break;

						case Drop:
							prodOrdersMngr.dropTable();
							break;

						case Truncate:
							prodOrdersMngr.truncateTable();
							break;

						case ListRecordset:
							prodOrdersMngr.iterate();
							break;
					}
				}
				// ---------------------------------------------------
				else if (tblName == TableName.ProductOrderDetails) {
					switch (cmdType) {
						case Create:
							prodOrderDetailsMngr.createTable();
							break;

						case Drop:
							prodOrderDetailsMngr.dropTable();
							break;

						case Truncate:
							prodOrderDetailsMngr.truncateTable();
							break;

						case ListRecordset:
							prodOrderDetailsMngr.iterate();
							break;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.enableCtrls();
		};

		for (

		var tblName : TableName.values()) {
			this.add(new JLabel(tblName + ""));
			for (var cmdType : CmdType.values()) {
				var btn = new JButton(cmdType + "");
				btn.putClientProperty("info", new BtnInfo(tblName, cmdType));
				btn.addActionListener(listener);
				this.add(btn);
			}
		}
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {

		//
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 오른쪽 버튼
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						var main = (Main) getStatusManager();
						var mainPage = (MainPage) main.getPageByNum(Main.PAGE_NUM_MAIN);
						mainPage.setSelectedViewByNum(MainPage.VIEW_NUM_MAIN);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});
	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		this.enableCtrls();
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		//
	}

	// -------------------------------------------------------------------------

	private JButton getBtnByInfo(BtnInfo info) {
		for (var comp : getComponents()) {
			if (comp instanceof JButton) {
				var btn = (JButton) comp;
				if (btn.getClientProperty("info").equals(info))
					return btn;
			}
		}

		return null;
	}

	private List<JButton> getBtnsByTableName(TableName tblName) {
		List<JButton> btns = new ArrayList<>();

		btns.add(this.getBtnByInfo(new BtnInfo(tblName, CmdType.Create)));
		btns.add(this.getBtnByInfo(new BtnInfo(tblName, CmdType.Drop)));
		btns.add(this.getBtnByInfo(new BtnInfo(tblName, CmdType.Truncate)));
		btns.add(this.getBtnByInfo(new BtnInfo(tblName, CmdType.ListRecordset)));

		return btns;
	}

	private void enableCtrls() {
		var custMngr = CustomerManager.getInstance();
		var prodMngr = ProductManager.getInstance();
		// var prodStocksMngr = ProductStocksManager.getInstance();
		// var pdocDiscntsMngr = ProductDiscountsManager.getInstance();
		var prodOrdersMngr = ProductOrdersManager.getInstance();
		var prodOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

		try {
			// customers
			{
				var hasTable = custMngr.hasTable();
				var btns = this.getBtnsByTableName(TableName.Customers);
				var createTblBtn = btns.get(0);
				var dropTblBtn = btns.get(1);
				var truncateTblBtn = btns.get(2);
				var listRecordsetBtn = btns.get(3);

				createTblBtn.setEnabled(!hasTable);
				dropTblBtn.setEnabled(hasTable);
				truncateTblBtn.setEnabled(hasTable);
				listRecordsetBtn.setEnabled(hasTable);

				createTblBtn.setEnabled(!hasTable);
				dropTblBtn.setEnabled(hasTable);
				truncateTblBtn.setEnabled(hasTable);
			}

			// products
			{
				var hasTable = prodMngr.hasTable();
				var btns = this.getBtnsByTableName(TableName.Products);
				var createTblBtn = btns.get(0);
				var dropTblBtn = btns.get(1);
				var truncateTblBtn = btns.get(2);
				var listRecordsetBtn = btns.get(3);

				createTblBtn.setEnabled(!hasTable);
				dropTblBtn.setEnabled(hasTable);
				truncateTblBtn.setEnabled(hasTable);
				listRecordsetBtn.setEnabled(hasTable);
			}

			// product_orders
			{
				var hasTable = prodOrdersMngr.hasTable();
				var btns = this.getBtnsByTableName(TableName.ProductOrders);
				var createTblBtn = btns.get(0);
				var dropTblBtn = btns.get(1);
				var truncateTblBtn = btns.get(2);
				var listRecordsetBtn = btns.get(3);

				createTblBtn.setEnabled(!hasTable);
				dropTblBtn.setEnabled(hasTable);
				truncateTblBtn.setEnabled(hasTable);
				listRecordsetBtn.setEnabled(hasTable);

			}

			// product_order_details
			{
				var hasTable = prodOrderDetailsMngr.hasTable();
				var btns = this.getBtnsByTableName(TableName.ProductOrderDetails);
				var createTblBtn = btns.get(0);
				var dropTblBtn = btns.get(1);
				var truncateTblBtn = btns.get(2);
				var listRecordsetBtn = btns.get(3);

				createTblBtn.setEnabled(!hasTable);
				dropTblBtn.setEnabled(hasTable);
				truncateTblBtn.setEnabled(hasTable);
				listRecordsetBtn.setEnabled(hasTable);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
