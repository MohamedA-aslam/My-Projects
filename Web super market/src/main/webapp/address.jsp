<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file="header.jsp"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Process</title>
</head>
<body>

<%  response.setHeader("Cache-Control","no-cache , no-store , must-revalidate");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("email")==null)
	response.sendRedirect("error.jsp");

String subtotal = request.getParameter("total");
System.out.println(subtotal);
%>

<form align="center" action="AddressAction?total=<%= subtotal %>" method = "post">
     Enter your Address <br>
    Full name (First and Last name)<br>
    <input type="text" name="fullName" /><br>
    plot number<br>
    <input type="text" name="plotNumber" /><br>
    Street name<br>
    <input type="text" name="streetName" /><br>
    City<br>
    <input type="text" name="cityName" /><br>
    State / Province / Region<br>
    <input type="text" name="state" /><br>
    PIN Code<br>
    <input type="number" name="Pincode" /><br>
    
    <input type="submit" value="submit"/>
</form>
</body>
</html>