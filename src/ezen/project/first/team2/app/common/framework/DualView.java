////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231115WED_165700] Created
// [SGLEE:20231116THU_114100] Left & Right View
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.framework;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSplitPane;

public class DualView extends View {
	// ---------------------------------------------------------------------------

	// [SGLEE:20231116THU_120600]
	// onInit()에서 생성하도록 한다.
	// super.onInit() 미호출 방지 목적.
	// super.onInit() 미호출 시, mSplitPane이 null이므로 사용 중 에러 발생한다.
	private JSplitPane mSplitPane = null;

	private int mLeftViewWidth = 0;

	private List<View> mViewList = new ArrayList<>();
	private int mSelectedLeftViewNum = -1;
	private int mSelectedRightViewNum = -1;

	// ---------------------------------------------------------------------------

	// 생성자
	public DualView(int num, int leftViewWidth) {
		super(num);

		this.mLeftViewWidth = leftViewWidth;
	}

	// ---------------------------------------------------------------------------

	// 뷰 초기화 -> View에서 뷰를 추가할 때 호출됨
	@Override
	public void init(StatusManager stsMngr, Page page) {
		super.init(stsMngr, page);

		// [SGLEE:20231116THU_124000] 호출 순서는 크게 상관 없을 것 같다.
		this.onAddViews();
	}

	// ---------------------------------------------------------------------------

	// 뷰 추가 - 추가하면서 init 메소드 호출
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
		view.init(super.getStatusManager(), super.getPage());
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

	// 뷰 번호로 왼쪽 뷰 선택
	public void setSelectedLeftViewByNum(int num) throws Exception {
		if (num == this.mSelectedLeftViewNum) {
			System.out.printf(
					"[DualView.setSelectedLeftViewByNum()]" +
							" Same view number(%d)! \n",
					num);
			return;
		}

		// 유효한 뷰 번호인지 확인한다
		if (!this.isValidViewNum(num)) {
			String msg = String.format(
					"[DualView.setSelectedLeftViewByNum()] " +
							"Invalid view number(%d)!",
					num);
			throw new Exception(msg);
		}

		// 기존 뷰 정리
		View selView = this.getSelectedLeftView();
		if (selView != null) {
			selView.onHide();
		}

		// 선택한 뷰 표시
		this.mSelectedLeftViewNum = num;
		View newView = this.getViewByNum(num);
		newView.performShow();
		this.mSplitPane.setLeftComponent(newView);
	}

	// 뷰 번호로 오른쪽 뷰 선택
	public void setSelectedRightViewByNum(int num) throws Exception {
		if (num == this.mSelectedRightViewNum) {
			System.out.printf(
					"[DualView.setSelectedRightViewByNum()]" +
							" Same view number(%d)! \n",
					num);
			return;
		}

		// 유효한 뷰 번호인지 확인한다
		if (!this.isValidViewNum(num)) {
			String msg = String.format(
					"[DualView.setSelectedRightViewByNum()] " +
							"Invalid view number(%d)!",
					num);
			throw new Exception(msg);
		}

		// 기존 뷰 정리
		View selView = this.getSelectedRightView();
		if (selView != null) {
			selView.onHide();
		}

		// 선택한 뷰 표시
		this.mSelectedRightViewNum = num;
		View newView = this.getViewByNum(num);
		// [SGLEE:20231116THU_125700] 크기 설정을 해도 적용이 안 된다?
		newView.setPreferredSize(new Dimension(this.mLeftViewWidth, 0));
		newView.performShow();
		this.mSplitPane.setRightComponent(newView);
	}

	// 선택된 왼쪽 뷰 번호 얻기
	public int getSelectedLeftViewNum() {
		return this.mSelectedLeftViewNum;
	}

	// 선택된 오른쪽 뷰 번호 얻기
	public int getSelectedRightViewNum() {
		return this.mSelectedRightViewNum;
	}

	// 선택된 왼쪽 뷰 객체 얻기
	public View getSelectedLeftView() {
		return this.getViewByNum(this.getSelectedLeftViewNum());
	}

	// 선택된 오른쪽 뷰 객체 얻기
	public View getSelectedRightView() {
		return this.getViewByNum(this.getSelectedRightViewNum());
	}

	// ---------------------------------------------------------------------------

	@Override
	protected void onInit() {
		super.onInit();

		this.mSplitPane = new JSplitPane();
		this.mSplitPane.setDividerSize(0);
		this.add(this.mSplitPane);
	}

	@Override
	protected void onSetLayout() {
	}

	// @Override
	// protected void onAddCtrls() {
	// super.onAddCtrls();
	// }

	// @Override
	// protected void onAddEventListeners() {
	// super.onAddEventListeners();
	// }

	protected void onAddViews() {
	}

	@Override
	protected void onShow(boolean firstTime) {
		// System.out.println("[DualView.onShow()]");
	}

	@Override
	protected void onHide() {
		// System.out.println("[DualView.onHide()]");
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
