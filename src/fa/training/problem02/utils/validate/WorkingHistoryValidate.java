package fa.training.problem02.utils.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fa.training.problem02.dao.DepartmentDAO;
import fa.training.problem02.dao.EmployeeDAO;
import fa.training.problem02.entity.Department;
import fa.training.problem02.entity.Employee;

public class WorkingHistoryValidate {
	 private EmployeeDAO employeeDAO=new EmployeeDAO();
	 private DepartmentDAO departmentDAO=new DepartmentDAO();
	 private final String VALIDATE_DATE="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
	 public boolean validateDate(String date) {
		 if (date.matches(VALIDATE_DATE)) { return true;}
		 else {return false;}
	 }
	 public  java.sql.Date getValidatedDateFromString(String date) {
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        Date parsed = null;
			try {
				parsed = format.parse(date.replace("-", "").replace("/", "").replace(" ", ""));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 java.sql.Date sql = new java.sql.Date(parsed.getTime());
			return sql;
		
	 }
	 
	 public boolean validateToDateAndFromDate(String fromDate,String toDate) {
		 boolean checkOK=false;
		 if(validateDate(toDate)&&validateDate(fromDate)) {
			 java.sql.Date toDateSql= getValidatedDateFromString(toDate);
			 java.sql.Date fromDateSql=getValidatedDateFromString(fromDate);
			 if(fromDateSql.before(toDateSql)) {
				 checkOK= true;
			 }else {
				 checkOK= false;
			 }
		 }
		 
	     return checkOK;
	 }
	 
	 public boolean validateEmployeeId(Integer id) {
		ArrayList<Employee> employeeList=employeeDAO.findAll();
	   boolean checkOk=false;
		for(Employee e:employeeList) {
			if(id==e.getId()) {
				checkOk=true;
			}
		}
		return checkOk;
	 }
	 public boolean validateDepartmentId(Integer id) {
			ArrayList<Department> departmentList=departmentDAO.findAll();
		   boolean checkOk=false;
			for(Department d:departmentList) {
				if(id==d.getId()) {
					checkOk=true;
				}
			}
			return checkOk;
		 }
}
