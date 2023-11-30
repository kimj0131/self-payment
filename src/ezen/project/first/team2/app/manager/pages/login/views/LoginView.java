////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:YYYYMMDDddd_HHMMSS] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.manager.pages.login.views;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.admin.AdminSession;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.login.LoginPage;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class LoginView extends View {
	// -------------------------------------------------------------------------

	JLabel mLabelViewInfo = new JLabel("관리자 로그인");

	JPanel mPanelLogin = new JPanel();
	JPanel mPanelLoginId = new JPanel();
	JLabel mLabelLoginId = new JLabel(" ID : ");
	JTextField mTextFieldLoginId = new JTextField(15);

	JPanel mPanelLoginPassward = new JPanel();
	JLabel mLabelLoginPassward = new JLabel("PW : ");
	JPasswordField mTextFieldLoginPassward = new JPasswordField(15);

	JPanel mPanelLoginBtn = new JPanel();
	private JButton mBtnLogin = new JButton();

	// -------------------------------------------------------------------------

	// 생성자
	public LoginView() {
		super(LoginPage.VIEW_NUM_LOGIN);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
		this.setLayout(new GridLayout(2, 1));
		this.mPanelLogin.setLayout(new GridLayout(3, 1));

	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {
		this.mBtnLogin.setText("Login");

		this.mLabelViewInfo.setHorizontalAlignment(JLabel.CENTER);

		this.add(mLabelViewInfo);
		this.add(mPanelLogin);

		this.mPanelLogin.add(mPanelLoginId);
		this.mPanelLogin.add(mPanelLoginPassward);
		this.mPanelLogin.add(mPanelLoginBtn);

		this.mPanelLoginId.add(mLabelLoginId);
		this.mPanelLoginId.add(mTextFieldLoginId);

		this.mPanelLoginPassward.add(mLabelLoginPassward);
		this.mPanelLoginPassward.add(mTextFieldLoginPassward);

		this.mPanelLoginBtn.add(mBtnLogin);
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
		this.mBtnLogin.addActionListener(e -> {
			AdminSession admin = AdminSession.getInstance();

			String id = mTextFieldLoginId.getText();
			String pw = new String(mTextFieldLoginPassward.getPassword());

			if (admin.login(id, pw) == AdminSession.LoginResult.Ok) {
				UiUtils.showMsgBox("Login Success", MainPage.TITLE);
				try {
					Main main = (Main) LoginView.this.getStatusManager();
					main.setSelectedPageByNum(Main.PAGE_NUM_MAIN);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				UiUtils.showMsgBox("Login Fail..", MainPage.TITLE);
				// this.mTextFieldLoginId.setText("");
				this.mTextFieldLoginPassward.setText("");

				this.mTextFieldLoginId.requestFocus();
			}

		});

		this.mTextFieldLoginId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				// 시연용 자동기입
				if (e.isControlDown() && e.getKeyCode() == '1') {
					autoInsert();
					mTextFieldLoginPassward.requestFocus();
				} else
				// 엔터를 누르면 passward 텍스트필드로 포커스가 넘어간다
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mPanelLoginBtn.getRootPane().setDefaultButton(null);
					mTextFieldLoginPassward.requestFocus();
				}
			}
		});

		this.mTextFieldLoginPassward.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 시연용 자동기입
				if (e.isControlDown() && e.getKeyCode() == '1') {
					autoInsert();
				}
				// 엔터를 누르면 로그인버튼을 누른다.
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mPanelLoginBtn.getRootPane().setDefaultButton(mBtnLogin);
				}
			}
		});

	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[LoginView.onShow()]");
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[LoginView.onHide()]");
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mLabelViewInfo.setFont(main.mFont1);
		mLabelLoginId.setFont(main.mFont2);
		mLabelLoginPassward.setFont(main.mFont2);
		mTextFieldLoginId.setFont(main.mFont2);
		mTextFieldLoginPassward.setFont(main.mFont2);
	}

	// 시연용 id,pw 자동기입
	private void autoInsert() {
		mTextFieldLoginId.setText("admin");
		mTextFieldLoginPassward.setText("qwer1234!@#$");
	}
}
