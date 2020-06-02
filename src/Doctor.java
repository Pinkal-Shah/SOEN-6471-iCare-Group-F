

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Doctor
 */
@WebServlet("/Doctor")
public class Doctor extends HttpServlet {
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost:3306";
	  final private String user = "root";
	  final private String passwd = "admin";
	  
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public Doctor() {
      super();
      // TODO Auto-generated constructor stub
  }
  

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
		Class.forName("com.mysql.jdbc.Driver");
	      
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://" + host + "/doctor?"
	              + "user=" + user + "&password=" + passwd );

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from doctor.doctor_login");
	      
	      while (resultSet.next()) {
	    	  if(resultSet.getString("username")==username) {
	    		  if(resultSet.getString("password")==password) {
	    			  response.sendRedirect("home.jsp");
	    		  }
	    	  }
	      }
	      
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      close();
	    }
	
  }
  
  private void close() {
      try {
        if (resultSet != null) {
          resultSet.close();
        }

        if (statement != null) {
          statement.close();
        }

        if (connect != null) {
          connect.close();
        }
      } catch (Exception e) {

      }
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.isEmpty() || password.isEmpty()) {
		   showLoginForm(request, response);
		}
		else {
		processRequest(request,response);
	}

}
	
	protected void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  out.println("<html>");
		  out.println("<head>");
		  out.println("<title>Login</title>");
		  out.println("</head>");
		  out.println("<body>");
		  out.println("<br>Please enter username and password");
		  out.println("<form method=post>");
		  out.println("<br>Username: <input type=text name=username>");
		  out.println("<br>Password: <input type=text name=password>");
		  out.println("<br><input type=submit>");
		  out.println("</form>");
		  out.println("</body>");
		  out.println("</html>");
	}
}