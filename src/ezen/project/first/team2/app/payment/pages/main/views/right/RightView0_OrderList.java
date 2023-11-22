package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView0_OrderList extends View {

	private static final int PADDING = 10;

	GridBagConstraints gbc = new GridBagConstraints();

	JTextArea mTextArea0 = new JTextArea("상품번호\t상품명\t상품개수\t상품가격\n");
	JScrollPane mTextArea0_Scoll = new JScrollPane(mTextArea0);

	JTextArea mTextArea1 = new JTextArea("합계\n");

	JButton mButton0 = new JButton("결제하기");

	public RightView0_OrderList() {
		super(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
	}

	@Override
	protected void onAddCtrls() {
		mTextArea0.setEditable(false);
		mTextArea1.setEditable(false);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 3;

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(mTextArea0_Scoll, gbc);

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

		mTextArea0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// mTextArea0.setText(mTextArea0.getText() + "1\t바나나킥\t1\t1500\n");

				ProductItem pi = ProductItem.getPredefinedProductData()[0];
				mTextArea0.setText(pi.toString());

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
