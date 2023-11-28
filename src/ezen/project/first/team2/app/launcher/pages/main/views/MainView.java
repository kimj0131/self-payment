////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231117FRI_124500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.launcher.pages.main.views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.launcher.pages.main.MainPage;

public class MainView extends View {
	// -------------------------------------------------------------------------

	private JButton mBtn0 = new JButton();
	private JButton mBtn1 = new JButton();
	private JButton mBtn2 = new JButton();
	
	JFrame frame = new JFrame("Box Layout");



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
	      this.setBackground(Color.PINK);

	     

	}

	// 레이아웃 설정
	@Override
	protected void onSetLayout() {
		 GridLayout g1 = new GridLayout(1, 3);
	      
	      g1.setHgap(30);
	      g1.setVgap(30);
	      
	      setLayout(new GridLayout(1, 10, 50, 50));
	      
	      setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

	}

	// 컨트롤 추가
	@Override
	protected void onAddCtrls() {
		this.mBtn0.setText("Manage- ment app");
		this.mBtn1.setText("New User app");
		this.mBtn2.setText("Payment app");


		  add(mBtn0);
	      add(mBtn1);
	      add(mBtn2);


	}

	// 이벤트 리스너 추가
	@Override
	protected void onAddEventListeners() {
		this.mBtn0.addActionListener(e -> {
		    UiUtils.showMsgBox("LOADING...", MainPage.TITLE);
	      });

	      mBtn1.addActionListener(e -> {
	         
	         UiUtils.showMsgBox("LOADING...", MainPage.TITLE);
	      });

	      mBtn2.addActionListener(e -> {
	         
	         UiUtils.showMsgBox("LOADING...", MainPage.TITLE);
	      });
	   
	      this.mBtn0.addActionListener(e -> {
	         UiUtils.showMsgBox("Login...", MainPage.TITLE);
	         
	      });
	      this.mBtn1.addActionListener(e -> {
	         UiUtils.showMsgBox("Login...", MainPage.TITLE);
	         
	      });
	      this.mBtn2.addActionListener(e -> {
	         UiUtils.showMsgBox("Login...", MainPage.TITLE);
	         
	      });

	}

	// 뷰가 표시될 때
	@Override
	protected void onShow(boolean firstTime) {
		// System.out.println("[MainView.onShow()]");
	}

	// 뷰가 숨겨질 때
	@Override
	protected void onHide() {
		// System.out.println("[MainView.onHide()]");
	}
}
