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

/**
 * Servlet implementation class getBookingServlet
 */
@WebServlet("/getBookingServlet")
public class getBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Reached");		
		List<booking> listOfFutureBookings=PatientDao.getFutureBookingDetails();
		
		for(booking e:listOfFutureBookings){
			System.out.println("Future Appointments");
			System.out.print(e.getId()+" ");
			System.out.print(e.getDepartment()+" ");
			System.out.print(e.getName()+" ");
			System.out.println(e.getDateTime()+" ");
		}
		
		List<booking> listOfPastBookings=PatientDao.getPastBookingDetails();
		
		System.out.println("Past Appointments");
		List<String> listOfPastBooking=new ArrayList<String>();
		
		for(booking e:listOfPastBookings){
			
			System.out.print(e.getId()+" ");
			//listOfPastBooking.add(String.valueOf(e.getId()));
			System.out.print(e.getDepartment()+" ");
			//listOfPastBooking.add(String.valueOf(e.getId()));
			System.out.print(e.getName()+" ");
			//listOfPastBooking.add(String.valueOf(e.getId()));
			System.out.println(e.getDateTime()+" ");
			//listOfPastBooking.add(String.valueOf(e.getId()));
		}
		
		
		ArrayList myList = new ArrayList(listOfPastBookings);
		for(int i=0; i< myList.size();i++)
		{
			System.out.println(myList.get(i));
		}
		
		
	
		request.setAttribute("listOfPastBooking", listOfPastBooking); 
		RequestDispatcher rd= request.getRequestDispatcher("/WebContent/Welcome.jsp");
		rd.forward(request, response);

			
		}
	}


