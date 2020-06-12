package iCarePatients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		DoctorDAO dao = new DoctorDAO();	
		String bookingId = request.getParameter("setval");
		System.out.println(bookingId);
		if(bookingId != null) {
			int bid = Integer.parseInt(bookingId.trim());
			dao.updateAppointment(bid);						
		}
		ArrayList<ArrayList<String>> schedules = dao.getSchedule(request.getParameter("username"));
    	request.setAttribute("schedule", schedules.get(0));
    	request.setAttribute("upcomingschedule", schedules.get(1));
    	String destPage = "doctorHome.jsp";
    	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
    	dispatcher.forward(request,response);
		
	}

}
