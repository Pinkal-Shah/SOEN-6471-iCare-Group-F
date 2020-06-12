package iCarePatients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class getDepartmentDetailsServlet
 */
@WebServlet("/getDepartmentDetailsServlet")
public class getDepartmentDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getDepartmentDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Reached");
		ArrayList<String> listOfDepartments = PatientDao.GetAllDepartments();

		System.out.println("Departments");
		for (String e : listOfDepartments) {
			System.out.println("-------");
			System.out.println(e + " ");
		}

		/*
		 * List<booking> listOfPastBookings=PatientDao.getPastBookingDetails();
		 * 
		 * System.out.println("Past Appointments"); List<String> listOfPastBooking=new
		 * ArrayList<String>();
		 */

		/*
		 * for(String e:listOfDepartments){ System.out.print(e+" ");
		 * 
		 * 
		 * System.out.print(e.getId()+" ");
		 * //listOfPastBooking.add(String.valueOf(e.getId()));
		 * System.out.print(e.getDepartment()+" ");
		 * //listOfPastBooking.add(String.valueOf(e.getId()));
		 * System.out.print(e.getName()+" ");
		 * //listOfPastBooking.add(String.valueOf(e.getId()));
		 * System.out.println(e.getDateTime()+" ");
		 * 
		 * //listOfPastBooking.add(String.valueOf(e.getId())); }
		 */

		//request.setAttribute("listOfDepartments", listOfDepartments);
		//request.getRequestDispatcher("./WebContent/Welcome.jsp").forward(request, response);
		
		request.setAttribute("listOfDepartments", listOfDepartments);  

		//forward the request to jsp
		request.getRequestDispatcher("/WebContent/Welcome.jsp").forward(request, response);

	}
}
