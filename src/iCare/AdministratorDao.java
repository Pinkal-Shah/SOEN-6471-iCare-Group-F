package iCare;

import java.sql.*;
import java.util.List;

public class AdministratorDao {

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
	
	public static int createDoctor(doctor d) {
		int status = 0;
		try {
			Connection con = AdministratorDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into icare.doctor_login (username, password, name, department) values(?, ?, ?, ?);");
			ps.setString(1, d.getUsername());
			ps.setString(2, d.getPassword());
			ps.setString(3, d.getName());
			ps.setString(4, d.getDepartment());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static List<doctor> getAllDoctors() {
		List<doctor> allDoctorsList = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iCare", "root", "root");

			PreparedStatement ps = con.prepareStatement("select * from icare.doctor_login");

			ResultSet rs = ps.executeQuery();
			while(rs.next()){  
				String username = rs.getString("username");
				String name = rs.getString("name");
				String department = rs.getString("department");
				String password = "";
				
				doctor d = new doctor();
				d.setName(name);
				d.setUsername(username);
				d.setPassword(password);
				d.setDepartment(department);
				
				allDoctorsList.add(d);
            }  
		} catch (Exception e) {
			System.out.println(e);
		}		
		
		return allDoctorsList;
	}
}