<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


<title>Discount Page</title>
<style>
</style>
</head>
<body>
<% 

session.getAttribute("adminEmail");
String cemail = request.getParameter("customerEmail");
String msg = request.getParameter("msg");
if("done".equals(msg))
{
%>
<h3 class="alert">Details Updated Successfully!</h3>
<% } %>
<%
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } 
%>

<%
ResultSet rs = null;
Connection con = ConnectionProvider.getConnection();
try(Statement st = con.createStatement()){
	rs = st.executeQuery("select * from customers where email='"+cemail+"'");
    		while(rs.next())
    		{
      %> 
      
    <div class="container">
<form action="discountAction" method="post">

 <h3>Customer Email</h3>
<input type="email" name="customerEmail" class="form-control" value="<%= rs.getString(1) %>" required="required">
<hr>


 

<h3>Enter dicount percetage </h3>
<input type="number" min="1" max="100" name="discount" class="form-control" value="30" required="required">

 

<h3>redemption count </h3>
<input type="number" min="1" max="100" name="redemption" class="form-control" value="3" required="required">


<%
    		}
      }catch(Exception e){System.out.println(e+" allproduct");}
finally {
	if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) { /* Ignored */}
    }
	
}
         %>

<br>
<input type="submit" class="btn btn-success" name = "save">
</form>
</div>


      <br>
      <br>
      <br>

</body>
</html>