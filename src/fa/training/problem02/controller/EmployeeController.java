package fa.training.problem02.controller;

import java.util.ArrayList;
import java.util.Scanner;


import fa.training.problem02.dao.DepartmentDAO;
import fa.training.problem02.dao.EmployeeDAO;
import fa.training.problem02.entity.Employee;
import fa.training.problem02.entity.WorkingHistory;
import fa.training.problem02.utils.validate.DepartmentValidate;
import fa.training.problem02.utils.validate.EmployeeValidate;

public class EmployeeController   {
	private EmployeeDAO employeeDao=new EmployeeDAO();
	private EmployeeValidate employeeValidate=new EmployeeValidate();

	public  Employee getEmployee() {
		Employee newEmployee = new Employee();
			String firstName = getStringInfor("Enter employee fisrt name: ");
			while(!employeeValidate.validateFirstName(firstName)) {
				System.out.println("You must enter less than 50 character");
				firstName =getStringInfor("Enter employee fisrt name: ");
			}
			newEmployee.setFirstName(firstName);
	     String lastName = getStringInfor("Enter employee last name: ");
	     while(!employeeValidate.validateLastName(lastName)) {
	    	 System.out.println("You enter wrong name format, just 50 character, try to short your name, now pls try again :v");
	    	 lastName=getStringInfor("Enter employee last name:");
	     }
	      newEmployee.setLastName(lastName);

		  String gender= getStringInfor("Enter employee gender [M] for male  or [F] for female: ");
		  while(!employeeValidate.validateGender(gender.toUpperCase())) {
			  System.out.println("You enter wrong gender format !!! now pls try again :v");
			  gender=getStringInfor("Enter employee gender [M] for male  or [F] for female: ");
		  }
		  newEmployee.setGender(gender.toUpperCase());
		  String dateOfBirth = getStringInfor("Enter employee date of birth in format [year-month-day] like 2001-03-24: "); 
		  while(!employeeValidate.validateDate(dateOfBirth)) {
			  System.out.println("You enter wrong date format !!! now pls try again :v");
			  dateOfBirth=getStringInfor("Enter employee date of birth in format [year-month-day] like 2001-03-24: ");
		  }
		  newEmployee.setBirthDate(employeeValidate.getValidatedDateFromString(dateOfBirth));
		 String hireDate = getStringInfor("Enter employee hire date in format [year-month-day] like 2001-03-24: ");
		 while(!employeeValidate.validateDate(hireDate)) {
			 System.out.println("You enter wrong date format !!! now pls try again :v");
			  hireDate=getStringInfor("Enter employee hire date in format [year-month-day] like 2001-03-24: ");
		 }
		 newEmployee.setHireDate(employeeValidate.getValidatedDateFromString(hireDate));
		  return newEmployee;
	}

	
	public boolean saveEmployee(Employee employee) {
		if(employeeDao.save(employee)) {
			return true;
		}else {
			return false;
		}
	}
	public boolean updateEmployee() {
		boolean checkInBD=false;
		int id = 0;
		while(!checkInBD) {
			int empId= getNumberInfor("Enter employee id:");
			ArrayList<Employee> employeeList=employeeDao.findAll();
			for(Employee e:employeeList) {
				if (e.getId()==empId) {
					checkInBD=true;
					id=empId;
					 break;
				}
					
				
			}
			if(!checkInBD) {
			System.out.println("You must enter id of employee in table");}
		}
		
		Employee employee= getEmployee();
		employee.setId(id);
		return saveEmployee(employee);
	}
	
	public void findById(Integer id) {
		Employee employee=employeeDao.findById(id);
		System.out.println(employee.toString());
	} 
	
	public void showListEmployee() {
		ArrayList<Employee> employeeList= employeeDao.findAll();
		for (Employee e:employeeList) {
			System.out.println(e.toString());
		}
	}
	
	public ArrayList<Employee> findAll() {
		return employeeDao.findAll();
		
	}
	
	
	
 	public int getNumberInfor(String ask) {
		boolean run=true;
		int a=0;
		while(run==true) {try {
			Scanner sc = new Scanner(System.in);
			System.out.println(ask);
			 a = sc.nextInt();
				run=false;
		
		}catch(Exception e ) {
			System.out.println("You must enter a number :v");
		}
	
		}
		
		return (int)a;
	}

	public  String getStringInfor(String ask) {
		boolean run=true;
		String s="";
		while(run==true) {try {
		Scanner sc = new Scanner(System.in);
		System.out.println(ask);
		s = sc.nextLine();
		run=false;
	
		}
		catch(Exception e ) {
			System.out.println("You must enter some text :D");
		}
			
		}
		return s;
	}

	public Double getDoubleInfor(String ask) {
		boolean run=true;
		Double d=0.0;
		while(run==true) {try {
		Scanner sc = new Scanner(System.in);
		System.out.println(ask);
		 d = sc.nextDouble();
			run=false;
	 
		}
		catch(Exception e ) {
			System.out.println("You must enter anumber :D");
		}
		
		}
		return (Double)d;
	}


}
