package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView1_CheckMember extends View {
	private static final int PADDING = 10;

	private static final String MSG_LABEL_TEXT = "<html>회원인 경우<br>휴대폰 번호를<br>입력해주세요</html>";
	private static final String PREV_BTN_TEXT = "이전단계";

	JLabel mMsg_label;
	JButton mPrev_btn;
	
	public LeftView1_CheckMember() {
		super(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
		
		mMsg_label = new JLabel(MSG_LABEL_TEXT);
		mPrev_btn = new JButton(PREV_BTN_TEXT);
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mMsg_label);
		this.add(this.mPrev_btn);
	}

	@Override
	protected void onAddEventListeners() {
		mPrev_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
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
