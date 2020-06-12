package iCarePatients;

import java.io.IOException;  
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  
@WebServlet("/LoginServelet")  
public class LoginServelet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        String n=request.getParameter("email"); 
        String p=request.getParameter("password");  
        
        System.out.println("Login Servelet");
              
        if(PatientDao.validate(n, p)){ 
        	String uname= PatientDao.getUname(n, p);
        	String id= PatientDao.getId(n, p);
        	System.out.println(uname);
        	request.setAttribute("uname",uname);
        	
        	System.out.println(id);
        	//request.setAttribute("id",id);
        	//RequestDispatcher rd1 = request.getRequestDispatcher("makeAppointment");4
        	//rd1.forward(request,response);
        	
        	
        	ServletContext application = getServletConfig().getServletContext();   
        	application.setAttribute("id", id);  
        	
        	
        	System.out.println("Login Successfull");
        	HttpSession session = request.getSession();
        	session.setAttribute("pid",id);
            RequestDispatcher rd=request.getRequestDispatcher("Welcome.jsp");  
            rd.forward(request,response);  
        }  
        else{  
        	 System.out.println("Wrong Crednetials");
        	out.println("<script type=\"text/javascript\">");
        	out.println("alert('Wrong Credentials');");
        	out.println("location='login.jsp';");
        	out.println("</script>"); 
        }  
              
        out.close();  
        }  
    }  
        
         
