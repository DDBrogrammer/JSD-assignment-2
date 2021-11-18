package fa.training.problem02.utils.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
 private static Connection con= null;
 private static final String URL="jdbc:mysql://localhost:3306/dbo";
 private static final String USER_NAME="root";
 private static final String PASSWORD="dai24032001";
 
 private MYSQLConnection() {}
 public static Connection getConnection() {
	 try {
		if(con==null|| con.isClosed())
		openConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} return con;
 } 
 private static synchronized void openConnection() {
	 try {
		 con=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 public void closeConnection() {
	 if(con!=null) {
		 try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }
}
