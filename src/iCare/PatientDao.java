package iCare;

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
	/*
	 * public static int update(patient e){ int status=0; try{ Connection
	 * con=EmpDao.getConnection(); PreparedStatement ps=con.prepareStatement(
	 * "update emp set name=?,password=?,email=?,country=? where id=?");
	 * ps.setString(1,e.getName()); ps.setString(2,e.getPassword());
	 * ps.setString(3,e.getEmail()); ps.setString(4,e.getGender());
	 * 
	 * ps.setInt(5,e.getId());
	 * 
	 * status=ps.executeUpdate();
	 * 
	 * con.close(); }catch(Exception ex){ex.printStackTrace();}
	 * 
	 * return status; } public static int delete(int id){ int status=0; try{
	 * Connection con=EmpDao.getConnection(); PreparedStatement
	 * ps=con.prepareStatement("delete from emp where id=?"); ps.setInt(1,id);
	 * status=ps.executeUpdate();
	 * 
	 * con.close(); }catch(Exception e){e.printStackTrace();}
	 * 
	 * return status; } public static patien getEmployeeById(int id){ patient e=new
	 * patient();
	 * 
	 * try{ Connection con=EmpDao.getConnection(); PreparedStatement
	 * ps=con.prepareStatement("select * from user905 where id=?"); ps.setInt(1,id);
	 * ResultSet rs=ps.executeQuery(); if(rs.next()){ e.setId(rs.getInt(1));
	 * e.setName(rs.getString(2)); e.setPassword(rs.getString(3));
	 * e.setEmail(rs.getString(4)); e.setGender(rs.getString(5)); } con.close();
	 * }catch(Exception ex){ex.printStackTrace();}
	 * 
	 * return e; } public static List<patient> getAllEmployees(){ List<patient>
	 * list=new ArrayList<patient>();
	 * 
	 * try{ Connection con=EmpDao.getConnection(); PreparedStatement
	 * ps=con.prepareStatement("select * from user905"); ResultSet
	 * rs=ps.executeQuery(); while(rs.next()){ patient e=new patient();
	 * e.setId(rs.getInt(1)); e.setName(rs.getString(2));
	 * e.setPassword(rs.getString(3)); e.setEmail(rs.getString(4));
	 * e.setGender(rs.getString(5)); list.add(e); } con.close(); }catch(Exception
	 * e){e.printStackTrace();}
	 * 
	 * return list; }
	 */

	public static boolean validate(String name, String pass) {
		boolean status = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iCare", "root", "root");

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
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iCare", "root", "root");

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

	/*
	 * public static List<Emp> getAllEmployees(){ List<Emp> list=new
	 * ArrayList<Emp>();
	 * 
	 * try{ Connection con=EmpDao.getConnection(); PreparedStatement
	 * ps=con.prepareStatement("select * from user905");
	 * 
	 * ResultSet* rs=ps.executeQuery(); while(rs.next()){ Emp e=new Emp();
	 * e.setId(rs.getInt(1)); e.setName(rs.getString(2));
	 * e.setPassword(rs.getString(3)); e.setEmail(rs.getString(4));
	 * e.setCountry(rs.getString(5)); list.add(e); } con.close(); }catch(Exception
	 * e){e.printStackTrace();}
	 * 
	 * return list; }
	 */

	public static List<booking> getFutureBookingDetails() {
		// TODO Auto-generated method stub
		 List<booking> listofFutureBookings=new ArrayList<booking>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iCare", "root", "root");

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
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iCare", "root", "root");

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
	
}