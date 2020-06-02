package iCare;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/SaveServlet")  
public class SaveServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        String fname= request.getParameter("fname");
        String lname= request.getParameter("lname");
        String name= fname+" "+lname;  
        String password=request.getParameter("password");  
        String email=request.getParameter("email");  
        String gender=request.getParameter("gender");  
        
        System.out.println(name +" "+password+" "+ email+" "+gender);
          
        patient e=new patient();  
        e.setName(name);  
        e.setPassword(password);  
        e.setEmail(email);  
        e.setGender(gender);  
          
        int status=PatientDao.save(e);  
        if(status>0){  
        	out.println("<script type=\"text/javascript\">");
        	out.println("alert('User Created Successfully');");
        	out.println("location='login.jsp';");
        	out.println("</script>"); 
            request.getRequestDispatcher("login.jsp").include(request, response);  
        }else{  
        	out.print("<script><alert>Unsuccessfull attempt, Please contact iCare Center.</alert></script>");   
        }  
          
        out.close();  
    }  
  
}  
