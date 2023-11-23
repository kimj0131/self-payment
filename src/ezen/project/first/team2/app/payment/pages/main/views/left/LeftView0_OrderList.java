package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView0_OrderList extends View {
	private static final int PADDING = 10;

	private static final String INFO_MESSAGE_TEXT = "<html>구매하실<br>상품을<br>스캔해주세요</html>";
	private static final String SELF_INPUT_TEXT = "<html>과일/채소<br>직접입력</html>";

	JLabel mInfoMessage;
	JButton mSelfInputButton;

	public LeftView0_OrderList() {
		super(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
		
		mInfoMessage = new JLabel(INFO_MESSAGE_TEXT);
		mSelfInputButton = new JButton(SELF_INPUT_TEXT);
		
		mSelfInputButton.setFocusable(false);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mInfoMessage);
		this.add(this.mSelfInputButton);
	}

	@Override
	protected void onAddEventListeners() {
		mSelfInputButton.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedRightViewByNum(MainPage.POPUP_VIEW_FRUITS_SELECTOR_NUM);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
		mInfoMessage.setFont(main.mFont0);
	}
}
