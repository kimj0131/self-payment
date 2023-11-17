package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView3_Payment extends View {
	
	private static final int PADDING = 10;
	
	JButton mButton0 = new JButton("결제완료");

	public RightView3_Payment() {
		super(MainPage.RIGHT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
	}

	@Override
	protected void onAddCtrls() {
		this.add(mButton0);
	}

	@Override
	protected void onAddEventListeners() {}

	@Override
	protected void onShow() {}

	@Override
	protected void onHide() {}
	
	
}
