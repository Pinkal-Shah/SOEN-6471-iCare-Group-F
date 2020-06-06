package iCare;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDoctorServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException { 
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        String department = request.getParameter("department");
        
        doctor d = new doctor();
        d.setName(firstName + " " + lastName);
        d.setUsername(username);
        d.setPassword(password);
        d.setDepartment(department);
        
        int status = AdministratorDao.createDoctor(d);
        
        if(status>0) {
        	out.println("<script type=\"text/javascript\">");
        	out.println("alert('Doctor added successfully');");
        	out.println("location='Administration.jsp';");
        	out.println("</script>"); 
        	request.getRequestDispatcher("Administration.jsp").include(request, response);
        }
        else {
        	out.print("<script><alert>Unsuccessfull attempt to add doctor. Please contact iCare Center</alert></script>");
        }
        out.close();
    }
}
