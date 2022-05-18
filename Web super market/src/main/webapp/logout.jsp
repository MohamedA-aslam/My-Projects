<html>
<%
session.invalidate();
%>
<body>
<% response.sendRedirect("login.jsp"); %></body>
</html>