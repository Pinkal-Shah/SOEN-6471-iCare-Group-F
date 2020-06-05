package iCare;
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
@SuppressWarnings("serial")
public class PatientScheduleServlet extends HttpServlet{
	private boolean bIsValidated = false;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
	    throws ServletException, IOException {  
	  
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String sUserName=request.getParameter("username");  
	    //out.print("Welcome "+n);  
	    	
	    PatientScheduleDao oDao = new PatientScheduleDao();
	    // Find the department selected and add the doctors
	    String sDept = request.getParameter("department");
	    if (sDept != null)
	    {
	    	System.out.println("Department found!");
	    	String sQuerry = "SELECT * from doctor_login WHERE department='" + sDept + "'";
	    	
			ResultSet oDoctorsSet = oDao.GetQuerry(sQuerry);
			ArrayList<String> oDoctorsLogin = new ArrayList<String>();
			ArrayList<String> oDoctorsName = new ArrayList<String>();
			try {
				while (oDoctorsSet.next())
				{
					oDoctorsLogin.add(oDoctorsSet.getString("doctor_login"));
					oDoctorsName.add(oDoctorsSet.getString("name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("doctorsLogin", oDoctorsLogin);
			request.setAttribute("doctorsName", oDoctorsName);
	    }
	    
	    String sDoctorLogin = request.getParameter("doctor");
	    if (sDoctorLogin != null)
	    {
	    	System.out.println("Doctor found!");
	    	String sDate = request.getParameter("date");
	    	String sTimeSlot = request.getParameter("timeslot");
	    	if (sDate != null && sTimeSlot != null)
	    	{
	    		System.out.println("Date found!");
	    		if (!bIsValidated)
	    			bIsValidated = oDao.ValidateBooking("1", sUserName, sDate + " " + sTimeSlot + ":00");
	    		else
	    			bIsValidated = oDao.BookAppointment("1", sUserName, sDate + " " + sTimeSlot + ":00");
	    	}
	    }
	    request.getRequestDispatcher("Welcome.jsp").include(request, response);
	    oDao.CloseConnection();
	    out.close();  
	    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
		    throws ServletException, IOException { 
			PatientScheduleDao oDao = new PatientScheduleDao();
			ArrayList<String> oDepts = oDao.GetDepartment();
			request.setAttribute("departments", oDepts);
			System.out.println("Chamando essa porra da desgraça");
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
			oDao.CloseConnection();
		}  
	}  