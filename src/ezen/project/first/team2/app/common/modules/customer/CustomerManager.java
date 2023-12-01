////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_105900] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.customer;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManagerDb;

public class CustomerManager extends ListManagerDb<CustomerItem> {

	private static final String TABLE_NAME = "CUSTOMERS";

	// -------------------------------------------------------------------------

	private static CustomerManager mInstance = null;
	private CustomerManager mTmpInstance = new CustomerManager();

	// -------------------------------------------------------------------------

	// 생성자
	private CustomerManager() {
		super(TABLE_NAME);
	}

	// 인스턴스 얻기
	public static CustomerManager getInstance() {
		if (mInstance == null) {
			mInstance = new CustomerManager();

			try {
				// 비회원 객체 추가
				mInstance.add(CustomerItem.GUEST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// 포인트 업데이트
	public void updatePoint(int custId, int point) throws Exception {
		var ci = this.findById(custId);

		// 비회원인 경우 예외 발생
		if (ci == null) {
			String msg = String.format("[CustomerManager.updatePoint()]"
					+
					" Invalid custId(%d)!",
					custId);
			throw new Exception(msg);
		}

		ci.setPoint(point);
		this.updateById(ci.getId(), null);
	}

	// -------------------------------------------------------------------------

	public CustomerItem findByName(String name) {
		return this.find((ci, idx) -> ci.getName().equals(name));
	}

	public CustomerItem findByBirthday(LocalDate date) {
		return this.find((ci, idx) -> ci.getBirthday().equals(date));
	}

	public CustomerItem findByPhoneNumber(String phoneNumber) {
		return this.find((ci, idx) -> ci.getPhoneNumber().equals(phoneNumber));
	}

	//

	public List<CustomerItem> findItemsByName(String name) {
		return this.findItems((ci, idx) -> ci.getName().contains(name));
	}

	public List<CustomerItem> findItemsByBirthday(LocalDate date) {
		return this.findItems((ci, idx) -> ci.getBirthday().equals(date));
	}

	public List<CustomerItem> findItemsByPhoneNumber(String phoneNumber) {
		return this.findItems((ci, idx) -> ci.getPhoneNumber().contains(phoneNumber));
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(CustomerItem item) {
		if (this.findByPhoneNumber(item.getPhoneNumber()) != null) {
			String msg = String.format("[CustomerManager.onAdd()]" +
					" You have same phone number(%s)!", item.getPhoneNumber());

			return msg;
		}

		return super.onAdd(item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id,
			CustomerItem oldItem, CustomerItem newItem) {
		return super.onUpdateById(id, oldItem, newItem);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) {
		return super.onDeleteById(id);
	}

	@Override
	protected ListManagerDb<CustomerItem> onGetTmpInstance() {
		return this.mTmpInstance;
	}

	@Override
	protected String onMakeCreateTableQuery(String tableName) {
		return String.format("create table %s(" +
				"cust_id number(6) primary key, " +
				"join_date date not null, " +
				"name varchar2(20), " +
				"birthday date not null, " +
				"phone_num varchar2(15) not null, " +
				"point number(10), " +
				"remark varchar2(50)" +
				")", tableName);
	}

	@Override
	protected CustomerItem onResultSetToItem(ResultSet rs) {

		return new CustomerItem(
				this.getInt(rs, "cust_id"),
				this.getDate(rs, "join_date").toLocalDate(),
				this.getString(rs, "name"),
				this.getDate(rs, "birthday").toLocalDate(),
				this.getString(rs, "phone_num"),
				this.getInt(rs, "point"),
				this.getString(rs, "remark"));
	}

	@Override
	protected String[] onMakeFieldsetAndValues(CustomerItem item) {
		String fieldset = "cust_id, join_date, name, birthday, phone_num, point, remark";
		String values = String.format("%d, '%s', '%s', '%s', '%s', %d, '%s'",
				item.getId(), item.getSqlJoinDateStr(), item.getName(),
				item.getSqlBirthdayStr(), item.getPhoneNumber(),
				item.getPoint(), item.getRemark());

		return new String[] { fieldset, values };
	}

	@Override
	protected String onMakeSet(CustomerItem item, String[] fieldset) throws Exception {
		String s = "";

		for (var f : fieldset) {
			if (f.equals("cust_id")) {
				s += String.format("cust_id = %d, ", item.getId());
			} else if (f.equals("join_date")) {
				s += String.format("join_date = '%s', ", item.getSqlJoinDateStr());
			} else if (f.equals("name")) {
				s += String.format("name = '%s', ", item.getName());
			} else if (f.equals("birthday")) {
				s += String.format("birthday = '%s', ", item.getSqlBirthdayStr());
			} else if (f.equals("phone_num")) {
				s += String.format("phone_num = '%s', ", item.getPhoneNumber());
			} else if (f.equals("point")) {
				s += String.format("point = %d, ", item.getPoint());
			} else if (f.equals("remark")) {
				s += String.format("remark = '%s', ", item.getRemark());
			} else {
				String msg = String.format("[CustomerManager.onMakeSet()]" +
						" Invalid field name('%s')!", f);
				throw new Exception(msg);
			}
		}

		s = s.substring(0, s.length() - 2);

		return s;
	}
}
