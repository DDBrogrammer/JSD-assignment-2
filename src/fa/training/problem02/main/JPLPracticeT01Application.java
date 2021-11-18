package fa.training.problem02.main;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import fa.training.problem02.controller.DepartmentController;
import fa.training.problem02.controller.EmployeeController;
import fa.training.problem02.controller.WorkingHistoryController;
import fa.training.problem02.dao.DepartmentDAO;
import fa.training.problem02.dao.EmployeeDAO;
import fa.training.problem02.dao.WorkingHistoryDAO;
import fa.training.problem02.entity.Department;
import fa.training.problem02.entity.Employee;
import fa.training.problem02.utils.databaseConnection.MYSQLConnection;
import fa.training.problem02.utils.validate.DepartmentValidate;
import fa.training.problem02.utils.validate.EmployeeValidate;
import fa.training.problem02.utils.validate.WorkingHistoryValidate;

public class JPLPracticeT01Application {
     public static EmployeeController employeeController=new EmployeeController();
    public static WorkingHistoryController workingHistoryController=new WorkingHistoryController();
    public static DepartmentController departmentController=new DepartmentController();
	public static void main(String[] args) {
		   int n = 0;
	        boolean continueRun = true;
	        //main
	        while (n != 5 && continueRun) { //loop for users choices and stop when boolean continou =false
	            try {
	                printMenu();
	                n= getNumberInfor("Enter number form 1 to 3:");
	              } catch (Exception line) {
	            	  System.out.println(line);
	                System.out.println("you must enter number!!!");
	                n= getNumberInfor("Enter number form 1 to 3:");
	            }//tell users if they not enter number
	            switch (n) {// create and compare each user's choices
	                case 1:
	                         int n1=0;
		                     System.out.println("EMPLOYEE MANAGER");
		                     employeeController.showListEmployee();
		                	 try{   n1=getNumberInfor("Action:\n"
		                			+ " [1] Add a new Employee.\n"
		                			+ " [2] Update a Employee.\n"
		                			+ " [3] Find an Employee by id.\n"
		                			+ " [4] Add the working history.\n"
		                			+ " [5] Find all the employees by working period of time.\n"
		                			+ " [6] Back to menu.\n"
		                			+ " Enter action:");}
		                	 catch(Exception e) {
		                				System.out.println("you must enter number!!!");
		                				n1= getNumberInfor("Enter number form 1 to 5:");
		                	}
		                	 
	                    	if(n1==1) {
	                    		if(employeeController.saveEmployee(employeeController.getEmployee())) {
	                    			System.out.println("Airport saved");
	                    		}else {	System.out.println("Cant save employee");}
				                    if (!askYesNo()) {
				                        System.out.print("program end!!!");//end the program when asked
				                        continueRun = false;
				                    }// add user  if they want to continue or stop the program(ask2)
				                    break;
	                    	}else if(n1==2) {
	              
	                    		if(employeeController.updateEmployee()) {
	                    			System.out.println("Employee updated");
	                    		}else {	System.out.println("Cant update employee");}
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                 
	                    	}else if(n1==3) {
	                    		int empId= getNumberInfor("Enter employee id:");
	                    		employeeController.findById(empId);
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}else if(n1==4) {
	                    		if(workingHistoryController.saveWorkingHistory(workingHistoryController.getWorkingHistory())) {
	                    			System.out.println("Working history have bean added");
	                    		}else {	System.out.println("Cant add Working history");}
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}else if(n1==5) {
	                    		System.out.println("DEPARTMENT LIST");
	                    		departmentController.showListDepartment();
	                    		workingHistoryController.showByWorkTime();
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}
	                       else if(n1==6 ) {
	                    		break;
	                    		
	                    	} else { 
	                    		if (!askYesNo()) {
		                        System.out.print("program end!!!");//end the program when asked
		                        continueRun = false;
		                        
		                         }
	                    		// add user  if they want to continue or stop the program(ask2)
		                      }
	                     
	                    break;
	                case 2:	
	                	 int n2=0;
	                     System.out.println("DEPARTMENT MANAGER");
	                	 departmentController.showListDepartment();
	                	 try{   n1=getNumberInfor("Action:\n"
		                			+ " [1] Add a new Department.\n"
		                			+ " [2] Back to menu.\n"
		                			+ " Enter action:");}
		                	 catch(Exception e) {
		                				System.out.println("you must enter number!!!");
		                				n1= getNumberInfor("Enter number form 1 to 2:");
		                	}
	                	 if(n1==1) {
	                    		if(departmentController.saveDepartment(departmentController.getDepartment())) {
	                    			System.out.println("Department saved");
	                    		}else {	System.out.println("Cant save Department");}
				                    if (!askYesNo()) {
				                        System.out.print("program end!!!");//end the program when asked
				                        continueRun = false;
				                    }// add user  if they want to continue or stop the program(ask2)
				                    break;
	                    	}
	                	 else if(n1==2) {
	                    		break;
	                    		
	                    	} else { 
	                    		if (!askYesNo()) {
		                        System.out.print("program end!!!");//end the program when asked
		                        continueRun = false;
		                        
		                         }}
	                	
	                	 
	                case 3:
	                    System.out.print("program end!!!");
	                	 break;
	                default:
	                    System.out.print("invalid value!!!");// tell the users when  value is invalid
	            }

	        }
				
	}

	public static boolean askYesNo() {
		   boolean ok=false;
		   boolean runAgain=true;
		   String ans="";
		   while(runAgain==true) {
			   try {
           ans = getStringInfor("Do you want to continue with another function \n" +
                 "[Y]  yes\n" +
                 "[N]  no\n" +
                 "Choose an option: "); }catch (Exception e) {
			// TODO: handle exceptions
     	   ans = getStringInfor("You must enter Y(yes) or N(no).");
		     }
			  if(ans.toUpperCase().equals("Y")) {
				  ok=true;
				  runAgain=false;
			  }else if(ans.toUpperCase().equals("N")) {
				  ok=false;
				  runAgain=false;
			  }else {
				  runAgain=true;
			  }
			   
			   
		   }
		  return ok; 
		  
	   }

	public static void printMenu() {
		System.out.println("Choses action:\n" + "[1] Employee management.\n" + "[2] Department management.\n"
				+  "[3] Close program.");

	}

	public static int getNumberInfor(String ask) {
		boolean run = true;
		int a = 0;
		while (run == true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println(ask);
				a = sc.nextInt();
				run = false;
				
			} catch (Exception e) {
				System.out.println("You must enter a number :v");
			}

		}

		return (int) a;
	}

	public static String getStringInfor(String ask) {
		boolean run = true;
		String s = "";
		while (run == true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println(ask);
				s = sc.nextLine();
				run = false;
			
			} catch (Exception e) {
				System.out.println("You must enter some text :D");
			}

		}
		return s;
	}

	public static Double getDoubleInfor(String ask) {
		boolean run = true;
		Double d = 0.0;
		while (run == true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println(ask);
				d = sc.nextDouble();
				run = false;
				
			} catch (Exception e) {
				System.out.println("You must enter anumber :D");
			}

		}
		return (Double) d;
	}

}
