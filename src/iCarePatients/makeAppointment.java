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
@WebServlet("/makeAppointment")  
public class makeAppointment extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        //String id = (String)request.getAttribute("id");
        ServletContext application = getServletConfig().getServletContext(); 
        String pid= (String) application.getAttribute("id"); 
        System.out.println(pid);
        
        System.out.println("hula");
        String n=request.getParameter("choose");  
        

        String[] temp;
        String delimiter = "\\+";

        temp = n.split(delimiter);   
        String dept = temp[0];   
        String name = temp[1];
        String uname= temp[2];
        
        System.out.println(dept);
        System.out.println(name);
        System.out.println(uname);
        
        
        String p=request.getParameter("meeting-time");  
        String time=p.replace("T"," ");
        System.out.println(time);
        
        
        
        System.out.println("Make Appointment Servelet");
              
        if(PatientDao.validateAppointment(name,dept, time)){ 
        	
        	//Appointment not available
        	System.out.println("Appointment not Avaialable");
        	out.println("<script type=\"text/javascript\">");
        	out.println("alert('Wrong Credentials');");
        	out.println("location='Welcome.jsp';");
        	out.println("</script>");  
        	
        	 //request.getRequestDispatcher("login.jsp").include(request, response);  
        }  
        else{  
        	
        	int status= PatientDao.Appoint(pid, time, uname);
        	System.out.println(status);
        	if(status !=0)
        	{
				System.out.println("Booking Successful");
        		 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Appointment Booking Successfull');");
				// out.println("location='Welcome.jsp';");
				 out.println("</script>");
				
            	
				 request.getRequestDispatcher("Welcome.jsp").include(request, response);
            	
        	}
        	
        }  
              
        out.close();  
        }

	
		
		
	
    }  
        
         
