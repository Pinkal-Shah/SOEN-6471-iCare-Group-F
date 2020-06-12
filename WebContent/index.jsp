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
					<img src="images/reg-Form.jpg" alt="">
				</div>
				<form action="SaveServlet" method="POST">
					<h3>Registration Form</h3>
					<div class="form-group">
						<input type="text" id="fname" name="fname" placeholder="First Name" class="form-control">
						<input type="text" id="lname" name="lname" placeholder="Last Name" class="form-control">
					</div>

					<div class="form-wrapper">
						<input type="text" name="email" id="email" placeholder="Email Address" class="form-control">
						<i class="zmdi zmdi-email"></i>
					</div>
					<div class="form-wrapper">
						<select name="gender" id="gender" id="" class="form-control">
							<option value="" disabled selected>Gender</option>
							<option value="male">Male</option>
							<option value="femal">Female</option>
							<option value="other">Other</option>
						</select>
						<i class="zmdi zmdi-caret-down" style="font-size: 17px"></i>
					</div>
					<div class="form-wrapper">
						<input type="password" id="password" name="password" placeholder="Password" class="form-control">
						<i class="zmdi zmdi-lock"></i>
					</div>
					<div class="form-wrapper">
						<input type="password"  placeholder="Confirm Password" class="form-control">
						<i class="zmdi zmdi-lock"></i>
					</div>
					<button>Register
						<i class="zmdi zmdi-arrow-right"></i>
					</button>
				</form>
				<a href="login.jsp" >Back</a>
			</div>
		</div>
		
	</body>
</html>