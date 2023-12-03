////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_124500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.launcher.pages.main.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.launcher.Main;
import ezen.project.first.team2.app.launcher.pages.main.MainPage;

public class MainView extends View {
	// -------------------------------------------------------------------------

	private final String JAR_PATH = "self-payment.jar";
	private final String MGMT_MAIN_CLS_PATH = "ezen.project.first.team2.app.manager.Main";
	private final String NEWUSER_MAIN_CLS_PATH = "ezen.project.first.team2.app.newuser.Main";
	private final String PAY_MAIN_CLS_PATH = "ezen.project.first.team2.app.payment.Main";

	// -------------------------------------------------------------------------

	private JButton mRunMgmtAppBtn = new JButton();
	private JButton mRunNewUserAppBtn = new JButton();
	private JButton mRunPayAppBtn = new JButton();

	JFrame frame = new JFrame();

	// -------------------------------------------------------------------------

	// 생성자
	public MainView() {
		super(MainPage.VIEW_NUM_MAIN);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	@Override
	protected void onInit() {
		super.onInit();
		this.setBackground(new Color(0xef, 0xf7, 0xff));
	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
		GridLayout grid = new GridLayout(0, 3, 20, 20);

		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		setLayout(grid);
	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {
		this.mRunMgmtAppBtn.setText("관리자 앱");
		this.mRunNewUserAppBtn.setText("사용자 등록 앱");
		this.mRunPayAppBtn.setText("결제 앱");

		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		this.mRunMgmtAppBtn.setFont(font);
		this.mRunNewUserAppBtn.setFont(font);
		this.mRunPayAppBtn.setFont(font);

		this.add(mRunMgmtAppBtn);
		this.add(mRunNewUserAppBtn);
		this.add(mRunPayAppBtn);
	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
		ActionListener runBtnListener = e -> {
			var btn = (JButton) e.getSource();
			if (btn == this.mRunMgmtAppBtn) {
				SystemUtils.runJarFile(JAR_PATH, MGMT_MAIN_CLS_PATH);
			} else if (btn == this.mRunNewUserAppBtn) {
				SystemUtils.runJarFile(JAR_PATH, NEWUSER_MAIN_CLS_PATH);
			} else if (btn == this.mRunPayAppBtn) {
				SystemUtils.runJarFile(JAR_PATH, PAY_MAIN_CLS_PATH);
			}

			// UiUtils.showMsgBox(btn.getText(), MainPage.TITLE);
		};

		this.mRunMgmtAppBtn.addActionListener(runBtnListener);
		this.mRunNewUserAppBtn.addActionListener(runBtnListener);
		this.mRunPayAppBtn.addActionListener(runBtnListener);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 오른쪽 버튼
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						var main = (Main) getStatusManager();
						var mainPage = (MainPage) main.getPageByNum(Main.PAGE_NUM_MAIN);
						mainPage.setSelectedViewByNum(MainPage.VIEW_NUM_DB_MGMT);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		System.out.println("[MainView.onShow()]");
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		System.out.println("[MainView.onHide()]");
	}
}
