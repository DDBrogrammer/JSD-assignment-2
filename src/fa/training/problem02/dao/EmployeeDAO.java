package fa.training.problem02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import fa.training.problem02.entity.Employee;
import fa.training.problem02.utils.databaseConnection.MYSQLConnection;

public class EmployeeDAO implements BaseDAO<Employee,Integer> {
    private final String TABLE ="employees";
   
	@Override
	public ArrayList<Employee> findAll() {
		 Connection conn= MYSQLConnection.getConnection();
		ArrayList<Employee> listEmployee=new ArrayList<>();
		String sql = "SELECT * FROM "+TABLE ;
		try {
			Statement statement;
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				int id=result.getInt(1);					
			    Date birthDate = result.getDate(2);
			    String firstName = result.getString(3);
			    String lastName = result.getString(4);
			    String gender= result.getString(5);
			    Date hireDate=result.getDate(6);
			    Employee employee=new Employee(id,birthDate,firstName,lastName,gender,hireDate);
			    listEmployee.add(employee);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return listEmployee;
	}

	@Override
	public Employee findById(Integer id) {
    	Employee employee=new Employee();
    	ArrayList<Employee> listEmployee=findAll();
		for(Employee d:listEmployee) {
			if(d.getId()==id) {
				employee=d;
			}
		}
		 return employee;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean checkOk=false;
		 Connection conn= MYSQLConnection.getConnection();
		String sql = "DELETE FROM "+TABLE +" WHERE emp_no=?";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			checkOk=true;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return checkOk;
	}

	@Override
	public boolean save(Employee employee) {					
		 String saveSql = "INSERT INTO " + TABLE + " ( birth_date, first_name,last_name,gender,hire_date) VALUES ( ?, ?, ?, ?, ?)";
		 String updateSql = "UPDATE " + TABLE + " SET  birth_date=? , first_name =? , last_name=? , gender=?,hire_date=? WHERE emp_no=?";

		 boolean checkOk=false;
		 boolean checkExit=false;
		 ArrayList<Employee> listEmployee= findAll();
		 for(Employee e:listEmployee) {
			 if (e.getId()==employee.getId()) {
		      checkExit=true;		 
			 }
		 }
		 if(checkExit) {
			 try {		 Connection conn= MYSQLConnection.getConnection(); 
					PreparedStatement statement;
					statement = conn.prepareStatement(updateSql);
					statement.setDate(1, employee.getBirthDate());
					statement.setString(2, employee.getFirstName());
					statement.setString(3, employee.getLastName());
					statement.setString(4, employee.getGender());
					statement.setDate(5, employee.getHireDate());
					statement.setLong(6,employee.getId());
					int rowsInserted = statement.executeUpdate();
					if (rowsInserted > 0) {
						  checkOk=true;
						}
					conn.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }else {
			 try {	 Connection conn= MYSQLConnection.getConnection(); 
					PreparedStatement statement;
					statement = conn.prepareStatement(saveSql);
				
					statement.setDate(1, employee.getBirthDate());
					statement.setString(2, employee.getFirstName());
					statement.setString(3, employee.getLastName());
					statement.setString(4, employee.getGender());
					statement.setDate(5, employee.getHireDate());
					int rowsInserted = statement.executeUpdate();
					if (rowsInserted > 0) {
						  checkOk=true;
						}
					conn.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			 
		 }
		
		return checkOk;
	}

}