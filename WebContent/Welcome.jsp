<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="iCare.PatientScheduleDao"%> 

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

				<div class="tab-content" >
					<div id="home" class="tab-pane fade in active" style="overflow:scroll">
						<h3>Future Appointments</h3>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Firstname</th>
									<th>Lastname</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
								</tr>
								<tr>
									<td>Mary</td>
									<td>Moe</td>
									<td>mary@example.com</td>
								</tr>
								<tr>
									<td>July</td>
									<td>Dooley</td>
									<td>july@example.com</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="menu1" class="tab-pane fade" style="overflow:scroll">
						<h3>Past Appointments</h3>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Firstname</th>
									<th>Lastname</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
								</tr>
								<tr>
									<td>Mary</td>
									<td>Moe</td>
									<td>mary@example.com</td>
								</tr>
								<tr>
									<td>July</td>
									<td>Dooley</td>
									<td>july@example.com</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="menu2" class="tab-pane fade">
						<u><h3>Make new Appointments</h3></u>
						<br>
						<form action="PatientScheduleServlet" method="post">
						   <div class="form-group">
							<label for="department">Select Department</label> <select
								class="form-control" id="department">
								<%
								PatientScheduleDao pdao = new PatientScheduleDao();
									ArrayList<String> depts = pdao.GetDepartment();
								// TODO: This always returns null, it is not linking with the PatientScheduleServlet,
								// and I have no idea why
								//ArrayList<String> deptos = (ArrayList<String>) request.getAttribute("depts");
									for (int i = 0; i < depts.size(); i++){%>
									<option value="<%=depts.get(i)%>"><%=depts.get(i)%></option>

								<%}%>
								
								<input type="submit" value="Select Department">
								</form>
							</select>
						</div>
						<div class="form-group">
							<label for="doctor">Select Doctor</label> <select
								class="form-control" id="doctor">
							<option>Doctor Anne</option>
							</select>
						</div>
						<div class="form-group">
							<label for="date">Select Day</label> <select
								class="form-control" id="doctor">
							<option value="2020-06-11">06/11</option>
							<option value="2020-06-12">06/12</option>
							<option value="2020-06-13">06/13</option>
							<option value="2020-06-14">06/14</option>
							<option value="2020-06-15">06/15</option>
							</select>
						</div>
						<div class="form-group">
							<label for="timeslot">Select Time Slot</label> <input
								class="form-control" type="time" id="appt" name="appt"
								min="09:00" max="18:00" required>
						</div>

						<form action="ValidateServlet" method="post">
						<div class="form-group">
							<button class="btn btn-danger">Validate</button>
							<button class="btn btn-success">Confirm Appointment</button>

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
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="../../dist/js/bootstrap.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	</div>
</body>
</html>
