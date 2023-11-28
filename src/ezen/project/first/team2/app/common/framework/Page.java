////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231113MON_014200] Created
// [SGLEE:20231114TUE_101800] 화면 표시
// [SGLEE:20231115WED_124200] 뷰 관리
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.framework;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
	private JLayeredPane mLayeredPane = this.getLayeredPane();
	private DimmedView mDimmedView = new DimmedView();

	private boolean mInited = false;
	private int mNumber = -1;

	private boolean mFirstTimeShow = true;

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

		// 페이지 초기화 오버라이드 호출
		this.onInit();

		// 옵션 적용
		this.applyOptions();

		// [SGLEE:20231127MON_120500]
		// 다음과 같이 하면 this.getContentPane()을 통해 뷰 크기를 얻을 수 있다
		this.setPreferredSize(this.getSize());
		this.pack();

		// 뷰 추가 오버라이드 호출
		this.onAddViews();

		// 이벤트 리스너 오버라이드 호출
		this.onAddEventListeners();

		// 팝업 뷰 관련 - dimmed view 추가
		this.mDimmedView.init(this);
	}

	// 뷰 리스트 얻기
	public List<View> getViewList() {
		return this.mViewList;
	}

	// -------------------------------------------------------------------------

	void performShow() {
		// System.out.println("[Page.performShow()] begin");

		this.onShow(this.isFirstTimeShow());
		this.setFirstTimeShow(false);

		this.setVisible(true);

		// 페이지에 추가된 뷰들의 크기를 설정한다
		// var viewSize = this.getContentPane().getSize();
		// var view = this.getSelectedView();
		// view.setSize(viewSize);

		// System.out.println("[Page.performshow()] end ");
	}

	void performHide() {
		// System.out.println("[Page.performHide()] begin");

		this.onHide();
		this.setFirstTimeShow(true);

		this.setVisible(false);

		// System.out.println("[Page.performHide()] end ");
	}

	void performSetResources() {
		for (View view : this.mViewList) {
			view.performSetResources();
		}
	}

	void setFirstTimeShow(boolean flag) {
		this.mFirstTimeShow = flag;
	}

	boolean isFirstTimeShow() {
		return this.mFirstTimeShow;
	}

	JPanel getDimmedView() {
		return this.mDimmedView;
	}

	// -------------------------------------------------------------------------

	// 뷰 추가 - 추가하면서 init() 메소드 호출
	public void addView(View view) throws Exception {
		// 뷰 번호 유효성 확인
		int viewNum = view.getNumber();
		if (viewNum == -1 || this.isValidViewNum(viewNum)) {
			String msg = String.format(
					"[Page.addView()] " +
							" Invalid or duplicated view number(%d)!",
					viewNum);
			throw new Exception(msg);
		}

		// 뷰 크기가 (0,0)인 경우 뷰 사이즈로 설정
		if (view.getSize().getWidth() == 0 || view.getSize().getHeight() == 0) {
			Dimension size = this.getViewSize();
			// System.out.println("[Page.addView()] " + size);
			view.setSize(size);
		}

		// 뷰 초기화 후 리스트에 추가
		view.init(this.mStatusManager, this);
		this.mViewList.add(view);

		if (view instanceof PopupView)
			this.mLayeredPane.add(view, JLayeredPane.POPUP_LAYER);
		else
			this.mLayeredPane.add(view, JLayeredPane.DEFAULT_LAYER);
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
	public void setSelectedViewByNum(int num) throws Exception {
		// 현재 뷰를 선택한 경우
		if (num == this.mSelectedViewNum) {
			String msg = String.format("[Page.setSelectedViewByNum()] Same view number(%d)! \n",
					num);
			throw new Exception(msg);
		}

		// 뷰 번호 유효성 확인
		if (!this.isValidViewNum(num)) {
			String msg = String.format(
					"[Page.selectViewByNum()] " +
							"Invalid view number(%d)!",
					num);
			throw new Exception(msg);
		}

		// 현재 뷰 숨김
		View currView = this.getSelectedView();
		if (currView != null)
			currView.performHide();

		// 선택한 뷰 표시
		this.mSelectedViewNum = num;
		View newView = this.getViewByNum(num);
		newView.performShow();
	}

	// 선택된 뷰 번호 얻기
	public int getSelectedViewNum() {
		return this.mSelectedViewNum;
	}

	// 선택된 뷰 객체 얻기
	public View getSelectedView() {
		return this.getViewByNum(this.getSelectedViewNum());
	}

	// 뷰 사이즈 얻기
	public Dimension getViewSize() {
		return this.getContentPane().getSize();
	}

	// 팝업 뷰 표시
	public void showPopupViewByNum(int num) throws Exception {
		var popupView = this.getViewByNum(num);
		// 표시 하려는 뷰가 팝업 뷰가 아닌 경우
		if (!(popupView instanceof PopupView)) {
			String msg = String.format("[Page.showPopupViewByNum()]" +
					" View number(%s) is not popup view!", num);
			throw new Exception(msg);
		}

		this.mDimmedView.setVisible(true);

		// 팝업 뷰를 뷰 가운데로 설정
		Dimension viewSize = this.getViewSize();
		int x = (int) (viewSize.getWidth() - popupView.getWidth()) / 2;
		int y = (int) (viewSize.getHeight() - popupView.getHeight()) / 2;
		popupView.setLocation(x, y);

		popupView.performShow();
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
	protected void onShow(boolean firstTime) {
	}

	// 페이지가 숨겨질 때 -> Status Manager에서 호출됨
	protected void onHide() {
	}

	// 리소스 설정
	protected void onSetResources() {
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