package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.payment.pages.main.MainPage;

public class PopUpView2_UnverifiedMemberInfo extends PopupView {

	private static final int PADDING = 20;
	private static final Dimension VIEW_SIZE = new Dimension(600, 500);

	private static final String MSG_LABEL_TEXT = "<html><center>회원정보를 찾지 못하였습니다<br>휴대폰 번호를 확인해 주세요</center></html>";
	private static final String CHECK_BTN_TEXT = "확인";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// Msg Label
	private static final Font MASSAGE_LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color MASSAGE_LABEL_FONT_COLOR = new Color(103, 121, 133);
	private static final Color MASSAGE_LABEL_COLOR = new Color(255, 255, 255);
	
	// Check Button
	private static final Font CHECK_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color CHECK_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color CHECK_BTN_COLOR = new Color(3, 181, 208);

	JLabel mMsg_label;
	JButton mCheck_btn;
	
	GridBagConstraints mGbc;

	public PopUpView2_UnverifiedMemberInfo() {
		super(MainPage.POPUP_VIEW_UNVERIFIED_MEMBER_INFO_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		super.onInit();

		mMsg_label = new JLabel(MSG_LABEL_TEXT);
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		
		mGbc = new GridBagConstraints();
	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		
		// 디자인 관련 설정
		mMsg_label.setOpaque(true);
		mMsg_label.setBackground(MASSAGE_LABEL_COLOR);
		mMsg_label.setForeground(MASSAGE_LABEL_FONT_COLOR);
		mMsg_label.setFont(MASSAGE_LABEL_FONT);
		mMsg_label.setHorizontalAlignment(JLabel.CENTER);
		
		mCheck_btn.setBackground(CHECK_BTN_COLOR);
		mCheck_btn.setForeground(CHECK_BTN_FONT_COLOR);
		mCheck_btn.setFont(CHECK_BTN_FONT);
	}

	@Override
	protected void onAddCtrls() {
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 1;
		mGbc.weighty = 1;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		add(mMsg_label, mGbc);
		
		mGbc.insets = new Insets(PADDING, 0, 0, 0);
		
		mGbc.weighty = 0.06;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		add(mCheck_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mCheck_btn.addActionListener(e -> {
			try {
				this.performClose();
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
	}

}
