package iCarePatients;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class deleteDoctor
 */
@WebServlet("/deleteDoctor")
public class deleteDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteDoctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out=response.getWriter();  
		 String n=request.getParameter("n1");
		 System.out.println(n+"uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
		 boolean status=false;
		 AdminDAO dao=new AdminDAO();
		 status= dao.delete(n);
		 
		 if(status)
		 {				
	        	System.out.println("Delete Successfull");
	        	HttpSession session = request.getSession();
	        	//session.setAttribute("pid",id);
	            RequestDispatcher rd=request.getRequestDispatcher("WelcomeAdmin.jsp");  
	            rd.forward(request,response);  
	            
	        }  
	        else{  
	        	 System.out.println("Could not delete");
					/*
					 * out.println("<script type=\"text/javascript\">");
					 * out.println("alert('Wrong Credentials');");
					 * out.println("location='login.jsp';"); out.println("</script>");
					 */
	        }  
	}

}
