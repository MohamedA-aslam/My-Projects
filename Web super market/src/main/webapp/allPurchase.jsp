<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Purchases</title>
<style>

h3
{
	color: #36454F ;
	text-align: center;
}
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
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
 


<table id="customers">
        <thead>
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">price</th>     
            <th scope="col">Quantity </th> 
            <th scope="col">Total </th>
            <th scope="col">Address </th>
            <th scope="col">Bill Time </th>
            <th scope="col">Status </th>   
          </tr>
        </thead>
        <tbody>
      <%ResultSet rs = null;
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
    		rs = st.executeQuery("select * from bill");
    		while(rs.next())
    		{
 
      %> 
       
          <tr>
            <td><%= rs.getString(2) %></td>
            <td><%= rs.getInt(3) %></td>
            <td><%= rs.getString(4) %></td>
            <td><%= rs.getFloat(5) %></td>
            <td><%= rs.getInt(6) %></td>
            <td><%= rs.getFloat(7) %></td>
            <td><%= rs.getString(8) %></td>
            <td><%= rs.getString(9) %></td>
            <td><%= rs.getString(10) %></td>
      		<td><button onclick="location.href='ReachedAction?sNo=<%= rs.getString(1)%>&currpage=reached'" > Reached </button>
          </tr>
         <%
    		}
      }catch(Exception e){System.out.println(e+" allpurchase");}
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