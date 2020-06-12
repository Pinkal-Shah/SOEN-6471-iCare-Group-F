<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "iCare";
String userid = "root";
String password = "root";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap core CSS -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<form action="makeAppointment" method="POST" id="appointment">

<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group">

					<select id="choose" name="choose" class="form-control" form="appointment" >



						<%
							try{
							connection = DriverManager.getConnection(connectionUrl+database, userid, password);
							statement=connection.createStatement();
							String sql ="select department,name,username from doctor_login";
							resultSet = statement.executeQuery(sql);
							while(resultSet.next()){
							%>
							
													<option
							value="<%=resultSet.getString("department")%>+<%=resultSet.getString("name")%>+<%=resultSet.getString("username")%>">Department:<%=resultSet.getString("department") %>
							|| Name: Dr.<%=resultSet.getString("name") %>
						</option>

						<%
							}
							connection.close();
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
												</select>
				</div>
			</div>
			<script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group">
					<input type="datetime-local" id="meeting-time" class="form-control"
       name="meeting-time" value=" new Date()"
       min="2018-06-07T00:00" max="2099-06-14T00:00" required="required">
					
				</div>
			</div>
			<script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group">
					<input type="submit" class="btn btn-danger" value="Validate & Confirm" />
							
				</div>
			</div>
			
		</div>
	</div>

</form>
	

</body>
</html>