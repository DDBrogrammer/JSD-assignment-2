package fa.training.problem02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fa.training.problem02.entity.Department;
import fa.training.problem02.utils.databaseConnection.MYSQLConnection;

public class DepartmentDAO implements BaseDAO<Department,Integer> {
    private final String TABLE ="departments";
   
	@Override
	public ArrayList<Department> findAll() {
		 Connection conn= MYSQLConnection.getConnection();
		ArrayList<Department> listDepartment=new ArrayList<>();
		String sql = "SELECT * FROM "+TABLE ;
		try {
			Statement statement;
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				int id=result.getInt(1);
			    String name = result.getString(2);
			    String description = result.getString(3);
			    Department department=new Department(id,name,description);
			    listDepartment.add(department);
			    
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return listDepartment;
	}

	@Override
	public Department findById(Integer id) {
    	Department department=new Department();
    	ArrayList<Department> listDepartment=findAll();
		for(Department d:listDepartment) {
			if(d.getId()==id) {
				department=d;
			}
		}
		 return department;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean checkOk=false;
		Connection conn= MYSQLConnection.getConnection();
		String sql = "DELETE FROM "+TABLE +" WHERE dept_no=?";
		 
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
	public boolean save(Department department) {
		 String saveSql = "INSERT INTO " + TABLE + " ( dept_name, description) VALUES ( ?, ?)";
		 String updateSql = "UPDATE " + TABLE + " SET dept_name=?, description=? WHERE dept_no=?";
		
		 boolean checkOk=false;
		 boolean checkExit=false;
		 ArrayList<Department> listDepartment= findAll();
		 for(Department d:listDepartment) {
			 if (d.getId()==department.getId()) {
		      checkExit=true;		 
			 }
		 }
		 if(checkExit) {
			 try { Connection conn= MYSQLConnection.getConnection();
					PreparedStatement statement;
					statement = conn.prepareStatement(updateSql);
					statement.setString(1, department.getName());
					statement.setString(2, department.getDescription());
					statement.setLong(3, department.getId());
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
			 try {Connection conn= MYSQLConnection.getConnection();
					PreparedStatement statement;
					statement = conn.prepareStatement(saveSql);
				
					statement.setString(1, department.getName());
					statement.setString(2, department.getDescription());
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
