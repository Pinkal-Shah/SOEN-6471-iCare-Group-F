<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

</head>
<body>
<h1> WELCOME Dr.<%String username = (String) request.getAttribute("name"); %> </h1>
<form action="DoctorSchedule" method ="post">
<label for="Select Date: "> Date  :</label>
  <input type="date" id="datetime" name="datetime">
  <input type="submit">
</form>

</body>
</html>