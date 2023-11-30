package ezen.project.first.team2.app.common.z_test.modules.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.utils.TimeUtils;

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

	public void setFirstName(String name) {
		this.mFirstName = name;
	}

	public void setLastName(String name) {
		this.mLastName = name;
	}

	public void setEmail(String email) {
		this.mEmail = email;
	}

	public void setPhoneNumber(String phoneNum) {
		this.mPhoneNumber = phoneNum;
	}

	public void setHireDate(LocalDate date) {
		this.mHireDate = date;
	}

	public void setJobId(String id) {
		this.mJobId = id;
	}

	public void setSalary(int salary) {
		this.mSalary = salary;
	}

	public void setCommissionPct(double pct) {
		this.mCommissionPct = pct;
	}

	public void setMangerId(int id) {
		this.mManagerId = id;
	}

	public void setDepartmentId(int id) {
		this.mDepartmentId = id;
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

	public String getHireDateStr() {
		return TimeUtils.localDateToStr(this.getHireDate());
	}

	public String getHireDateStrYYYYMMDD() {
		return this.getHireDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
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

	public String getLabelString() {
		return "employeeId, firstName, lastName," +
				" email, phoneNumber, hireDate, jobId," +
				" salary, comissionPct, managerId, departmentId";
	}

	@Override
	public String toString() {
		return String.format("%4d, %-16s, %-16s, %-16s, %-20s, %s, %-16s, %8d, %5.2f, %4d, %4d",
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
