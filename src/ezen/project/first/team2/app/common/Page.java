////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231113MON_014200] Created
// [SGLEE:20231114TUE_101800] 화면 표시
// [SGLEE:20231115WED_124200] 뷰 관리
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Page extends JFrame {
	// -------------------------------------------------------------------------

	public static final int OPTION_CENTER_IN_SCREEN = 0x0001;
	public static final int OPTION_VISIBLE = 0x0002;
	public static final int OPTION_BORDERLESS = 0x0004;
	public static final int OPTION_FULL_SCREEN = 0x0008;
	public static final int OPTION_FIXED_SIZE = 0x0010;

	// -------------------------------------------------------------------------

	// private static boolean DEBUG_MODE = true;
	// private static boolean TEST_MODE = true;

	// -------------------------------------------------------------------------

	private StatusManager mStatusManager = null;
	private int mOptions = 0;

	private List<View> mViewList = new ArrayList<>();
	private int mSelectedViewNum = -1;

	private boolean mInited = false;
	private int mNumber = -1;

	// -------------------------------------------------------------------------

	// 생성자
	public Page(int num, String title, Dimension size, int opts) {
		// if (DEBUG_MODE) {
		// System.out.printf("[Page.ctor()] num:%d, title:%s, size:%s \n",
		// num, title, UnitUtils.dimToStr(size));
		// }

		this.mOptions = opts;

		this.mNumber = num;
		this.setTitle(title);
		this.setSize(size);
	}

	// -------------------------------------------------------------------------

	// 상태 관리자 객체 얻기 -> 상속받은 클래스(ex, Main)로 캐스팅 후 사용
	public StatusManager getStatusManager() {
		return this.mStatusManager;
	}

	// -------------------------------------------------------------------------

	// 페이지 번호 얻기
	public int getNumber() {
		return this.mNumber;
	}

	// 페이지 초기화 -> Status Manager에서 페이지를 추가할 때 호출됨
	public void init(StatusManager stsMngr) {
		if (this.mInited) {
			System.out.println("[Page.init()] Already initialized!");
			return;
		}

		this.mStatusManager = stsMngr;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.onInit();
		this.onAddViews();
		this.onAddEventListeners();

		this.applyOptions();
	}

	// -------------------------------------------------------------------------

	// 뷰 추가 - 추가하면서 init() 메소드 호출
	public void addView(View view) throws Exception {
		// 뷰 번호 유효성 확인
		int viewNum = view.getNumber();
		if (viewNum == -1 || this.isValidViewNum(viewNum)) {
			String msg = String.format(
					"[StatusManager.addView()] " +
							" Invalid or duplicated view number! => %d",
					viewNum);
			throw new Exception(msg);
		}

		// 뷰 초기화 후 리스트에 추가
		view.init(this.mStatusManager, this);
		this.mViewList.add(view);
	}

	// 뷰 개수 얻기
	public int getViewCount() {
		return this.mViewList.size();
	}

	// 뷰 번호로 객체 얻기
	public View getViewByNum(int num) {
		for (View view : this.mViewList) {
			if (view.getNumber() == num)
				return view;
		}

		return null;
	}

	// 뷰 번호로 선택
	public void selectViewByNum(int num) throws Exception {
		if (num == this.mSelectedViewNum) {
			System.out.printf(
					"[Page.selectViewByNum()] Same view number(%d)! \n",
					num);
			return;
		}

		// 유효한 뷰 번호인지 확인한다
		if (!this.isValidViewNum(num)) {
			String msg = String.format(
					"[Page.selectViewByNum()] " +
							"Invalid view number(%d)!",
					num);
			throw new Exception(msg);
		}

		// 선택한 뷰 표시
		this.mSelectedViewNum = num;
		View newView = this.getViewByNum(num);
		newView.onShow();

		// [SGLEE:20231115WED_153300] 기존 컨텐츠를 삭제하고 뷰를 추가한다
		Container cp = this.getContentPane();
		cp.removeAll();
		cp.add(newView);
		revalidate();
		repaint();
	}

	// 선택된 뷰 번호 얻기
	public int getSelectedViewNum() {
		return this.mSelectedViewNum;
	}

	// 선택된 뷰 객체 얻기
	public View getSelectedView() {
		return this.getViewByNum(this.getSelectedViewNum());
	}

	// -------------------------------------------------------------------------

	// 초기화 작업
	protected void onInit() {
	}

	// 뷰 추가
	protected void onAddViews() {
	}

	// 이벤트 리스너 추가
	protected void onAddEventListeners() {
	}

	// 페이지가 표시될 때 -> Status Manager에서 호출됨
	protected void onShow() {
	}

	// 페이지가 숨겨질 때 -> Status Manager에서 호출됨
	protected void onHide() {
	}

	// -------------------------------------------------------------------------

	private void applyOptions() {
		// 화면 중앙에 배치
		if ((this.mOptions & OPTION_CENTER_IN_SCREEN) == OPTION_CENTER_IN_SCREEN)
			this.setLocationRelativeTo(null);

		// [SGLEE:20231113MON_002300] 컨트롤 추가 후 표시해야한다
		if ((this.mOptions & OPTION_VISIBLE) == OPTION_VISIBLE)
			this.setVisible(true);

		if ((this.mOptions & OPTION_BORDERLESS) == OPTION_BORDERLESS)
			this.setUndecorated(true);

		if ((this.mOptions & OPTION_FULL_SCREEN) == OPTION_FULL_SCREEN)
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		if ((this.mOptions & OPTION_FIXED_SIZE) == OPTION_FIXED_SIZE)
			this.setResizable(false);
	}

	// -------------------------------------------------------------------------

	private boolean isValidViewNum(int num) {
		for (View view : this.mViewList) {
			if (view.getNumber() == num)
				return true;
		}

		return false;
	}
}