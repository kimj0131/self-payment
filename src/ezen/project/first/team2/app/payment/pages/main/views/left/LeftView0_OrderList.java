package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView0_OrderList;

public class LeftView0_OrderList extends View {
	private static final int PADDING = 10;

	private static final String ORDER_LIST_TEXT = "<html>구매하실<br>상품을<br>스캔해주세요</html>";
	private static final String SELF_INPUT_TEXT = "<html>과일/채소<br>직접입력</html>";

	JLabel mLabel0 = new JLabel();
	JButton mButton0 = new JButton();

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
		
		this.mButton0.setText(SELF_INPUT_TEXT);
		this.add(this.mButton0);
	}

	@Override
	protected void onAddEventListeners() {
		mButton0.addActionListener(e -> {
			UiUtils.showMsgBox("과일/채소", MainPage.TITLE);
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mLabel0.setFont(main.mFont0);
	}
}
