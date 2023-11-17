package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;


public class LeftView3_Payment extends View {
	private static final int PADDING = 10;

	JLabel mLabel0 = new JLabel("Payment");
	JLabel mLabel1 = new JLabel("View");

	public LeftView3_Payment() {
		super(MainPage.LEFT_VIEW_PAYMENT_NUM);
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
