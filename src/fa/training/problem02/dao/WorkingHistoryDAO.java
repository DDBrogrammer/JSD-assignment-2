package fa.training.problem02.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fa.training.problem02.entity.Department;
import fa.training.problem02.entity.WorkingHistory;
import fa.training.problem02.utils.databaseConnection.MYSQLConnection;

public class WorkingHistoryDAO implements BaseDAO<WorkingHistory,Integer> {
    private final String TABLE ="working_history";
   
	@Override
	public ArrayList<WorkingHistory> findAll() {
		 Connection conn= MYSQLConnection.getConnection();
		ArrayList<WorkingHistory> listWorkingHistory=new ArrayList<>();
		String sql = "SELECT * FROM "+TABLE ;
		try {
			Statement statement;
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				int empId=result.getInt(2);
				int deptId=result.getInt(1);		
			    Date fromDate = result.getDate(3);
			    Date toDate=result.getDate(4);
			    WorkingHistory employee=new WorkingHistory(deptId, empId, fromDate, toDate);
			    listWorkingHistory.add(employee);
			}
		conn.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return listWorkingHistory;
	}

	
	
	
	@Override
	public WorkingHistory findById(Integer id) {
    	WorkingHistory workingHistory=new WorkingHistory();
		 return workingHistory;
	}
	public ArrayList<WorkingHistory> getListByDepartmentId(Integer id){
		ArrayList<WorkingHistory> listWorkingHistory=new ArrayList<WorkingHistory>();
		for(WorkingHistory wh:findAll()) {
			if(wh.getDepartmentId()==id) {
				listWorkingHistory.add(wh);
			}
		}
		return listWorkingHistory;
	}
	public ArrayList<WorkingHistory> getListByEmployeeId(Integer id){
		ArrayList<WorkingHistory> listWorkingHistory=new ArrayList<WorkingHistory>();
		for(WorkingHistory wh:findAll()) {
			if(wh.getEmployeeId()==id) {
				listWorkingHistory.add(wh);
			}
		}
		return listWorkingHistory;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean checkOk=false;		
		return checkOk;
	}
	public boolean deleteByEmployeeId(Integer id) {
		boolean checkOk=false;
		Connection conn= MYSQLConnection.getConnection();
		String sql= "DELETE FROM "+TABLE +" WHERE epm_no=?";
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
	public boolean deleteByDepartmentId(Integer id) {
		boolean checkOk=false;
		Connection conn= MYSQLConnection.getConnection();
		String sql= "DELETE FROM "+TABLE +" WHERE dept_no=?";
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
	public boolean save(WorkingHistory workingHistory) {					
		 String saveSql = "INSERT INTO " + TABLE + " (dept_no, emp_no, from_date,to_date) VALUES ( ?, ?, ?, ?)";
		 Connection conn= MYSQLConnection.getConnection(); 
		 boolean checkOk=false;
	
			 try {
					PreparedStatement statement;
					statement = conn.prepareStatement(saveSql);
					statement.setLong(1, workingHistory.getDepartmentId());
					statement.setLong(2, workingHistory.getEmployeeId());
					statement.setDate(3, workingHistory.getFromDate());
					statement.setDate(4, workingHistory.getToDate());
			
					int rowsInserted = statement.executeUpdate();
					if (rowsInserted > 0) {
						  checkOk=true;
						}
					conn.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			 
		 
		
		return checkOk;
	}

}