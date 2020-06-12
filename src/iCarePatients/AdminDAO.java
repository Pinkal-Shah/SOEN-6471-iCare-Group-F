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

public class AdminDAO {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost:3306";
	final private String user = "root";
	final private String passwd = "root";

	public boolean delete(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/iCare?" + "user=" + user + "&password=" + passwd);
			statement = connect.createStatement();
			String sql = "DELETE FROM booking WHERE username = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, username);

			int result = statement.executeUpdate();

			if (result!=0) {
				System.out.println("Deleted all bookings and now deleting the doctor");
				String sql1 = "DELETE FROM doctor_login WHERE username = ?";
				PreparedStatement statement1 = connect.prepareStatement(sql1);
				statement1.setString(1, username);

				int result1 = statement1.executeUpdate();

				if (result1!=0) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return false;

	}

	
	public static int save(String name, String pass, String uname, String dept) {
		int status = 0;
		try {
			Connection con = PatientDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into doctor_login(name,password,username,department) values (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setString(3, uname);
			ps.setString(4, dept);

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
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

}
