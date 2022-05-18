
<%@include file = "adminHeader.jsp" %>

<html>
<head>
<title>Admin Home</title>
<style>
h1
{
color: Dark grey;
text-align: center;
font-size: 100px;
}</style>
</head>
<body>
<%
session.getAttribute("adminEmail");
%>
<h1>welcome admin!</h1>
</body>
</html>