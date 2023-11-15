////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231114TUE_101700] Created
// [SGLEE:20231114TUE_102000] Page, View, Pane 전환 상태 관리
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common;

import java.util.ArrayList;
import java.util.List;

public class StatusManager {
	//
	private static boolean DEBUG_MODE = true; 
	private static boolean TEST_MODE = true;
	//
	
	private StatusManagerListener mListener = null;
	
	private List<Page> mPageList = new ArrayList<>();
	private List<View> mViewList = new ArrayList<>();
	private List<Pane> mPaneList = new ArrayList<>();
	
	private int mCurrPageNum = -1;
	private int mCurrViewNum = -1;
	private int mCurrPaneNum = -1;
	
	// 생성자
	public StatusManager() {
		//
	}
	
	//
	
	// 상태 관리자 실행 - 상속 받은 클래스에서 호출
	public void run() {
		this.onInit();
		this.onRun();
	}
	
	// 이벤트 리스너 추가	
	public void addEventListener(StatusManagerListener listener) {
		this.mListener = listener;
	}
	
	// 페이지, 뷰, 페인 추가 - 아이템을 추가하면서 init() 메소드 호출
		
	public void addPage(Page page) throws Exception {
		// 페이지 번호 유효성 확인
		int pageNum = page.getNumber();
		if (pageNum == -1 || this.isValidPageNum(pageNum)) {
			String msg = String.format(
					"[StatusManager.addPage()] " +
					" Invalid or duplicated page number! => %d", pageNum);
			throw new Exception(msg);
		}		
		
		// 페이지 초기화 후 리스트에 추가
		page.init(this);		
		this.mPageList.add(page);
		
		if (this.mListener != null)
			this.mListener.onAddPage(page.getNumber(), page);
	}

	public void addView(View view) throws Exception {
		// 뷰 번호 유효성 확인
		int viewNum = view.getNumber();
		if (viewNum == -1 || this.isValidViewNum(viewNum)) {
			String msg = String.format(
					"Invalid or duplicated view number! => %d", viewNum);
			throw new Exception(msg);
		}		
		
		// 뷰 초기화 후 리스트에 추가
		view.init(this);		
		this.mViewList.add(view);
		
		if (this.mListener != null)
			this.mListener.onAddView(view.getNumber(), view);
	}

	public void addPane(Pane pane) throws Exception {
		// 페인 번호 유효성 확인
		int paneNum = pane.getNumber();
		if (paneNum == -1 || this.isValidPaneNum(paneNum)) {
			String msg = String.format(
					"Invalid or duplicated view number! => %d", paneNum);
			throw new Exception(msg);
		}		
		
		// 페인 초기화 후 리스트에 추가
		pane.init(this);		
		this.mPaneList.add(pane);
		
		if (this.mListener != null)
			this.mListener.onAddPane(pane.getNumber(), pane);
	}

	// 페이지, 뷰, 페인 넘버로 객체 얻기
	
	public Page getPageByNum(int num) {
		for (Page page : this.mPageList) {
			if (page.getNumber() == num)
				return page;
		}
		
		return null;
	}

	public View getViewByNum(int num) {
		for (View view : this.mViewList) {
			if (view.getNumber() == num)
				return view;
		}

		return null;
	}

	public Pane getPaneByNum(int num) {
		for (Pane pane : this.mPaneList) {
			if (pane.getNumber() == num)
				return pane;
		}
		
		return null;		
	}
	
	// 페이지, 뷰, 페인 넘버로 선택
	
	public void selectPageByNum(int num) {
		if (num == this.mCurrPageNum) {
			System.out.printf(
					"[StatusManager.selectPageByNum()] Same page number(%d)! \n",
					num);
			return;
		}
		
		// 현재 페이지 숨김
		Page currPage = this.getCurrPage();
		if (currPage != null)
			currPage.setVisible(false);
		
		// 선택한 페이지 표시
		try {
			this.mCurrPageNum = num;
			Page newPage = this.getPageByNum(num);
			newPage.setVisible(true);
			
			if (this.mListener != null) {
				int oldNum = currPage == null ? -1 : currPage.getNumber();
				this.mListener.onSelectPage(num, oldNum, newPage, currPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectViewByNum(int num) {
		if (num == this.mCurrViewNum) {
			System.out.printf(
					"[StatusManager.selectViewByNum] Same view number(%d)! \n",
					num);
			return;
		}
		
		// 현재 뷰 숨김
		View currView = this.getCurrView();
		if (currView != null)
			currView.setVisible(false);
		
		// 선택한 뷰 표시
		try {
			this.mCurrViewNum = num;
			View newView = this.getViewByNum(num);
			newView.setVisible(true);
			
			if (this.mListener != null) {
				int oldNum = currView == null ? -1 : currView.getNumber();
				this.mListener.onSelectView(num, oldNum, newView, currView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectPaneByNum(int num) {
		if (num == this.mCurrPaneNum) {
			System.out.printf(
					"[StatusManager.selectPaneByNum] Same pane number(%d)! \n",
					num);
			return;
		}
		
		// 현재 페인 숨김
		Pane currPane = this.getCurrPane();
		if (currPane != null)
			currPane.setVisible(false);
		
		// 선택한 폐인 표시
		try {
			this.mCurrPaneNum = num;
			Pane newPane = this.getPaneByNum(num);
			newPane.setVisible(true);
			
			if (this.mListener != null) {
				int oldNum = currPane == null ? -1 : currPane.getNumber();
				this.mListener.onSelectPane(num, oldNum, newPane, currPane);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 현재 페이지, 뷰, 페인 객체 얻기
	
	public Page getCurrPage() {
		return this.getPageByNum(this.mCurrPageNum);
	}
	
	public View getCurrView() {
		return this.getViewByNum(this.mCurrPageNum);
	}
	
	public Pane getCurrPane() {
		return this.getPaneByNum(this.mCurrPaneNum);
	}
	
	//
	
	// 초기화 작업 - 페이지 추가 등
	protected void onInit() {
		//
	}
	
	// 실행 작업 - 페이지 선택 등
	protected void onRun() {
		//
	}
	
	// 페이지, 뷰, 페인 넘버가 유효한지 확인 
	
	private boolean isValidPageNum(int num) {
		for (Page page : this.mPageList) {
			//System.out.printf("[StatusManager.isValidPageIdx()] " +
			//		"idx:%d, page.getIndex():%d \n",
			//		num, page.getNumber());
			
			if (page.getNumber() == num)
				return true;
		}
		
		return false;
	}
	
	private boolean isValidViewNum(int num) {
		for (View view : this.mViewList) {
			if (view.getNumber() == num)
				return false;
		}
		
		return true;
	}
	
	private boolean isValidPaneNum(int num) {
		for (Pane pane: this.mPaneList) {
			if (pane.getNumber() == num)
				return false;
		}
		
		return true;
	}
}



















