<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "header.jsp" %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Cart</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style>
h3
{
	color: #36454F ;
	text-align: center;
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
<body bgcolor="#E5E4E2">
<div style="color: white; text-align: center; font-size: 30px;">My Cart <i class='fas fa-cart-arrow-down'></i></div>
<% 
String email = session.getAttribute("email").toString();
String msg = request.getParameter("msg");
String  initialOffer = request.getParameter("offer");
Float offer = 0.0f;
if("done".equals(msg))
{
%>
<h3 class="alert">Product  Increased Successfully!</h3>
<% } %>
<%
if("removed".equals(msg))
{
%>
<h3 class="alert">Product  Decreased Successfully!</h3>

<%
/*
#customers th:hover::after { 
    background-color: #ffa;
    content: '\00a0';  
    height: 10000px;    
    left: 0;
    position: absolute;  
    top: -5000px;
    width: 100%;
    z-index: -1;        

}
*/


}
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } 
if("nooffer".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% }
%>
<table id="customers">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col"> Price per Item </th>
             <th scope="col">Quantity</th>
             <th scope="col">total Price for Item </th>
            
          </tr>
        </thead>
        <tbody>
		<tbody>
      <% 
      ResultSet rs = null;
      try(Connection con = ConnectionProvider.getConnection()){
      try(Statement st = con.createStatement()){
      	 rs = st.executeQuery("select * from cart where email='"+email+"'");
    		while(rs.next())
    		{
      %> 
       
          <tr>
            <td><%= rs.getInt(3) %></td>
            <td><%= rs.getString(4) %></td>
            <td><i class="fa fa-inr"></i><%= rs.getFloat(5) %> </td>
            <td><%= rs.getInt(6) %></td>
            <td><i class="fa fa-inr"></i><%= rs.getFloat(7) %> </td>
            <td><a href="AddToCartActionMYCART?id=<%= rs.getInt(3) %>"><i class="fa fa-plus" aria-hidden="true"></i> </a></td>
             <td><a href="RemoveFromCartActionMYCART?id=<%= rs.getInt(3) %>"><i class="fa fa-minus" aria-hidden="true"></i> </a></td>
          </tr>
<%
    		}
      }catch(Exception e){System.out.println(e+" content - mycart");}
      finally {
    		if (rs != null) {
    	        try {
    	            rs.close();
    	        } catch (SQLException e) { /* Ignored */}
    	    }
    		
    	}
      }catch(Exception e){System.out.println(e+" content - mycart");}
      
         %>
        </tbody>
      </table>
      <br>
      <br>
      <br>
      <% Float subtotal = 0.0f;
      try(Connection con = ConnectionProvider.getConnection()){
      try(Statement st1 = con.createStatement()){
    		try(ResultSet rs2 = st1.executeQuery("SELECT SUM(total) FROM cart where email='"+email+"'")){
    		while(rs2.next())
    		{
    			 subtotal=rs2.getFloat(1);
    		}
      }catch(Exception e){
    	  System.out.println(e);
      }
      }catch(Exception e){
    	  System.out.println(e+" total-mycart");
      }
      
      if(initialOffer==null){
    	 offer = 0.0f; 
      }
      else{
    	  offer = Float.parseFloat(initialOffer);
      }
      }
      try(Connection con = ConnectionProvider.getConnection()){
      try(Statement s = con.createStatement();){
    	 try( ResultSet r = s.executeQuery("SELECT `discount percentage`,redemption FROM customers where email='"+email+"'" )){
  		while(r.next())
  		{
  			
  		 
    	  
      %>
      <button type="button" onclick="location.href='offerAction'"> press to avail Offer: <%=r.getInt(1) %>% <br>Offers left: <%= r.getInt(2)%> </button>
      <%}
      }catch(Exception ex){
    	  System.out.println(ex+" offer button");
      }
      }catch(Exception ex){
    	  System.out.println(ex+" offer button");
      }
      }catch(Exception ex){
    	  System.out.println(ex+" offer button");
      }finally{
      
      %>
      
      <h3>Subtotal : <i class="fa fa-inr"></i> <%= subtotal %></h3>
      <div id ="myDiv">
      <% double total = subtotal + subtotal*0.0025; 
      double finalTotal = Math.round((total-subtotal*(offer/100))*100)/100.0;
      %>
      <h3>Offer Amount: <%=subtotal*(offer/100) %></h3>
      <h3>Tax : <i class="fa fa-inr"></i> <%= Math.round(((subtotal*0.0025))*100)/100.0 %></h3>
      <h3>Total Amount to be paid : <i class="fa fa-inr"></i> <%= finalTotal%> </h3>
      
    <button type="button" onclick="location.href='address.jsp?total=<%= finalTotal %>'" class="navButton" >Proceed to Next Step</button>
	<%} 
	%>
</div>
</body>
</html>