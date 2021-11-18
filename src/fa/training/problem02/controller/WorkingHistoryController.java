package fa.training.problem02.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import fa.training.problem02.dao.WorkingHistoryDAO;
import fa.training.problem02.entity.Department;
import fa.training.problem02.entity.Employee;
import fa.training.problem02.entity.WorkingHistory;
import fa.training.problem02.utils.validate.WorkingHistoryValidate;

public class WorkingHistoryController {
   private WorkingHistoryValidate workingHistoryValidate=new WorkingHistoryValidate();
   private WorkingHistoryDAO workingHistoryDAO=new WorkingHistoryDAO();
   private EmployeeController employeeController=new EmployeeController();
   private DepartmentController departmentController=new DepartmentController();
   public  WorkingHistory getWorkingHistory() {
	    employeeController.showListEmployee();
	    departmentController.showListDepartment();
		WorkingHistory newWorkingHistory = new WorkingHistory();
			int empId = getNumberInfor("Enter employee id: ");
			while(!workingHistoryValidate.validateEmployeeId(empId)) {
				System.out.println("You must enter a validable employee id");
			    empId =getNumberInfor("Enter employee id: ");
			}
			newWorkingHistory.setEmployeeId(empId);
	     
	       
	        int deptId = getNumberInfor("Enter department id: ");
			while(!workingHistoryValidate.validateDepartmentId(deptId)) {
				System.out.println("You must enter a validable department id");
			    deptId =getNumberInfor("Enter department id: ");
			}
			newWorkingHistory.setDepartmentId(deptId);
	       String fromDate = getStringInfor("Enter from date in format [year-month-day] like 2001-03-24: ");
	     while(!workingHistoryValidate.validateDate(fromDate)) {
	    	 System.out.println("You enter wrong date format, now pls try again :v");
	    	 fromDate=getStringInfor("Enter from date in format [year-month-day] like 2001-03-24: ");
	     }
	      newWorkingHistory.setFromDate(workingHistoryValidate.getValidatedDateFromString(fromDate));
	      
	      String toDate = getStringInfor("Enter to date in format [year-month-day] like 2001-03-24: ");
	  
	       
		     while(!workingHistoryValidate.validateDate(toDate)||!workingHistoryValidate.validateToDateAndFromDate(fromDate, toDate)) {
		    	 if(!workingHistoryValidate.validateDate(toDate)) {
			    	 System.out.println("You enter wrong date format, now pls try again :v");
		    	 }else  {

		    			 System.out.println("To date must be after from date , now pls try again :v");
		    		 
		    		
		    	 }	    	 
		    	 toDate=getStringInfor("Enter to date in format [year-month-day] like 2001-03-24: ");
		     }
		  

		      newWorkingHistory.setToDate(workingHistoryValidate.getValidatedDateFromString(toDate));
		  return newWorkingHistory;
	}
     public ArrayList<WorkingHistory> findAll(){
    	 return workingHistoryDAO.findAll();
     }
     public void showByWorkTime() {
    		ArrayList<WorkingHistory> workingHistoryList= findAll();
 		ArrayList<Employee> employeeList= employeeController.findAll();
 		 int deptId = getNumberInfor("Enter department id: ");
			while(!workingHistoryValidate.validateDepartmentId(deptId)) {
				System.out.println("You must enter a validable department id");
			    deptId =getNumberInfor("Enter department id: ");
			}
			String fromDate = getStringInfor("Enter from date in format [year-month-day] like 2001-03-24: ");
		     while(!workingHistoryValidate.validateDate(fromDate)) {
		    	 System.out.println("You enter wrong date format, now pls try again :v");
		    	 fromDate=getStringInfor("Enter from date in format [year-month-day] like 2001-03-24: ");
		     }
		      String toDate = getStringInfor("Enter to date in format [year-month-day] like 2001-03-24: ");
			     while(!workingHistoryValidate.validateDate(toDate)||!workingHistoryValidate.validateToDateAndFromDate(fromDate, toDate)) {
			    	 if(!workingHistoryValidate.validateDate(toDate)) {
				    	 System.out.println("You enter wrong date format, now pls try again :v");
			    	 }else  {

			    			 System.out.println("To date must be after from date , now pls try again :v");
			    		 
			    		
			    	 }	    	 
			    	 toDate=getStringInfor("Enter to date in format [year-month-day] like 2001-03-24: ");
			     }
			  
	    ArrayList<Employee> employeeShowList=findByWorkTime( workingHistoryValidate.getValidatedDateFromString(fromDate), workingHistoryValidate.getValidatedDateFromString(toDate));
	    ArrayList<Employee> employeeShowListCheck=new ArrayList<Employee>();
 		for(Employee e:employeeShowList) {
 		          for(WorkingHistory w:workingHistoryList) {
 		        	  
 		        		 if(e.getId()==w.getEmployeeId()&&w.getDepartmentId()==deptId) {
 	 		        		 employeeShowListCheck.add(e);
 	 		        	  }  
 		          }
 		}
 		for (int i = 0; i < employeeShowListCheck.size(); i++) {
 	        for (int j = i + 1; j < employeeShowListCheck.size(); j++) {
 	            if (employeeShowListCheck.get(i).getId() == employeeShowListCheck.get(j).getId()) {
 	                employeeShowListCheck.remove(j);
 	                j--;
 	            }
 	        }
 	    }
 		for(Employee e:employeeShowListCheck) {
 			System.out.println(e.toString());
 		}
 	}
     
     public ArrayList<Employee> findByWorkTime(Date fromDate,Date toDate ) {
    		ArrayList<WorkingHistory> workingHistoryList= findAll();
    		ArrayList<Employee> employeeDBList= employeeController.findAll();
    		  ArrayList<Employee> employeeList=new ArrayList<Employee>();
    	 		for (WorkingHistory w:workingHistoryList) {
    	 			for(Employee e:employeeDBList) {
    	 				if( fromDate.before(w.getToDate())
    	     	 		 &&  toDate.after(w.getFromDate())
    	     	 	    	&& e.getId()==w.getEmployeeId())  { 
    	     	 			employeeList.add(e);
    	     	 			}	
    	 			}
    	 			
    	 		}
    	 	return employeeList; 	
	}
	public boolean saveWorkingHistory(WorkingHistory workingHistory) {
		if(workingHistoryDAO.save(workingHistory)) {
			return true;
		}else {
			return false;
		}
	}
   
   
   
   
	public static int getNumberInfor(String ask) {
		boolean run=true;
		int a=0;
		while(run==true) {
			try {
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

	public static String getStringInfor(String ask) {
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

	public static Double getDoubleInfor(String ask) {
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
