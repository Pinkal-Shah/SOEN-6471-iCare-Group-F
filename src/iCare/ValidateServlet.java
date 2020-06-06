package iCare;
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private boolean bIsValidated = false;
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
		    throws ServletException, IOException {  
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    String sUserName=request.getParameter("username");  
	    
	    PatientScheduleDao oDao = new PatientScheduleDao();
	    String sDoctorLogin = request.getParameter("doctor");
	    if (sDoctorLogin != null)
	    {
	    	System.out.println("Doctor found!");
	    	String sDate = request.getParameter("date");
	    	String sTimeSlot = request.getParameter("appt");
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
}
