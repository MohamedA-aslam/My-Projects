<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>


<title>Home</title>

<style>
h3
{
	color: yellow;
	text-align: center;
}
#myDiv{
  
}
.navButton {
  color: white;
  text-decoration: none;
  font-family: Georgia, serif;
  font-size:24px;
  text-align:center;
  padding:0 30px;
  line-height: 60px;
  
  display: inline-block;
  position: fixed;
  right:    50px;
  bottom:   50px;
  border-radius:14px;
  background-image: linear-gradient(#04AA6D 45%, #048a59 55%);
  box-shadow: 0 1px 0 #888888;
  transition:color 0.3s, background-image 0.5s, ease-in-out;
}
.navButton:hover{
  background-image: linear-gradient(#b1ccda 49%, #96b4c5 51%);
  color: #03324c;
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
<% String search = request.getParameter("search"); 
int z = 0;
%>
<div style="color: white; text-align: center; font-size: 30px;">Home <i class="fa fa-institution"></i></div>
<table id="customers">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            
            <th scope="col"><i class="fa fa-inr"></i> Price</th>
            <th scope="col">Add to cart <i class='fas fa-cart-plus'></i></th>
          </tr>
        </thead>
        <tbody>
	<%Connection con = ConnectionProvider.getConnection();
		ResultSet rs = null;
		try(Statement st = con.createStatement()){
		 	rs = st.executeQuery("select * from product where productName LIKE '%"+search+"%'");
    		while(rs.next())
    		{
    			z=1;
    %> 
          <tr>
         <td><%= rs.getInt(1) %></td>
            <td><%= rs.getString(2) %></td>
            <td><i class="fa fa-inr"></i><%= rs.getFloat(3) %> </td>
            <td><a href="addToCartAction.jsp?id=<%= rs.getInt(1) %>">Add to cart <i class='fas fa-cart-plus'></i></a></td>
          </tr>
<%
    		}
      }catch(Exception e){System.out.println(e+" ---- allproduct");}
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
      	<%if(z==0){%>
	<h1 style="color:Blue; text-align: center;">Nothing to show</h1>
	<%} %>
      <br>
      <br>
      <br>
      
<div id ="myDiv">
    <button type="button" onclick="location.href='myCart.jsp'" class="navButton" >Proceed to Buy</button>
</div>
</body>
</html>