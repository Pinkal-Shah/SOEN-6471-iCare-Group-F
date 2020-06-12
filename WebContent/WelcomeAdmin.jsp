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

<script>
	function ValidateDate() {

		var date = document.getElementById("meeting-time").value;
		console.log(date);
	}
</script>

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
		$.post("getBookingServlet", function(responseText) {
			$("#somediv").text(responseText);
		});

		$.post("getDepartmentDetailsServlet", function(responseText) {
			$("#somediv").text(responseText);
		});

	});

	$("#delbtn").on('click', '.btn-danger', function() {
		alert("Clicked");
		$(this).closest('tr').remove();
	});

	$(document).on('click', '#delbtn', function(e) {
		//$(this).closest('tr').remove();
		var $row = $(this).closest("tr");
		var $text = $row.find("#nr").text();
		$.post("deleteDoctor", {
			n1 : $text
		}).done(function(data) {
			alert("Deletion Success");
			location.reload();
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
					<li class="active"><a data-toggle="tab" href="#home">View
							List of Doctors</a></li>
					<li><a data-toggle="tab" href="#menu2">Add a Doctor</a></li>
				</ul>
				<hr>

				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<div class="table-wrapper-scroll-y my-custom-scrollbar">

							<table class="table table-bordered table-striped mb-0"
								id="example">
								<thead>
									<tr>

										<th scope="col">Name</th>
										<th scope="col">Department</th>
										<th scope="col">Username/Id</th>
										<th scope="col">Action</th>

									</tr>
								</thead>
								<tbody>
									<%
										try {
										//String pid = (String) session.getAttribute("pid");
										connection = DriverManager.getConnection(connectionUrl + database, userid, password);
										statement = connection.createStatement();
										String sql = "select * from doctor_login";
										resultSet = statement.executeQuery(sql);
										while (resultSet.next()) {
									%>
									<tr>
										<td><%=resultSet.getString("name")%></td>
										<td><%=resultSet.getString("department")%></td>
										<td id="nr"><%=resultSet.getString("username")%></td>
										<td><button id="delbtn" class="btn-danger">Delete</button></td>

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
						<u><h3>Add new Doctor</h3></u> <br>

						<form action="SaveDoctor" method="POST" id="appointment">

							<div class="container">
								<div class="row">
									<div class='col-sm-6'>
										<div class="form-group">
											<label for="labelInputFirstName">First name</label> <input
												type="text" class="form-control" id="fname" name="fname"
												placeholder="Enter first name">
										</div>
										<div class="form-group">
											<label for="labelInputLastName">Last name</label> <input
												type="text" class="form-control" id="lname" name="lname"
												placeholder="Enter last name">
										</div>
										<div class="form-group">
											<label for="labelInputUsername">Password</label> <input
												type="text" class="form-control" id="pass" name="pass"
												placeholder="Enter username">
										</div>
										
										<div class="form-group">
											<label for="labelInputUsername">Username</label> <input
												type="text" class="form-control" id="uname" name="uname"
												placeholder="Enter username">
										</div>
										<div class="form-group">
											<div class="dropdown">
											<label >Department</label>
												<select class="form-control" id="dept" name="dept">
													<option><a href="#">Cardiology</a></option>
													<option><a href="#">Nephrology</a></option>
													<option><a href="#">Neurology</a></option>
													<option><a href="#">Oncology</a></option>
													<option><a href="#">Ophthalmology</a></option>
													<option><a href="#">Radiology</a></option>
												</select>
											</div>
										</div>
										<br><br>
										<div class="form-group">
											<input type="submit" class="btn btn-success" value="Save">
											<input type="reset" class="btn btn-info" value="Reset">
											</div>
						</form>
					</div>

				</div>
			</div>
		</div>

	</div>
	</div>


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
