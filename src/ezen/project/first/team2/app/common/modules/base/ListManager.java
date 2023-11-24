////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_100900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.util.List;

public class ListManager<T extends ListItem> {
	// -------------------------------------------------------------------------

	public static interface Iterator<T> {
		// false를 리턴하면 열거를 중단한다
		public boolean onGetItem(T item, int idx);
	}

	// -------------------------------------------------------------------------

	private ListActionListener<T> mActionListener = null;
	private boolean mInited = false;

	// -------------------------------------------------------------------------

	// 생성자
	public ListManager() {
	}

	// 상속받은 클래스에서 구현해야 한다!
	// public static MemberManager getInstance() {
	// if (MemberManager.mInstance != null) {
	// MemberManager.mInstance = new MemberManager();
	// }
	//
	// return MemberManager.mInstance;
	// }

	// -------------------------------------------------------------------------

	// 액션 리스너 설정
	public void setActionListener(ListActionListener<T> listener) {
		this.mActionListener = listener;
	}

	// 초기화
	public void init() throws Exception {
		if (this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
					" Already initialized!");
			throw new Exception(msg);
		}

		this.mInited = true;
		this.onInit();

		// 액션 리스너 호출
		if (this.mActionListener != null) {
			this.mActionListener.onInitialized(this);
		}
	}

	// 종료
	public void deinit() throws Exception {
		if (!this.isInited()) {
			String msg = String.format("[ListManager.deinit()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// 액션 리스너 호출
		if (this.mActionListener != null) {
			this.mActionListener.onDeinitializing(this);
		}

		this.onDeinit();
		this.mInited = false;
	}

	// 초기화 여부 확인
	public boolean isInited() {
		return this.mInited;
	}

	// 다음 ID 얻기
	public int getNextID() throws Exception {
		if (!this.isInited()) {
			String msg = String.format("[ListManager.getNextID()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onGetNextID();
	}

	// 아이템 추가. id값이 -1이면 내부적으로 this.getNextID()를 호출하여 id를 할당한다.
	public void add(T item) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.add()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// ID값 자동 부여
		if (item.getId() == -1) {
			// [SGLEE:20231121TUE_150000] protected int mId = -1;
			item.mId = this.getNextID();
		}

		// 아이템 ID 중복 확인
		final int id = item.getId();
		if (this.isDuplicatedId(id)) {
			String msg = String.format("[ListManager.add()]" +
					" You have duplicated id(%d)!",
					id);
			throw new Exception(msg);
		}

		// 아이템 추가
		String errMsg = this.onAdd(item);
		if (!errMsg.isEmpty())
			throw new Exception(errMsg);

		// 액션 리스너 호출
		if (this.mActionListener != null) {
			this.mActionListener.onAdded(this, item);
		}
	}

	// 아이템 수정
	public void updateById(int id, T item) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.updateById()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// 아이템 ID 존재(중복) 확인
		if (!this.isDuplicatedId(id)) {
			String msg = String.format("[ListManager.updateById()]" +
					" Invalid id(%d)!",
					id);
			throw new Exception(msg);
		}

		// [SGLEE:20231122WED_143800] 일단 경고 오프
		@SuppressWarnings("unchecked")
		T oldItem = (T) this.findById(id).clone();

		// 아이템 수정
		item.mId = id;
		String errMsg = this.onUpdateById(id, oldItem, item);
		if (!errMsg.isEmpty())
			throw new Exception(errMsg);

		// 액션 리스너 호출
		if (this.mActionListener != null) {
			this.mActionListener.onUpdated(this, oldItem, item);
		}
	}

	// 아이템 삭제
	public void deleteById(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.deleteById()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// 아이템 ID 존재(중복) 확인
		if (!this.isDuplicatedId(id)) {
			String msg = String.format("[ListManager.deleteById()]" +
					" Invalid id(%d)!",
					id);
			throw new Exception(msg);
		}

		// [SGLEE:20231122WED_143800] 일단 경고 오프
		@SuppressWarnings("unchecked")
		T oldItem = (T) this.findById(id).clone();

		// 아이템 삭제
		String errMsg = this.onDeleteById(id);
		if (!errMsg.isEmpty())
			throw new Exception(errMsg);

		// 액션 리스너 호출
		if (this.mActionListener != null) {
			this.mActionListener.onDeleted(this, oldItem);
		}
	}

	// 아이템 ID 중복(존재) 확인
	public boolean isDuplicatedId(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.isDuplicatedId()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onIsDuplicatedId(id);
	}

	// 아이템 수 얻기
	public int getCount() throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.getCount()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onGetCount();
	}

	// 아이템 열거. iterator에서 false를 반환하면 열거 중단.
	public void iterate(Iterator<T> iterator) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		this.onIterate(iterator);
	}

	// 아이템 찾기. iterator에서 원하는 아이템을 찾았으면 true 반환.
	public T find(Iterator<T> iterator) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.find()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onFind(iterator);
	}

	// 여러 아이템 찾기. iterator에서 원하는 아이템을 찾았으면 true 반환.
	public List<T> findItems(Iterator<T> iterator) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.find()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onFindItems(iterator);
	}

	// ID로 아이템 찾기
	public T findById(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.findById()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onFindById(id);
	}

	// -------------------------------------------------------------------------

	// 초기화
	protected void onInit() {
	}

	// 종료
	protected void onDeinit() {
	}

	// 다음 ID 얻기 -> 가장 큰 값을 얻은 뒤 +1을 더한다.
	protected int onGetNextID() {
		return -1;
	}

	// 아이템 추가 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onAdd(T item) throws Exception {
		return "/ERROR/";
	}

	// 아이템 수정 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onUpdateById(int id, T oldItem, T newItem) throws Exception {
		return "/ERROR/";
	}

	// 아이템 삭제 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onDeleteById(int id) throws Exception {
		return "/ERROR/";
	}

	// 아이템 ID 중복(존재) 확인
	protected boolean onIsDuplicatedId(int id) {
		return false;
	}

	// 아이템 수 얻기
	protected int onGetCount() {
		return -1;
	}

	// 아이템 열거
	protected void onIterate(Iterator<T> iterator) {
	}

	// 아이템 찾기
	protected T onFind(Iterator<T> iterator) {
		return null;
	}

	// 여러 아이템 찾기
	protected List<T> onFindItems(Iterator<T> iterator) {
		return null;
	}

	// ID로 아이템 찾기
	protected T onFindById(int id) {
		return null;
	}
}
