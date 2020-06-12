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
@WebServlet("/getDoctors")
public class getDoctors extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getDoctors() {
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
		//System.out.println(request.getParameter("value"));
		String department =request.getParameter("department"); 
		
		ArrayList<String> listofDoctors = PatientDao.GetDoctors(department);

		System.out.println("Doctors:");
		for (String e : listofDoctors) {
			System.out.println("-------");
			System.out.println(e + " ");
		}
		
		request.setAttribute("listofDoctors", listofDoctors);  

		//forward the request to jsp
		request.getRequestDispatcher("/WebContent/Welcome.jsp").forward(request, response);

	}
}
