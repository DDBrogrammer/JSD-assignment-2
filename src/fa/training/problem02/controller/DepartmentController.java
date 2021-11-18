package fa.training.problem02.controller;

import java.util.ArrayList;
import java.util.Scanner;

import fa.training.problem02.dao.DepartmentDAO;
import fa.training.problem02.entity.Department;
import fa.training.problem02.entity.Employee;
import fa.training.problem02.utils.validate.DepartmentValidate;

public class DepartmentController {
	private DepartmentDAO departmentDAO=new DepartmentDAO();
	private DepartmentValidate departmentValidate=new DepartmentValidate();
	
	public  Department getDepartment() {
		Department newDepartment = new Department();
			String name = getStringInfor("Enter department name: ");
			while(!departmentValidate.ValidateName(name)) {
				System.out.println("You must enter less than 50 character");
				name =getStringInfor("Enter department name: ");
			}
			newDepartment.setName(name);
	     String description = getStringInfor("Enter department description: ");
	     while(!departmentValidate.validatDescription(description)) {
	    	 System.out.println("You enter wrong name format, just 100 character, try to short your description, now pls try again :v");
	    	 description=getStringInfor("Enter department description:");
	     }
	      newDepartment.setDescription(description);
		  return newDepartment;
	}
	public boolean saveDepartment(Department department) {
		if(departmentDAO.save(department)) {
			return true;
		}else {
			return false;
		}
	}
	public void showListDepartment() {
		ArrayList<Department> departmentList=departmentDAO.findAll();
		for (Department d:departmentList) {
			System.out.println(d.toString());
		}
	}

	public  int getNumberInfor(String ask) {
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
