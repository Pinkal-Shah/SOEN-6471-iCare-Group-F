package iCare;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDoctorsServlet extends HttpServlet{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
		processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	AdministratorDao dao = new AdministratorDao();
    	ArrayList<String> doctorList = dao.getDoctors();
    	request.setAttribute("doctors", doctorList);

		try {
			request.getRequestDispatcher("Administration.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

    }
}
