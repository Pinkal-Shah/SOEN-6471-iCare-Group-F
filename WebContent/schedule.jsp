<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule</title>
</head>
<body>
<h1>Reached in Schedule.JSP </h1>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%> 
<table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Date </b></th> 
          <th><b>Time</b></th> 
          <th><b>Patient Name</b></th>
          <th><b>Booking ID</b></th> 
<%
	ArrayList<String> arr = (ArrayList<String>) request.getAttribute("schedule");
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
	
	for(int i=0;i<arr.size()-3;i++){%>

	<tr>
		<td><%=arr.get(i)%></td>
		<td><%=arr.get(i+1)%></td>
		<td><%=arr.get(i+2)%></td>
		<td><%=arr.get(i+3)%></td>
	</tr>
	
<%}%>

</table>

<form action="UpdateServlet" method="post">
	<button>Close Appointment</button>
</form>

	
</body>
</html>