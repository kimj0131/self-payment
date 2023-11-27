package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;

public class LeftView0_OrderList extends View {
	private static final int PADDING = 20;

	private static final String MSG_LABEL_TEXT = "<html>구매하실<br>상품을<br>스캔해주세요</html>";
	private static final String SELF_INPUT_BTN_TEXT = "<html>과일/채소<br>직접입력</html>";
	
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	private static final Font TITLE_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	
	

	JLabel mMsg_label;
	JButton mSelfInput_btn;

	public LeftView0_OrderList() {
		super(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
	}

	@Override
	protected void onInit() {

		setBackground(BACKGROUND_COLOR);
		
		mMsg_label = new JLabel(MSG_LABEL_TEXT);
		mSelfInput_btn = new JButton(SELF_INPUT_BTN_TEXT);
		
		mSelfInput_btn.setFocusable(false);
		
		// 디자인 관련 설정
		mMsg_label.setFont(TITLE_FONT);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BorderLayout());
	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mMsg_label, BorderLayout.NORTH);
		this.add(this.mSelfInput_btn, BorderLayout.SOUTH);
	}

	@Override
	protected void onAddEventListeners() {
		mSelfInput_btn.addActionListener(e -> {
			try {
				MainPage mainPage = (MainPage) this.getPage();
				mainPage.showPopupViewByNum(MainPage.POPUP_VIEW_FRUITS_SELECTOR_NUM);
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
		mMsg_label.setFont(main.mFont0);
	}
}
