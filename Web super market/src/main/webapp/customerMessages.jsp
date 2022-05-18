<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Customer Messages</title>
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
<%
//String email = session.getAttribute("email").toString();

%>
<div style="color: white; text-align: center; font-size: 30px;">Home <i class="fa fa-institution"></i></div>




<%
String customerName="";
ResultSet rs = null;
try(Connection con = ConnectionProvider.getConnection()){
try(Statement st = con.createStatement()){
	rs = st.executeQuery("select * FROM `webshoppingapp`.`query`;");
	while(rs.next())
	{
		customerName = rs.getString(2);
	}
	}catch(Exception e){System.out.println(e+" home.jsp");}
finally {
	if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    
	
}
	}catch(Exception e){System.out.println(e+" home.jsp");}
finally {
	if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    
	
}

%>


<table id="customers">
        <thead>
          <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Country</th>
            <th scope="col">Subject</th>
            <th scope="col">Email</th>
            <th scope="col">Status</th>
          </tr>
        </thead>
        <tbody>
		<tbody>
      <%
      try(Connection con = ConnectionProvider.getConnection()){
      try(Statement st = con.createStatement()){
      	rs = st.executeQuery("select * FROM `webshoppingapp`.`query`;");
    		while(rs.next())
    		{
      %> 
       
          <tr>
            <td><%= rs.getString(1) %></td>
            <td><%= rs.getString(2) %></td>
            <td><%= rs.getString(3) %></td>
            <td><%= rs.getString(4) %></td>
            <td><%= rs.getString(6) %></td>
            <td><%= rs.getString(7) %></td>
            <td><button type="button" onclick="location.href='ReviewedAction?sno=<%= rs.getString(5) %>'"  >Reviewed</button></td>
            </tr>
<%
    		}
      }catch(Exception e){System.out.println(e+" customer messages");}
      finally {
    		if (rs != null) {
    	        try {
    	            rs.close();
    	        } catch (SQLException e) { /* Ignored */}
    	    }
    	    
    		
    	}
      }catch(Exception e){System.out.println(e+" customer messages");}
         %>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>