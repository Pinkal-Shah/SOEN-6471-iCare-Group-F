<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" import="java.util.*"%>

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

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<link rel="canonical"
	href="https://getbootstrap.com/docs/3.4/examples/navbar/">

<title>iCare- Welcome</title>

<!-- Bootstrap core CSS -->
<link
	href="https://getbootstrap.com/docs/3.4/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="https://getbootstrap.com/docs/3.4/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="navbar.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.my-custom-scrollbar {
position: relative;
height: 200px;
overflow: auto;
}
.table-wrapper-scroll-y {
display: block;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		//alert({listOfPastBooking});
		$.post("getBookingServlet", function(responseText) {
			$("#somediv").text(responseText);
		});

		$.post("getDepartmentDetailsServlet", function(responseText) {
			$("#somediv").text(responseText);
		});

	});

	function deptSelChange() {
		// alert("Hello");
		console.log("doctor sent")
		var value = document.getElementById('selDepartment').value;
		console.log(value);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var data = xhr.responseText;
				//alert(data);
			}
		}
		xhr.open('POST',
				'${pageContext.request.contextPath}/getDoctors?department='
						+ value, true);
		xhr.send(value);

		// self.location.reload(true);///
	}
</script>
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div
		style="background-image: url('images/3.png'); min-height: 100vh; background-size: cover; background-repeat: no-repeat; display: flex;">
		<div class="container">


			<!-- Static navbar -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">iCare</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Home</a></li>

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Dropdown <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">Edit Profile</a></li>
									<li><a href="#">Withdraw Profile</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="./">Welcome, ${uname}<span
									class="sr-only"></span></a></li>
							<li class="active"><a href="./">Logout <span
									class="sr-only"></span></a></li>

						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
				<!--/.container-fluid -->
			</nav>

			<!-- Main component for a primary marketing message or call to action -->
			<div class="jumbotron"
				style="box-shadow: 5px 10px 18px #888888; background-color: white">
				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="tab" href="#home">Up-Coming
							Appointments</a></li>
					<li><a data-toggle="tab" href="#menu1">Past Appointments</a></li>
					<li><a data-toggle="tab" href="#menu2">Make a new
							Appointment</a></li>
				</ul>
				<hr>

				<div class="tab-content">
					<div id="home" class="tab-pane fade in active"
						>
						<h3>Future Appointments</h3>

						<!-- <iframe src="SeeFutureBookings.jsp" title="Future Bookings"
							style="position: relative; height: 100%; width: 100%;"
							frameBorder="0"></iframe> -->

						<%-- <table class="table">
							<tr>
								<td>Id</td>
								<td>Department</td>
								<td>Name</td>
								<td>Date/Time</td>

							</tr>
							<%
								try {

								//ServletContext application1 = getServletConfig().getServletContext(); 
								String pid = (String) session.getAttribute("pid");

								//String pid="1";	
								connection = DriverManager.getConnection(connectionUrl + database, userid, password);
								statement = connection.createStatement();

								String sql = "select bid,dtime,name,department  from booking INNER JOIN doctor_login ON booking.username= doctor_login.username WHERE dtime >= NOW() AND PID = '"
								+ pid + "'";
								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
							%>
							<tr>
								<td><%=resultSet.getString("bid")%></td>
								<td><%=resultSet.getString("department")%></td>
								<td><%=resultSet.getString("name")%></td>
								<td><%=resultSet.getString("dtime")%></td>
							</tr>
							<%
								}
							connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							%>
						</table> --%>
						
						
						
						
						
						<div class="table-wrapper-scroll-y my-custom-scrollbar">

							<table class="table table-bordered table-striped mb-0">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Department</th>
										<th scope="col">Name</th>
										<th scope="col">Date- Time</th>
									</tr>
								</thead>
								<tbody>
									<%
								try {
								String pid = (String) session.getAttribute("pid");
								connection = DriverManager.getConnection(connectionUrl + database, userid, password);
								statement = connection.createStatement();
								String sql = "select bid,dtime,name,department  from booking INNER JOIN doctor_login ON booking.username= doctor_login.username WHERE dtime >= NOW() AND PID = '"
										+ pid + "'";
								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
							%>
							<tr>
								<td><%=resultSet.getString("bid")%></td>
								<td><%=resultSet.getString("department")%></td>
								<td><%=resultSet.getString("name")%></td>
								<td><%=resultSet.getString("dtime")%></td>
							</tr>
							<%
								}
							connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							%>
									
								</tbody>
							</table>

						</div>
						
						
						
						
						
						

					</div>


					<div id="menu1" class="tab-pane fade" style="overflow: scroll">
						<h3>Past Appointments</h3>
						<!-- <iframe src="SeePastBookings.jsp" title="Past Bookings"
							style="position: relative; height: 100%; width: 100%;"
							frameBorder="0"></iframe> -->



						<div class="table-wrapper-scroll-y my-custom-scrollbar">

							<table class="table table-bordered table-striped mb-0">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Department</th>
										<th scope="col">Name</th>
										<th scope="col">Date- Time</th>
									</tr>
								</thead>
								<tbody>
									<%
								try {
								String pid = (String) session.getAttribute("pid");
								connection = DriverManager.getConnection(connectionUrl + database, userid, password);
								statement = connection.createStatement();
								String sql = "select bid,dtime,name,department  from booking INNER JOIN doctor_login ON booking.username= doctor_login.username WHERE dtime < NOW() AND PID = '"
								+ pid + "'";
								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
							%>
							<tr>
								<td><%=resultSet.getString("bid")%></td>
								<td><%=resultSet.getString("department")%></td>
								<td><%=resultSet.getString("name")%></td>
								<td><%=resultSet.getString("dtime")%></td>
							</tr>
							<%
								}
							connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							%>
									
								</tbody>
							</table>

						</div>

					</div>


					<div id="menu2" class="tab-pane fade">
						<u><h3>Make new Appointments</h3></u> <br>

						<form action="makeAppointment" method="POST" id="appointment">

							<div class="container">
								<div class="row">
									<div class='col-sm-6'>
										<div class="form-group">

											<select id="choose" name="choose" class="form-control"
												form="appointment">



												<%
													try {
													connection = DriverManager.getConnection(connectionUrl + database, userid, password);
													statement = connection.createStatement();
													String sql = "select department,name,username from doctor_login";
													resultSet = statement.executeQuery(sql);
													while (resultSet.next()) {
												%>

												<option
													value="<%=resultSet.getString("department")%>+<%=resultSet.getString("name")%>+<%=resultSet.getString("username")%>">Department:<%=resultSet.getString("department")%>
													|| Name: Dr.<%=resultSet.getString("name")%>
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
										$(function() {
											$('#datetimepicker1')
													.datetimepicker();
										});
									</script>
								</div>
							</div>

							<div class="container">
								<div class="row">
									<div class='col-sm-6'>
										<div class="form-group">
											<input type="datetime-local" id="meeting-time"
												class="form-control" name="meeting-time" value=" new Date()"
												min="2018-06-07T00:00" max="2099-06-14T00:00"
												required="required">

										</div>
									</div>
									<script type="text/javascript">
										$(function() {
											$('#datetimepicker1')
													.datetimepicker();
										});
									</script>
								</div>
							</div>

							<div class="container">
								<div class="row">
									<div class='col-sm-6'>
										<div class="form-group">
											<input type="submit" class="btn btn-danger"
												value="Validate & Confirm" />

										</div>
									</div>

								</div>
							</div>

						</form>


					</div>
				</div>
			</div>

		</div>
		<!-- /container -->


		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"
			integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
			crossorigin="anonymous"></script>
		<script>
			window.jQuery
					|| document
							.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
		</script>
		<script src="../../dist/js/bootstrap.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	</div>
</body>
</html>
