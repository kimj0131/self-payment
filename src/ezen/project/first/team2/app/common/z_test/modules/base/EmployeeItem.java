package ezen.project.first.team2.app.common.z_test.modules.base;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.base.ListItem;

public class EmployeeItem extends ListItem {
	// -------------------------------------------------------------------------

	private String mFirstName;
	private String mLastName;
	private String mEmail;
	private String mPhoneNumber;
	private LocalDate mHireDate;
	private String mJobId;
	private int mSalary;
	private double mCommissionPct;
	private int mManagerId;
	private int mDepartmentId;

	// -------------------------------------------------------------------------

	public EmployeeItem() {
	}

	public EmployeeItem(int id, String firstName, String lastName, String email,
			String phoneNumber, LocalDate hireDate, String jobId, int salary,
			double comissionPct, int managerId, int departmentId) {
		super();

		this.setValues(id, firstName, lastName, email, phoneNumber, hireDate,
				jobId, salary, comissionPct, managerId, departmentId);
	}

	// -------------------------------------------------------------------------

	public void setValues(int id, String firstName, String lastName, String email,
			String phoneNumber, LocalDate hireDate, String jobId, int salary,
			double comissionPct, int managerId, int departmentId) {
		this.mId = id;
		this.mFirstName = firstName;
		this.mLastName = lastName;
		this.mEmail = email;
		this.mPhoneNumber = phoneNumber;
		this.mHireDate = hireDate;
		this.mJobId = jobId;
		this.mSalary = salary;
		this.mCommissionPct = comissionPct;
		this.mManagerId = managerId;
		this.mDepartmentId = departmentId;
	}

	// -------------------------------------------------------------------------

	public String getFirstName() {
		return this.mFirstName;
	}

	public String getLastName() {
		return this.mLastName;
	}

	public String getEmail() {
		return this.mEmail;
	}

	public String getPhoneNumber() {
		return this.mPhoneNumber;
	}

	public LocalDate getHireDate() {
		return this.mHireDate;
	}

	public String getJobId() {
		return this.mJobId;
	}

	public int getSalary() {
		return this.mSalary;
	}

	public double getCommissionPct() {
		return this.mCommissionPct;
	}

	public int getManagerId() {
		return this.mManagerId;
	}

	public int getDepartmentId() {
		return this.mDepartmentId;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		return String.format("employeeId:%4d, firstName:%-16s, lastName:%-16s," +
				" email:%-16s, phoneNumber:%-20s, hireDate:%s, jobId:%-16s," +
				" salary:%8d, comissionPct:%4.2f, managerId:%4d, departmentId:%4d",
				this.getId(), this.getFirstName(), this.getLastName(),
				this.getEmail(), this.getPhoneNumber(), this.getHireDate(),
				this.getJobId(),
				this.getSalary(), this.getCommissionPct(), this.getManagerId(),
				this.getDepartmentId());
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		var item = new EmployeeItem(mId, mFirstName, mLastName, mEmail, mPhoneNumber,
				mHireDate, mJobId, mSalary, mCommissionPct, mManagerId, mDepartmentId);
		return item;
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		var ei = (EmployeeItem) item;
		this.setValues(ei.mId, ei.mFirstName, ei.mLastName, ei.mEmail, ei.mPhoneNumber,
				ei.mHireDate, ei.mJobId, ei.mSalary, ei.mCommissionPct, ei.mManagerId,
				ei.mDepartmentId);
	}
}
