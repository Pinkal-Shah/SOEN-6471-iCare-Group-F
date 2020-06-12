package iCarePatients;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/SaveDoctor")  
public class SaveDoctor extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        String fname= request.getParameter("fname");
        String lname= request.getParameter("lname");
        String name= fname+" "+lname;  
        String password=request.getParameter("pass");  
        String uname=request.getParameter("uname");  
        String dept=request.getParameter("dept");  
        
        System.out.println(name +" "+password+" "+ uname+" "+dept);
          
       
          
        int status=AdminDAO.save(name,password, uname,dept);  
        if(status>0){  
        	out.println("<script type=\"text/javascript\">");
        	out.println("alert('Doctor Added Successfully');");
        	out.println("location='WelcomeAdmin.jsp';");
        	out.println("</script>"); 
            request.getRequestDispatcher("WelcomeAdmin.jsp").include(request, response);  
        }else{  
        	out.print("<script><alert>Unsuccessfull attempt, Please contact iCarePatients Center.</alert></script>");   
        }  
          
        out.close();  
    }  
  
}  
