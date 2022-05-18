<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sort customer list</title>
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
<div style="color: white; text-align: center; font-size: 30px;">All Products & Edit Products <i class='fab fa-elementor'></i></div>
<%
session.getAttribute("adminEmail");
%>
 


<table id="customers">
        <thead>
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Total Money Spent</th>   
          </tr>
        </thead>
        <tbody>
      <%Connection con = ConnectionProvider.getConnection();
		ResultSet rs = null;
		try(Statement st = con.createStatement()){
			rs = st.executeQuery("select  email,`total money spent` from customers where `customer/admin`= 'customer' group by email order by `total money spent` desc ;");
    		while(rs.next())
    		{
 
      %> 
       
          <tr>
            <td><%= rs.getString(1) %></td>
            <td><%= rs.getString(2) %></td>
      		<td><button onclick="location.href='discount.jsp?customerEmail=<%= rs.getString(1)%>'" > give discount </button>
      		
          </tr>
         <%
    		}
      }catch(Exception e){System.out.println(e+" allcustomer");}
      finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { System.out.println(e);}
		    }
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { System.out.println(e);}
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