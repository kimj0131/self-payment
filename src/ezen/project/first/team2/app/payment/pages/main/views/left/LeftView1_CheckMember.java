package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView1_CheckMember extends View {
	private static final int PADDING = 20;

	private static final String TITLE_LABEL_TEXT = "<html>회원인 경우<br>휴대폰 번호를<br>입력해주세요</html>";
	private static final String PREV_BTN_TEXT = "이전단계";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// title
	private static final Font TITLE_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color TITLE_FONT_COLOR = new Color(103, 121, 133);
	
	// Prev button
	private static final Font PREV_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color PREV_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color PREV_BTN_COLOR = new Color(21, 150, 136);

	JLabel mTitle_label;
	JButton mPrev_btn;
	
	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;
	
	public LeftView1_CheckMember() {
		super(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
	}

	@Override
	protected void onInit() {
		mTitle_label = new JLabel(TITLE_LABEL_TEXT);
		mPrev_btn = new JButton(PREV_BTN_TEXT);
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();
	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		
		// 디자인 관련 설정
		mTitle_label.setFont(TITLE_FONT);
		mTitle_label.setForeground(TITLE_FONT_COLOR);
		
		mPrev_btn.setFont(PREV_BTN_FONT);
		mPrev_btn.setBackground(PREV_BTN_COLOR);
		mPrev_btn.setForeground(PREV_BTN_FONT_COLOR);
	}

	@Override
	protected void onAddCtrls() {
		mGbc.anchor = GridBagConstraints.NORTH;
		mGbc.weighty = 1;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(this.mTitle_label, mGbc);
		
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weighty = 0.05;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(this.mPrev_btn, mGbc);
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
		mTitle_label.setFont(main.mFont0);
	}
}
