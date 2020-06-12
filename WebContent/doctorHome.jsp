<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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

<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function callServlet() {    	
        document.forms[0].action = "UpdateServlet";
        document.forms[0].submit();      
    }
</script>
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
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="./">Welcome!<span
									class="sr-only"></span></a></li>
							<li class="active"><a href="./">Logout <span
									class="sr-only"></span></a></li>
						</ul>
					</div>					
				</div>				
			</nav>
			
			<div class="jumbotron"
				style="box-shadow: 5px 10px 18px #888888; background-color: white">
				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="tab" href="#home">Today's Appointments</a></li>
					<li><a data-toggle="tab" href="#menu1">Future Appointments</a></li>
				</ul>
				<hr>				
				<div class="tab-content">					
					<div id="home" class="tab-pane fade in active"
						style="overflow: scroll">
						<form action="UpdateServlet" method="post">
						<%@page import="java.util.ArrayList"%>
						<%@page import="java.util.Iterator"%> 
						<h3>Upcoming Appointments</h3>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Date</th>
									<th>Time</th>
									<th>Patient Name</th>
									<th>Booking ID</th>
									<th>Select</th>
								</tr>
							</thead>
							<tbody>							
								<%ArrayList<String> arr = (ArrayList<String>) request.getAttribute("schedule");
								String date[] = new String[arr.size()];
								String time[] = new String[arr.size()];
								String patient[] = new String[arr.size()];
								String[] bid = new String[arr.size()];
								
								for(int i=0;i<arr.size()-3;i++){
									date[i] = arr.get(i);
									time[i] = arr.get(i+1);
									patient[i] = arr.get(i+2);
									bid[i] = arr.get(i+3);
								}
								
								int rowCount = 0;
								for(int i=0;i<arr.size()/4;i++){%>

								<tr>
									<td><%=arr.get(rowCount)%></td>
									<td><%=arr.get(rowCount+1)%></td>
									<td><%=arr.get(rowCount+2)%></td>
									<td><%=arr.get(rowCount+3)%></td>				
									<td><input type="radio" name="setval" value="<%=arr.get(rowCount+3)%>"/></td>
								</tr>
								<%rowCount += 4;}%>
							</tbody>
						</table>
						<br><br>						
						<div class="form-group">							
							<button id="closeApt" class="btn btn-success">Close Appointment</button>
						</div>		
						</form>										
					</div>																	
					<div id="menu1" class="tab-pane fade" style="overflow: scroll">
						<%@page import="java.util.ArrayList"%>
						<%@page import="java.util.Iterator"%> 						
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Date</th>
									<th>Time</th>
									<th>Patient Name</th>
									<th>Booking ID</th>									
								</tr>
							</thead>
							<tbody>							
								<%ArrayList<String> arr1 = (ArrayList<String>) request.getAttribute("upcomingschedule");
								String date1[] = new String[arr1.size()];
								String time1[] = new String[arr1.size()];
								String patient1[] = new String[arr1.size()];
								String[] bid1 = new String[arr1.size()];
								
								for(int i=0;i<arr1.size()-3;i++){
									date1[i] = arr1.get(i);
									time1[i] = arr1.get(i+1);
									patient1[i] = arr1.get(i+2);
									bid1[i] = arr1.get(i+3);
								}
								
								rowCount = 0;
								for(int i=0;i<arr1.size()/4;i++){%>

								<tr>
									<td><%=arr1.get(rowCount)%></td>
									<td><%=arr1.get(rowCount+1)%></td>
									<td><%=arr1.get(rowCount+2)%></td>
									<td><%=arr1.get(rowCount+3)%></td>													
								</tr>
								<%rowCount += 4;}%>
							</tbody>
						</table>
					</div>					
				</div>
			</div>
		</div>		
	</div>
</body>
</html>