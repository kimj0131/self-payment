package ezen.project.first.team2.app.common.z_test.etc;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ezen.project.first.team2.app.common.modules.database.DBConnector;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;

public class TestJPanelTest {
	public static void main(String[] args) {
		var dbConn = DBConnector.getInstance();
		try {
			dbConn.loadJdbcDriver();
			dbConn.connect("localhost", 1521, "hr", "1234");

			var f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(420, 600);
			f.setLocationRelativeTo(null);
			f.setVisible(true);

			var prodMngr = ProductManager.getInstance();
			var prodStocksMngr = ProductStocksManager.getInstance();
			var prodDiscntsMngr = ProductDiscountsManager.getInstance();

			var prodOrdersMngr = ProductOrdersManager.getInstance();
			var prodOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

			prodMngr.doSelectQuery();
			prodStocksMngr.doSelectQuery();
			prodDiscntsMngr.doSelectQuery();

			int poId = 9;
			prodOrdersMngr.doSelectQuery(null, null, "prod_order_id=" + poId, null);
			var poi = prodOrdersMngr.getFirstItem();
			prodOrdersMngr.iterate();

			prodOrderDetailsMngr.doSelectQuery(null, null, "prod_order_id=" + poi.getId(), null);
			prodOrderDetailsMngr.iterate();

			var rv = new ReceiptView(poi);
			// int w = f.getContentPane().getWidth();
			// int h = f.getContentPane().getHeight();
			// rv.setSize(w, h);
			rv.setPreferredSize(new Dimension(1000, 1000));
			// f.add(rv);
			rv.repaint();

			var scrPane = new JScrollPane(rv, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrPane.setPreferredSize(new Dimension(1000, 1000));

			scrPane.add(rv);
			scrPane.repaint();

			f.add(scrPane);

			dbConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
