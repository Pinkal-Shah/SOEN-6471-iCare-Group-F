package iCare;

import java.sql.*;

public class booking {
	private int id;
	private Date datetime;
	private int patientId;
	private String doctorUsername;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public Date getDateTime() {
		return datetime;
	}
	
	public void setDateTime(Date datetime) {
		this.datetime = datetime;
	}
	 
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getDoctorUsername() {
		return doctorUsername;
	}
	
	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

}
