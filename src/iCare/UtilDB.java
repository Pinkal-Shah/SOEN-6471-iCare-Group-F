package iCare;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.sql.Connection;

/**
 * Class to connect to the MySQL server and make all the requests
 * To get the tables and to set new lines to the tables
 * @author Leticia Pasdiora
 *
 */

public class UtilDB {

	private Connection oConnection;
	private Statement oStatement;
	
	
	/**
	 * Method to create a new instance and connect to the MySQL server
	 */
	public UtilDB()
	{
		String sConnectionUrl = "jdbc:mysql://localhost:3306/iCare?autoReconnect=true&useSSL=false";
		String sUser = "iCareRoot";
		String sPasswd = "root";
		String sDriver = "com.mysql.jdbc.Driver";
		try {
		    Class.forName(sDriver);
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			oConnection = DriverManager.getConnection(sConnectionUrl, sUser, sPasswd);
			oStatement = oConnection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to request querries to the database
	 * @param sQuerry - the querry you want to make
	 * @return Null if the querry wasn't found or there was an error, a list with the result otherwise.
	 */
	public ResultSet GetQuerry(String sQuerry)
	{
		ResultSet oRequest = null;
		try {
			oRequest = oStatement.executeQuery(sQuerry);
		} catch (SQLException e) {
			oRequest = null;
		}
		return oRequest;
	}
	
	/**
	 * Method to analyze if it is possible to add the requested appointment to the booking list
	 * @param sPid - the patient's id
	 * @param sUserName - the doctor's username 
	 * @param sDate - the date requested
	 * @return if the booking was inserted successfully or not (if the doctor is already busy in this time slot)
	 */
	public boolean InsertBooking(String sPid, String sUserName, String sDate)
	{
		ResultSet oRequest = null;
		try {
			oRequest = oStatement.executeQuery("SELECT * FROM booking WHERE username='" + sUserName + "';");
		} catch (SQLException e) {
			oRequest = null;
		}
		if (oRequest != null)
		{
			System.out.println("Found matching!");
			try {
				while (oRequest.next())
				{
					// Notice: Leave the 'contains' as the MySQL adds an extra .00 in the end of the string
					if (oRequest.getString("dtime").contains(sDate))
						return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String sQuerry = "INSERT INTO booking (dtime, pid, username) VALUES(?, ?, ?)";
		try {
			PreparedStatement oPreparedStatement = oConnection.prepareStatement(sQuerry);
			oPreparedStatement.setString(1, sDate);
			oPreparedStatement.setString(2, sPid);
			oPreparedStatement.setString(3, sUserName);
			oPreparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Method to close the connection
	 * Call this after finishing all the requests
	 */
	public void CloseConnection()
	{
		try {
			oConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
