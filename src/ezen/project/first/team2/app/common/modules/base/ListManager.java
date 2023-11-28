////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_100900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.util.ArrayList;
import java.util.List;

public class ListManager<T extends ListItem> {
	// -------------------------------------------------------------------------

	public static interface Iterator<T> {
		// false를 리턴하면 열거를 중단한다
		public boolean onGetItem(T item, int idx);
	}

	// -------------------------------------------------------------------------

	private ListActionListener<T> mActionListener = null;

	private List<T> mList = new ArrayList<>();

	// -------------------------------------------------------------------------

	// 생성자
	public ListManager() {
	}

	// 필요한 경우 상속받은 클래스에서 구현한다
	// public static MemberManager getInstance() {
	// if (mInstance != null) {
	// mInstance = new MemberManager();
	// }
	//
	// return mInstance;
	// }

	// -------------------------------------------------------------------------

	// 액션 리스너 설정
	public void setActionListener(ListActionListener<T> listener) {
		this.mActionListener = listener;
	}

	// 다음 ID 얻기
	public int getNextID() {
		return this.onGetNextID();
	}

	// 아이템 추가. id값이 -1이면 내부적으로 this.getNextID()를 호출하여 id를 할당한다.
	public int add(T item) throws Exception {
		// ID값 자동 부여
		if (item.getId() == -1) {
			// [SGLEE:20231121TUE_150000] protected int mId = -1;
			item.mId = this.getNextID();
		}

		// 아이템 ID 중복 확인
		int id = item.getId();
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

		return item.getId();
	}

	// ID로 아이템 수정
	public int updateById(int id, T item) throws Exception {
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

		return item.getId();
	}

	// ID로 아이템 삭제
	public int deleteById(int id) throws Exception {
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

		return id;
	}

	// 지정된 조건으로 아이템들 삭제 => iterator에서 true를 리턴하면 삭제한다
	public int deleteItems(Iterator<T> iterator) {
		List<Integer> ids = this.onGetItemIds(iterator);

		// 액션 리스너 호출 -> 아이템들이 삭제될 때
		if (this.mActionListener != null) {
			this.mActionListener.onDeleteItems(this, ids);
		}

		// ID 리스트로 아이템들 삭제
		this.onDeleteItems(ids);

		// 액션 리스너 호출 -> 아이템들이 삭제되었을 때
		if (this.mActionListener != null) {
			this.mActionListener.onDeletedItems(this, ids);
		}

		return ids.size();
	}

	// 아이템 ID 중복(존재) 확인
	public boolean isDuplicatedId(int id) {
		return this.onIsDuplicatedId(id);
	}

	// 아이템 수 얻기
	public int getCount() {
		return this.onGetCount();
	}

	// 아이템 열거. iterator에서 false를 반환하면 열거 중단.
	public void iterate(Iterator<T> iterator) {
		this.onIterate(iterator);
	}

	// 아이템 찾기. iterator에서 원하는 아이템을 찾았으면 true 반환.
	public T find(Iterator<T> iterator) {
		return this.onFind(iterator);
	}

	// 여러 아이템 찾기. iterator에서 원하는 아이템을 찾았으면 true 반환.
	public List<T> findItems(Iterator<T> iterator) {
		return this.onFindItems(iterator);
	}

	// ID로 아이템 찾기
	public T findById(int id) {
		return this.onFindById(id);
	}

	// -------------------------------------------------------------------------

	public void loadFromDb(Iterator<T> iterator) {
		//
	}

	public void saveToDb(Iterator<T> iterator) {
		//
	}

	public void saveToDbById(int id) {
		//
	}

	// -------------------------------------------------------------------------

	// 다음 ID 얻기 -> 가장 큰 값을 얻은 뒤 +1을 더한다.
	protected int onGetNextID() {
		int maxId = -1;
		for (var i : this.mList) {
			if (i.getId() > maxId)
				maxId = i.getId();
		}

		return maxId + 1;
	}

	// 아이템 추가 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onAdd(T item) {
		this.mList.add(item);

		return "";
	}

	// ID로 아이템 수정 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onUpdateById(int id, T oldItem, T newItem) {
		for (var item : this.mList) {
			if (item.getId() == id) {
				item.setValuesFrom(newItem);

				return "";
			}
		}

		return String.format("[ListManager.onUpdateById()]" +
				" Invalid id(%d)!",
				id);
	}

	// ID로 아이템 삭제 -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	protected String onDeleteById(int id) {
		boolean res = this.mList.removeIf(item -> item.getId() == id);

		return res ? ""
				: String.format("[ListManager.onDeleteById()]" +
						" Invalid id(%d)!", id);
	}

	// 아이템 ID들을 얻는다
	protected List<Integer> onGetItemIds(Iterator<T> iterator) {
		int idx = 0;
		List<Integer> ids = new ArrayList<>();
		for (var i : this.mList) {
			if (iterator.onGetItem(i, idx))
				ids.add(idx++);
		}

		return ids;
	}

	// ID 리스트로 아이템들 삭제
	protected void onDeleteItems(List<Integer> ids) {
		// [SGLEE:20231128TUE_112800] 이터레이션을 하면서 아이템을 삭제할 수는 없다!
		// int idx = 0;
		// for (var i : this.mList) {
		// if (iterator.onGetItem(i, idx++))
		// this.deleteById(i.getId());
		// }

		// 리스트에 있는 ID를 기준으로 삭제한다
		for (var id : ids) {
			try {
				this.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 아이템 ID 중복(존재) 확인
	protected boolean onIsDuplicatedId(int id) {
		for (var item : this.mList) {
			if (item.getId() == id)
				return true;
		}

		return false;
	}

	// 아이템 수 얻기
	protected int onGetCount() {
		return this.mList.size();
	}

	// 아이템 열거
	protected void onIterate(Iterator<T> iterator) {
		int i = 0;
		for (var item : this.mList) {
			if (!iterator.onGetItem(item, i++))
				break;
		}
	}

	// 아이템 찾기
	protected T onFind(Iterator<T> iterator) {
		int i = 0;
		for (var item : this.mList) {
			if (iterator.onGetItem(item, i++))
				return item;
		}

		return null;
	}

	// 여러 아이템 찾기
	protected List<T> onFindItems(Iterator<T> iterator) {
		List<T> items = new ArrayList<>();

		int i = 0;
		for (var item : this.mList) {
			if (iterator.onGetItem(item, i++))
				items.add(item);
		}

		return items;
	}

	// ID로 아이템 찾기 -> 없는 경우 null 리턴
	protected T onFindById(int id) {
		for (var item : this.mList) {
			if (item.getId() == id)
				return item;
		}

		return null;
	}
}
