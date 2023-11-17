package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;


public class LeftView0_OrderList extends View {
	private static final int PADDING = 10;
	
	private static final String ORDER_LIST_TEXT = "Order List";

	JLabel mLabel0 = new JLabel();
	JLabel mLabel1 = new JLabel("View");

	public LeftView0_OrderList() {
		super(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		this.mLabel0.setText(ORDER_LIST_TEXT);
		
		this.add(this.mLabel0);
		this.add(this.mLabel1);
	}

	@Override
	protected void onAddEventListeners() {}

	@Override
	protected void onShow() {}

	@Override
	protected void onHide() {}
	
	
}
