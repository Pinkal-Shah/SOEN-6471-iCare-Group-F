package iCarePatients;

import java.util.*;
import java.sql.*;

public class PatientDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iCare", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(patient e) {
		int status = 0;
		try {
			Connection con = PatientDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into patients(name,password,email,gender) values (?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getGender());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	
	
	public static int Appoint(String pid, String dtime, String username) {
		int status = 0;
		try {
			System.out.println("pid:"+pid);
			Connection con = PatientDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement(" insert into booking (dtime, Pid,username) values (?,?,?);");
			ps.setString(1, dtime);
			ps.setString(2, pid);
			ps.setString(3, username);

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	

	public static boolean validate(String name, String pass) {
		boolean status = false;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = PatientDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from patients where email=? and password=?");
			ps.setString(1, name);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static String getUname(String n, String p) {
		String name = "";
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = PatientDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select name from patients where email=? and password=?");
			ps.setString(1, n);
			ps.setString(2, p);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}

			// status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}

		return name;
	}
	
	public static String getId(String n, String p) {
		String pid = "";
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = PatientDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select Pid from patients where email=? and password=?");
			ps.setString(1, n);
			ps.setString(2, p);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pid = rs.getString("Pid");
			}

			// status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}

		return pid;
	}


	public static List<booking> getFutureBookingDetails() {
		// TODO Auto-generated method stub
		 List<booking> listofFutureBookings=new ArrayList<booking>();
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = PatientDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select bid,dtime,name,department  from booking INNER JOIN doctor_login ON booking.username= doctor_login.username WHERE dtime >= NOW()");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 	booking e=new booking();  
	                e.setId( rs.getInt("bid"));   // Input Id
	                e.setDateTime(rs.getDate("dtime").toString());  // Input the date
	                e.setName(rs.getString("name"));  // Input the Name of Doc
	                e.setDepartment(rs.getString("department"));    // Input the name of the Doctor
	                listofFutureBookings.add(e);  
				

				//listofFutureBookings.add(e);
			}
			// status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return listofFutureBookings;

		// return null;

	}
	
	
	public static List<booking> getPastBookingDetails() {
		// TODO Auto-generated method stub
		 List<booking> listofPastBookings=new ArrayList<booking>();
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = PatientDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select bid,dtime,name,department  from booking INNER JOIN doctor_login ON booking.username= doctor_login.username WHERE dtime < NOW()");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 	booking e=new booking();  
	                e.setId( rs.getInt("bid"));   // Input Id
	                e.setDateTime(rs.getDate("dtime").toString());  // Input the date
	                e.setName(rs.getString("name"));  // Input the Name of Doc
	                e.setDepartment(rs.getString("department"));    // Input the name of the Doctor
	                listofPastBookings.add(e);  
				

			}
			
			con.close();
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return listofPastBookings;

		// return null;

	}
	
	
	
	//Author : Leticia
	public static ArrayList<String> GetAllDepartments() {
		// TODO Auto-generated method stub
		ArrayList<String> listofDepartments=new ArrayList<String>();
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = PatientDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select DISTINCT department from doctor_login");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 	//doctorDetails e=new doctorDetails();  
	                String e= ( rs.getString("department"));	                
	                listofDepartments.add(e);  	
			}
			
			con.close();		
		} catch (Exception e) {
			System.out.println(e);
		}
		return listofDepartments;
	}
	
	
	
	//Author : Leticia
		public static ArrayList<String> GetDoctors(String department) {
			// TODO Auto-generated method stub
			ArrayList<String> listofDoctors=new ArrayList<String>();
			try {
				//Class.forName("com.mysql.jdbc.Driver");
				Connection con = PatientDao.getConnection();
				PreparedStatement ps = con.prepareStatement(" select * from doctor_login where department =?");
				ps.setString(1, department);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {  
		                String e= ( rs.getString("name"));	                
		                listofDoctors.add(e);  	
				}
				
				con.close();		
			} catch (Exception e) {
				System.out.println(e);
			}
			return listofDoctors;
		}

		public static boolean validateAppointment(String name, String dept, String time) {
			boolean status = false;
			try {
				//Class.forName("com.mysql.jdbc.Driver");
				Connection con = PatientDao.getConnection();

				PreparedStatement ps = con.prepareStatement("select dtime,name,department  from booking INNER JOIN doctor_login WHERE doctor_login.name=? AND booking.dtime=? AND department=?");
				ps.setString(1, name);
				ps.setString(2, time);
				ps.setString(2, dept);
				
				ResultSet rs = ps.executeQuery();
				status = rs.next();

			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
		}

	
}