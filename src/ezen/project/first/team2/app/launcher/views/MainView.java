package ezen.project.first.team2.app.launcher.views;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import ezen.project.first.team2.app.framework.View;
import ezen.project.first.team2.app.launcher.MainPage;
import ezen.project.first.team2.utils.UiUtils;

public class MainView extends View {
	private static final int PADDING = 10;

	private static final String MGMT_BTN_TEXT = "관리 앱 실행";
	private static final String NEW_USER_BTN_TEXT = "사용자 추가 앱 실행";
	private static final String PAYMENT_BTN_TEXT = "결제 앱 실행";

	private static final Font BTN_FONT = new Font("맑은 고딕", Font.PLAIN, 14);

	private JButton mRunMgmtAppBtn = new JButton();
	private JButton mRunNewUserAppBtn = new JButton();
	private JButton mRunPaymentAppBtn = new JButton();

	public MainView() {
		super(MainPage.VIEW_NUM_MAIN);
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout(1, 3, 10, 10));
	}

	@Override
	protected void onAddCtrls() {
		this.mRunMgmtAppBtn.setText(MGMT_BTN_TEXT);
		this.mRunNewUserAppBtn.setText(NEW_USER_BTN_TEXT);
		this.mRunPaymentAppBtn.setText(PAYMENT_BTN_TEXT);

		this.mRunMgmtAppBtn.setFont(BTN_FONT);
		this.mRunNewUserAppBtn.setFont(BTN_FONT);
		this.mRunPaymentAppBtn.setFont(BTN_FONT);

		this.add(mRunMgmtAppBtn);
		this.add(mRunNewUserAppBtn);
		this.add(mRunPaymentAppBtn);
	}

	@Override
	protected void onAddEventListeners() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton sender = (JButton) e.getSource();

				// [관리 앱 실행] 버튼
				if (sender == MainView.this.mRunMgmtAppBtn) {
					UiUtils.showMsgBox("관리 앱 실행", MainPage.TITLE);
				}
				// [사용자 추가 앱 실행] 버튼
				else if (sender == MainView.this.mRunNewUserAppBtn) {
					UiUtils.showMsgBox("사용자 추가 앱 실행", MainPage.TITLE);
				}
				// [결제 앱 실행] 버튼
				else if (sender == MainView.this.mRunPaymentAppBtn) {
					UiUtils.showMsgBox("결제 앱 실행", MainPage.TITLE);
				}
			}
		};

		this.mRunMgmtAppBtn.addActionListener(listener);
		this.mRunNewUserAppBtn.addActionListener(listener);
		this.mRunPaymentAppBtn.addActionListener(listener);
	}

	@Override
	protected void onShow() {
	}

	@Override
	protected void onHide() {
	}
}
