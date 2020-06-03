

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoctorSchedule
 */
@WebServlet("/DoctorSchedule")
public class DoctorSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorSchedule() {
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
//		doGet(request, response);
		try {
			processRequest(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		DoctorDAO dao = new DoctorDAO();
		
		
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("datetime")); 
		String username = request.getParameter("username");
		
		ArrayList<String> storedSchedule = dao.getSchedule(date,username);
		
		for(int i=0;i<storedSchedule.size();i++) {
			System.out.println(storedSchedule.get(i));
		}
		
		request.setAttribute("schedule", storedSchedule);
		try {
			request.getRequestDispatcher("schedule.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
