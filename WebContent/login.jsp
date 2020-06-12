<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Login iCare System</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>

		<div class="wrapper" style="background-image: url('images/1.png');">
			<div class="inner" style="box-shadow: 5px 10px 18px #888888">
				<div class="image-holder">
					<img src="images/2.png" alt="">
				</div>
				<form action="LoginServelet" method="POST">
					<h3>Login</h3>
					<div class="">
						<input type="email" name="email" placeholder="email" class="form-control">
						
					</div>
					<div class="">
						
						<input type="password" name="password" placeholder="password" class="form-control">
					</div>
					
					<button>Sign In
						<i class="zmdi zmdi-arrow-right"></i>
					</button>
					<br><br><br>
					<div style="text-align: center">
					<a href="index.jsp">New User? Click here to register.</a>
					</div>					
					
					<div style="text-align: center">
					<a href="indexDoctor.html">Doctor? Click here to login.</a>
					</div>					
					
					<div style="text-align: center">
					<a href="indexAdmin.html">Admin? Click here to login.</a>
					</div>
					
				</form>
				
			</div>
		</div>
		
	</body>
</html>