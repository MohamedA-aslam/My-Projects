<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>
<%
session.getAttribute("email");
%>
<html>
<head>
<title>Edit Product</title>
<style>
.back
{
  color: white;
  margin-left: 2.5%
}
</style>
</head>
<body>
<% 

String id = request.getParameter("id");
String msg = request.getParameter("msg");
if("done".equals(msg))
{
%>
<h3 class="alert">Product Updated Successfully!</h3>
<% } %>
<%
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } 
%>
 <h2><a  href="allProductEditProduct.jsp"> Back</a></h2>
<%
ResultSet rs = null;
Connection con = ConnectionProvider.getConnection();
try(Statement st = con.createStatement()){
	rs = st.executeQuery("select * from product where idproduct="+id);
    		while(rs.next())
    		{
      %> 
<form action="EditProductAction" method="post">
<input type="hidden" name="id" value="<%out.println(id);%>">
<div class="left-div">
 <h3>Enter Name</h3>
<input class="input-style"type="text" name="name" value="<%= rs.getString(2) %>" required="required">
<hr>
</div>



<div class="right-div">
<h3>Enter Price</h3>
<input class="input-style"type="number" name="price" value="<%= rs.getFloat(3) %>" required="required">
 
 
 <br>
<hr>
</div>
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


<input type="submit" name = "save">
</form>

<%
 
  %>
</body>
<br><br><br>
</body>
</html>