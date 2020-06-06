<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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

<title>Administration</title>

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
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div style="background-image: url('images/3.png'); min-height: 100vh; background-size: cover; background-repeat: no-repeat; display: flex;">
	
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
			<div class="jumbotron" style="box-shadow: 5px 10px 18px #888888; background-color: white">
			
				<div id="doctorlist" style="overflow:scroll">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%> 					
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Name</th>
								<th>Username</th>
								<th>Department</th>
							</tr>
						</thead>
						<tbody>
				<%
				ArrayList<String> arr = (ArrayList<String>) request.getAttribute("doctors");
				String name[] = new String[arr.size()];
				String username[] = new String[arr.size()];
				String department[] = new String[arr.size()];
				
				for(int i = 0; i < arr.size() - 2; i+=3){%>
					<tr>
					    <td><%=arr.get(i)%></td>
						<td><%=arr.get(i+1)%></td>
						<td><%=arr.get(i+2)%></td>
					</tr>
				<%}%>		
						</tbody>
					</table>
				</div>
			
				<br/>
				
				<div class="form-group">
					<button class="btn btn-primary" data-toggle="modal" data-target="#addDoctor">Add</button>
					<button class="btn btn-danger">Delete</button>
				</div>
				
				<!-- Modal -->
				<div class="modal fade" id="addDoctor" tabindex="0" role="dialog">
				  <div class="modal-dialog" role="document">
					<div class="modal-content">
					  <div class="modal-header">
						<h5 class="modal-title">Add a new doctor:</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						  <span aria-hidden="true">&times;</span>
						</button>
					  </div>
					  <div class="modal-body">
						<form action="AddDoctorServlet" method="POST">
						  <div class="form-group">
							<label for="labelInputFirstName">First name</label>
							<input type="text" class="form-control" id="inputFirstName" placeholder="Enter first name">
						  </div>
  						  <div class="form-group">
							<label for="labelInputLastName">Last name</label>
							<input type="text" class="form-control" id="inputLastName" placeholder="Enter last name">
						  </div>
						  <div class="form-group">
							<label for="labelInputUsername">Username</label>
							<input type="text" class="form-control" id="inputUsername" placeholder="Enter username">
						  </div>
						  <div class="form-group">
							<label for="labelInputPassword">Password</label>
							<input type="password" class="form-control" id="inputPassword" placeholder="Enter password">
						  </div>
						  <div class="form-group">
						<div class="dropdown">
						  <button class="btn btn-primary dropdown-toggle" id="department" type="button" data-toggle="dropdown">Select the department<span class="caret"></span></button>
						  <ul class="dropdown-menu">
							<li><a href="#">Cardiology</a></li>
							<li><a href="#">Nephrology</a></li>
							<li><a href="#">Neurology</a></li>
							<li><a href="#">Oncology</a></li>
							<li><a href="#">Ophthalmology</a></li>
							<li><a href="#">Radiology</a></li>
						  </ul>
						</div> 
						</form>
					  </div>
					  <div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" value="saveBtn">Save changes</button>
					  </div>
					</div>
				  </div>
				</div>
				
			</div>
			
			</div>
	
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