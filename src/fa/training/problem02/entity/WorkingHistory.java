package fa.training.problem02.entity;

import java.sql.Date;
import java.util.Objects;

public class WorkingHistory {
	 @Override
	public String toString() {
		return "WorkingHistory [departmentId=" + departmentId + ", employeeId=" + employeeId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	private int departmentId;
	 private int employeeId;
	 private Date fromDate;
     private Date toDate;
	 public  WorkingHistory() {}
	 public WorkingHistory(int departmentId, int employeeId, Date fromDate, Date toDate) {
		super();
		this.departmentId = departmentId;
		this.employeeId = employeeId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	 public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(departmentId, employeeId, fromDate, toDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkingHistory other = (WorkingHistory) obj;
		return departmentId == other.departmentId && employeeId == other.employeeId
				&& Objects.equals(fromDate, other.fromDate) && Objects.equals(toDate, other.toDate);
	}
	
}
