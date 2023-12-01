package ezen.project.first.team2.app.common.z_test.modules.base;

import java.sql.ResultSet;

import ezen.project.first.team2.app.common.modules.base.ListManagerDb;

public class EmployeesManager extends ListManagerDb<EmployeeItem> {
	// -------------------------------------------------------------------------

	// [SGLEE:20231130THU_112200] 테이블 이름은 반드시 대문자?
	private static final String TABLE_NAME = "EMPLOYEES2";

	// -------------------------------------------------------------------------

	private static EmployeesManager mInstance;
	private static EmployeesManager mTmpInstance = new EmployeesManager();

	// -------------------------------------------------------------------------

	// 생성자
	private EmployeesManager() {
		super(TABLE_NAME);
	}

	public static EmployeesManager getInstance() {
		if (mInstance == null) {
			mInstance = new EmployeesManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	@Override
	protected ListManagerDb<EmployeeItem> onGetTmpInstance() {
		return mTmpInstance;
	}

	@Override
	protected String onMakeCreateTableQuery(String tableName) {
		return String.format("create table %s(" +
				"employee_id number(6) primary key, " +
				"first_name varchar2(20), " +
				"last_name varchar2(25) not null, " +
				"email varchar2(25) not null, " +
				"phone_number varchar2(20), " +
				"hire_date date not null, " +
				"job_id varchar2(10) not null, " +
				"salary number(8,2), " +
				"commission_pct number(2,2), " +
				"manager_id number(6), " +
				"department_id number(4) " +
				")", tableName);
	}

	// -------------------------------------------------------------------------

	@Override
	protected EmployeeItem onResultSetToItem(ResultSet rs) {
		return new EmployeeItem(
				this.getInt(rs, "employee_id"),
				this.getString(rs, "first_name"),
				this.getString(rs, "last_name"),
				this.getString(rs, "email"),
				this.getString(rs, "phone_number"),
				this.getDate(rs, "hire_date").toLocalDate(),
				this.getString(rs, "job_id"),
				this.getInt(rs, "salary"),
				this.getDouble(rs, "commission_pct"),
				this.getInt(rs, "manager_id"),
				this.getInt(rs, "department_id"));

	}

	@Override
	protected String[] onMakeFieldsetAndValues(EmployeeItem item) {
		String fieldset = "employee_id, first_name, last_name, email, phone_number," +
				"hire_date, job_id, salary, commission_pct, manager_id, department_id";
		String values = String.format(
				// "employees_seq.nextval, '%s', '%s', '%s', '%s'" +
				"%d, '%s', '%s', '%s', '%s'" +
						", '%s', '%s', %d, %f" + ", %d, %d",
				item.getId(), item.getFirstName(), item.getLastName(), item.getEmail(), item.getPhoneNumber(),
				item.getHireSqlDateStr(), item.getJobId(), item.getSalary(), item.getCommissionPct(),
				item.getManagerId(), item.getDepartmentId());

		return new String[] { fieldset, values };
	}

	@Override
	protected String onMakeSetAll(EmployeeItem item) throws Exception {
		String s = "";

		// s += String.format("employee_id = %d, ", item.getId());
		s += String.format("first_name = '%s', ", item.getFirstName());
		s += String.format("last_name = '%s', ", item.getLastName());
		s += String.format("email = '%s', ", item.getEmail());
		s += String.format("phone_number = '%s', ", item.getPhoneNumber());
		s += String.format("hire_date = '%s', ", item.getHireSqlDateStr());
		s += String.format("job_id = '%s', ", item.getJobId());
		s += String.format("salary = %d, ", item.getSalary());
		s += String.format("commission_pct = %f, ", item.getCommissionPct());
		s += String.format("manager_id = %d, ", item.getManagerId());
		s += String.format("department_id = %d", item.getDepartmentId());

		return s;
	}

	@Override
	protected String onMakeSet(EmployeeItem item, String[] fieldset) throws Exception {
		String s = "";

		for (var f : fieldset) {
			if (f.equals("employee_id"))
				s += String.format("employee_id = %d, ", item.getId());
			else if (f.equals("first_name"))
				s += String.format("first_name = '%s', ", item.getFirstName());
			else if (f.equals("last_name"))
				s += String.format("last_name = '%s', ", item.getLastName());
			else if (f.equals("email"))
				s += String.format("email = '%s', ", item.getEmail());
			else if (f.equals("phone_number"))
				s += String.format("phone_number = '%s', ", item.getPhoneNumber());
			else if (f.equals("hire_date"))
				s += String.format("hire_date = '%s', ", item.getHireSqlDateStr());
			else if (f.equals("job_id"))
				s += String.format("job_id = '%s', ", item.getJobId());
			else if (f.equals("salary"))
				s += String.format("salary = %d, ", item.getSalary());
			else if (f.equals("commission_pct"))
				s += String.format("commission_pct = %f, ", item.getCommissionPct());
			else if (f.equals("manager_id"))
				s += String.format("manager_id = %d, ", item.getManagerId());
			else if (f.equals("department_id"))
				s += String.format("department_id = %d, ", item.getDepartmentId());
			else {
				String msg = String.format("[EmplyeesManager.onMakeSet()]" +
						" Invalid field name('%s')!", f);
				throw new Exception(msg);
			}
		}

		// 마지막 comma와 space 제거
		s = s.substring(0, s.length() - 2);

		return s;
	}
}
