<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Product</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}

#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 70%;
   margin-left: auto; 
  margin-right: auto;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}

#customers tr:hover {
  background-color: B7BCC4;
}

</style>
</head>
<body>
<%
session.getAttribute("adminEmail");
%>
 <%
  String msg = request.getParameter("msg");
  if("done".equals(msg))
  {
  %>
<h3 class="alert">Product Edited Successfully!</h3>
<% } %>
<%
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } %>


<table id="customers">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col"><i class="fa fa-inr"></i> Price</th>
            
            <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
          </tr>
        </thead>
        <tbody>
      <%ResultSet rs = null;
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
    		 rs = st.executeQuery("select * from product");
    		while(rs.next())
    		{
      %> 
       
          <tr>
            <td><%= rs.getInt(1) %></td>
            <td><%= rs.getString(2) %></td>
            <td><i class="fa fa-inr"><%= rs.getFloat(3) %></i></td>
            <td><a href="editProduct.jsp?id=<%= rs.getFloat(1) %>">Edit <i class='fas fa-pen-fancy'></i></a></td>
          </tr>
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
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>