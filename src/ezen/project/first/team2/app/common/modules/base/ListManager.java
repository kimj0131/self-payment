////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_100900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

public class ListManager<T extends ListItem> {
	// -------------------------------------------------------------------------

	public static interface Iterator<T> {
		// false를 리턴하면 열거를 중단한다
		public boolean onGetItem(T item, int idx);
	}

	// -------------------------------------------------------------------------

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

	// 초기화
	public void init() throws Exception {
		if (this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
					" Already initialized!");
			throw new Exception(msg);
		}

		this.mInited = true;
		this.onInit();
	}

	// 종료
	public void deinit() throws Exception {
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
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
			String msg = String.format("[ListManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onGetNextID();
	}

	// 아이템 추가. id값이 -1이면 내부적으로 this.getNextID()를 호출하여 id를 할당한다.
	public void add(T info) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// ID값 자동 부여
		if (info.getId() == -1) {
			// [SGLEE:20231121TUE_150000] protected int mId = -1;
			info.mId = this.getNextID();
		}

		// 아이템 ID 중복 확인
		final int id = info.getId();
		if (this.isDuplicatedId(id)) {
			String msg = String.format("[ListManager.add()]" +
					" You have duplicated id(%d)!",
					id);
			throw new Exception(msg);
		}

		String errMsg = this.onAdd(info);
		if (!errMsg.isEmpty())
			throw new Exception(errMsg);
	}

	// 아이템 수정
	public void updateById(int id, T info) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
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

		String errMsg = this.onUpdateById(id, info);
		if (!errMsg.isEmpty())
			throw new Exception(errMsg);
	}

	// 아이템 삭제
	public void deleteById(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
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

		String errMsg = this.onDeleteById(id);
		if (!errMsg.isEmpty())
			throw new Exception(errMsg);
	}

	// 아이템 ID 중복(존재) 확인
	public boolean isDuplicatedId(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onIsDuplicatedId(id);
	}

	// 아이템 수 얻기
	public int getCount() throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[ListManager.init()]" +
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
			String msg = String.format("[ListManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onFind(iterator);
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
	protected String onAdd(T info) {
		return "/ERROR/";
	}

	// 아이템 수정 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onUpdateById(int id, T info) {
		return "/ERROR/";
	}

	// 아이템 삭제 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onDeleteById(int id) {
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
}
