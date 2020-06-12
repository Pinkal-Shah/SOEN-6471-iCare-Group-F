package iCarePatients;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
public class DoctorDAO {
	
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "root";

	
	public boolean checkLogin(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/iCare?"
		              + "user=" + user + "&password=" + passwd );
		      statement = connect.createStatement();
		      String sql = "SELECT * FROM doctor_login WHERE username = ? and password = ?";
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
	
	public ArrayList<ArrayList<String>> getSchedule(String username) {
		
		ArrayList<ArrayList<String>> schedules = new ArrayList<>();
		
		ArrayList<String> storedSchedule = new ArrayList<>();
		ArrayList<String> upcomingSchedule = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/iCare?"
		              + "user=" + user + "&password=" + passwd );

		      statement = connect.createStatement();
		      
		      
//		      PreparedStatement statement1 =  connect.prepareStatement("SELECT dtime FROM doctor.booking");
//		      ResultSet dateconverted = statement1.executeQuery();
//		      java.sql.Date dd = null ;
//		      
//		      if(dateconverted.next()) {
//		    	dd = dateconverted.getDate("dtime");		    	  
//		      }		    
		      
		      long millis=System.currentTimeMillis(); 
		      
		      java.sql.Date dateToday = new java.sql.Date(millis);
		      
		      Calendar cal = Calendar.getInstance();		      
		      cal.setTime(dateToday);
		      cal.add(Calendar.DAY_OF_YEAR,1);
		      cal.set(Calendar.HOUR_OF_DAY, 0);
		      cal.set(Calendar.MINUTE, 0);
		      cal.set(Calendar.SECOND, 0);
		      cal.set(Calendar.MILLISECOND, 0);
		      
		      java.sql.Date dateTommorow = new java.sql.Date(cal.getTimeInMillis());
		      
//		      java.sql.Date sDate = new java.sql.Date(date.getTime());
//		      java.sql.Date sTime = null;
//		      if(dd == sDate) {
//		    	  sTime = new java.sql.Date(date.getTime());
//		      }
		      
		      String sql = "SELECT booking.bid,booking.dtime,iCare.patients.NAME FROM iCare.patients INNER JOIN iCare.booking ON iCare.patients.Pid = iCare.booking.Pid WHERE dtime >= '"+dateToday+"' and dtime < '"+dateTommorow+"' ";
		       
		      PreparedStatement statement = connect.prepareStatement(sql);
		      ResultSet result = statement.executeQuery();
		      	
		      
		      while(result.next()) {
		    	  String datetime[] = result.getString("dtime").split(" ");
		    	  String patient = result.getString("NAME");
		    	  storedSchedule.add(datetime[0]);
		    	  storedSchedule.add(datetime[1]);
		    	  storedSchedule.add(patient);
		    	  storedSchedule.add(result.getString("bid"));
		      }
		      System.out.println("dd:"+dateTommorow);
		      String upcomingsql = "SELECT booking.bid,booking.dtime,iCare.patients.NAME FROM iCare.patients INNER JOIN iCare.booking ON iCare.patients.Pid = iCare.booking.Pid WHERE dtime >= '"+dateTommorow+"' ";
		       
		      statement = connect.prepareStatement(upcomingsql);
		      result = statement.executeQuery();
		      
		      while(result.next()) {
		    	  String datetime[] = result.getString("dtime").split(" ");
		    	  String patient = result.getString("NAME");
		    	  upcomingSchedule.add(datetime[0]);
		    	  upcomingSchedule.add(datetime[1]);
		    	  upcomingSchedule.add(patient);
		    	  upcomingSchedule.add(result.getString("bid"));
		      }
		    
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      close();
		    }
		
		schedules.add(storedSchedule);
		schedules.add(upcomingSchedule);
		return schedules;

		
	}
	
	public void updateAppointment(int bid) throws SQLException {
		
		connect = getConnection();
		statement = connect.createStatement();
	      
		String sql = "DELETE FROM iCare.booking WHERE bid = '"+bid+"'";
	    PreparedStatement statement = connect.prepareStatement(sql);
	
	    int update = statement.executeUpdate();	
		
	}
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/iCare?"
		              + "user=" + user + "&password=" + passwd );
		}catch (Exception e) {
		      e.printStackTrace();
		} 
//		finally {			
//		      close();
//		    }
		
		return connect;
		
	}

}
