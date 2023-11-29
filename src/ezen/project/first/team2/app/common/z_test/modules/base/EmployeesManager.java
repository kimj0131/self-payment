package ezen.project.first.team2.app.common.z_test.modules.base;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.base.ListManagerDb;

public class EmployeesManager extends ListManagerDb<EmployeeItem> {
	// -------------------------------------------------------------------------

	private static final String TABLE_NAME = "employees";

	// -------------------------------------------------------------------------

	private static EmployeesManager mInstance;

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

	// -------------------------------------------------------------------------

	@Override
	protected EmployeeItem onResultSetToItem(ResultSet rs) {
		try {

			System.out.println(rs.getMetaData().getColumnCount());

			int employee_id = rs.findColumn("employee_id");
			int first_name = rs.findColumn("first_name");
			int last_name = rs.findColumn("last_name");
			int email = rs.findColumn("email");
			int phone_number = rs.findColumn("phone_number");
			int hire_date = rs.findColumn("hire_date");
			int job_id = rs.findColumn("job_id");
			int salary = rs.findColumn("salary");
			int commission_pct = rs.findColumn("commission_pct");
			int manager_id = rs.findColumn("manager_id");
			int department_id = rs.findColumn("department_id");

			return new EmployeeItem(
					this.getInt(rs, "employee_id", -1),
					this.getString(rs, "first_name", ""),
					this.getString(rs, "last_name", ""),
					this.getString(rs, "email", ""),
					this.getString(rs, "phone_number", ""),
					this.getDate(rs, "hire_date", Date.valueOf(LocalDate.now()),
					this.getString(rs, "job_id", ""),
					commission_pct > 0 ? rs.getDouble(commission_pct) : 0,
					manager_id > 0 ? rs.getInt(manager_id) : 0,
					department_id > 0 ? rs.getInt(department_id) : 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new EmployeeItem();
	}
}
