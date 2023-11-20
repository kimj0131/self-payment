////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_100900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules;

public class MemberManager {
	// -------------------------------------------------------------------------

	public static interface Iterator {
		// false를 리턴하면 열거를 중단한다
		public boolean onGetItem(MemberInfo item);
	}

	// -------------------------------------------------------------------------

	private boolean mInited = false;

	// -------------------------------------------------------------------------

	// 생성자
	public MemberManager() {
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
			String msg = String.format("[MemberManager.init()]" +
					" Already initialized!");
			throw new Exception(msg);
		}

		this.mInited = true;
		this.onInit();
	}

	// 종료
	public void deinit() throws Exception {
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
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

	// 회원 추가
	public void add(MemberInfo info) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// 회원 번호(ID) 중복 확인
		final int id = info.getId();
		if (this.isValidId(id)) {
			String msg = String.format("[MemberManager.add()]" +
					"Invalid or duplicated id(%d)!",
					id);
			throw new Exception(msg);
		}

		this.onAdd(info);
	}

	// 회원 수정
	public void updateById(int id, MemberInfo info) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// 회원 번호(ID) 존재 확인
		if (!this.isValidId(id)) {
			String msg = String.format("[MemberManager.updateById()]" +
					"Invalid id(%d)!",
					id);
			throw new Exception(msg);
		}

		this.onUpdateById(id, info);
	}

	// 회원 삭제
	public void deleteById(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		// 회원 번호(ID) 존재 확인
		if (!this.isValidId(id)) {
			String msg = String.format("[MemberManager.deleteById()]" +
					"Invalid id(%d)!",
					id);
			throw new Exception(msg);
		}

		this.onDeleteById(id);
	}

	// 회원 번호(ID) 확인
	public boolean isValidId(int id) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onIsValidId(id);
	}

	// 회원 수 얻기
	public int getCount() throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		return this.onGetCount();
	}

	// 회원 열거
	public void iterate(Iterator iterator) throws Exception {
		// 초기화 여부 확인
		if (!this.isInited()) {
			String msg = String.format("[MemberManager.init()]" +
					" You must initialize!");
			throw new Exception(msg);
		}

		this.onIterate(iterator);
	}

	// -------------------------------------------------------------------------

	// 초기화
	protected void onInit() {
	}

	protected void onDeinit() {
	}

	// 회원 추가
	protected void onAdd(MemberInfo info) {
	}

	// 회원 수정
	protected void onUpdateById(int id, MemberInfo info) {
	}

	// 회원 삭제
	protected void onDeleteById(int id) {
	}

	// 회원 번호(ID) 확인
	protected boolean onIsValidId(int id) {
		return false;
	}

	// 회원 수 얻기
	protected int onGetCount() {
		return -1;
	}

	// 회원 열거
	protected void onIterate(Iterator iterator) {
	}
}
