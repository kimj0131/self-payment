////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231114TUE_101700] Created
// [SGLEE:20231114TUE_101900] 재활용 가능한 뷰 영역
// [SGLEE:20231115WED_124200] 뷰 또는 컨트롤 관리
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common;

import java.awt.Dimension;

import javax.swing.JPanel;

public class View extends JPanel {
	// -------------------------------------------------------------------------

	private StatusManager mStatusManager = null;
	private Page mPage = null;

	private boolean mInited = false;
	private int mNumber = -1;

	// -------------------------------------------------------------------------

	// 생성자
	public View(int num, Dimension size) {
		this.mNumber = num;
		// this.setSize(size);
	}

	// -------------------------------------------------------------------------

	// 상태 관리자 객체 얻기 -> 상속받은 클래스(ex, Main)로 캐스팅 후 사용
	public StatusManager getStatusManager() {
		return this.mStatusManager;
	}

	// 페이지 객체 얻기
	public Page getPage() {
		return this.mPage;
	}

	// 뷰 번호 얻기
	public int getNumber() {
		return this.mNumber;
	}

	// 뷰 초기화 -> Page에서 뷰를 추가할 때 호출됨
	public void init(StatusManager stsMngr, Page page) {
		if (this.mInited) {
			System.out.println("[View.init()] Already initialized!");
			return;
		}

		this.mStatusManager = stsMngr;
		this.mPage = page;

		this.onInit();
		//
		this.onSetLayout();
		this.onAddCtrls();
		this.onAddEventListeners();
		//

		// this.setVisible(false);
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	protected void onInit() {
	}

	// 레이아웃 설정
	protected void onSetLayout() {
	}

	// 컨트롤 추가
	protected void onAddCtrls() {
	}

	// 이벤트 리스너 추가
	protected void onAddEventListeners() {
		//
	}

	// 뷰가 표시될 때 -> Page에서 호출됨
	protected void onShow() {
	}

	// 뷰가 숨겨질 때 -> Page에서 호출됨
	protected void onHide() {
	}
}
