package iCare;

import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/DeleteServlet")  
public class DeleteServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
    	 response.setContentType("text/html");  
         PrintWriter out=response.getWriter();  
		 String sid=request.getParameter("id"); 
		 int id=Integer.parseInt(sid);
		 int status = AdministratorDao.deleteDoctor(id); 
		 if(status>0) {
	        	out.println("<script type=\"text/javascript\">");
	        	out.println("alert('Doctor was deleted successfully');");
	        	out.println("location='Administration.jsp';");
	        	out.println("</script>"); 
	        	request.getRequestDispatcher("Administration.jsp").include(request, response);
	        }
	        else {
	        	out.print("<script><alert>Unsuccessfull attempt to delete doctor. Please contact iCare Center</alert></script>");
	        }
		 out.close();
		
    }  
}  