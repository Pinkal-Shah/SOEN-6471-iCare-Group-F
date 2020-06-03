import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDAO {
	
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "admin";

	
	public boolean checkLogin(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/doctor?"
		              + "user=" + user + "&password=" + passwd );
		      statement = connect.createStatement();
		      String sql = "SELECT * FROM doctor.doctor_login WHERE username = ? and password = ?";
		      PreparedStatement statement = connect.prepareStatement(sql);
		        statement.setString(1, username);
		        statement.setString(2, password);
		 
		        ResultSet result = statement.executeQuery();
		 
		      if (result.next()) {
		    			  return true;
		    		  }
		    	
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      close();
		    }
		
		return false;
	      

	}
	
	private void close() {
	      try {
	        if (resultSet != null) {
	          resultSet.close();
	        }

	        if (statement != null) {
	          statement.close();
	        }

	        if (connect != null) {
	          connect.close();
	        }
	      } catch (Exception e) {

	      }
	    }
	
	public ArrayList<String> getSchedule(java.util.Date date,String username) {
		
		ArrayList<String> storedSchedule = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/doctor?"
		              + "user=" + user + "&password=" + passwd );

		      statement = connect.createStatement();
		      
		      
		      PreparedStatement statement1 =  connect.prepareStatement("SELECT dtime FROM doctor.booking");
		      ResultSet dateconverted = statement1.executeQuery();
		      java.sql.Date dd = null ;
		      
		      if(dateconverted.next()) {
		    	dd = dateconverted.getDate("dtime");
		    	  
		      }
		      
//		      System.out.println(dd);
		      
		      java.sql.Date sDate = new java.sql.Date(date.getTime());
		      java.sql.Date sTime = null;
		      if(dd == sDate) {
		    	  sTime = new java.sql.Date(date.getTime());
		      }
		      
		      String sql = "SELECT booking.dtime,doctor.patients.NAME FROM doctor.patients INNER JOIN doctor.booking ON doctor.patients.Pid = doctor.booking.Pid WHERE '"+dd+"' = '"+sDate+"'";
		       
		      PreparedStatement statement = connect.prepareStatement(sql);
		      ResultSet result = statement.executeQuery();
		     
		      
		      while(result.next()) {
		    	  String datetime[] = result.getString("dtime").split(" ");
		    	  String patient = result.getString("NAME");
		    	  storedSchedule.add(datetime[0]);
		    	  storedSchedule.add(datetime[1]);
		    	  storedSchedule.add(patient);
		      }
		        
		    
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      close();
		    }
		
		return storedSchedule;

		
	}
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/doctor?"
		              + "user=" + user + "&password=" + passwd );
		}catch(Exception e) {
			e.printStackTrace();
		}		
	
		return connect;
		
	}

	
	

}
