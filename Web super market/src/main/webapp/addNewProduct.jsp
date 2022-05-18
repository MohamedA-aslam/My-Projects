<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file = "adminHeader.jsp" %>


<html>
<head>

<title>Add New Product</title>

<style>

.login{  
        width: 382px;  
        overflow: hidden;  
        margin: auto;  
        margin: 20 0 0 450px;  
        padding: 80px;  
        background: #B7BCC4;  
        border-radius: 15px ;  
          
}  
h2{  
    text-align: center;  
    color: #277582;  
    padding: 20px;  
}  
label{  
    color: #08ffd1;  
    font-size: 17px;  
}  
#Uname{  
    width: 300px;  
    height: 30px;  
    border: none;  
    border-radius: 3px;  
    padding-left: 8px;  
}  
#Pass{  
    width: 300px;  
    height: 30px;  
    border: none;  
    border-radius: 3px;  
    padding-left: 8px;  
      
}  
#log{  
    width: 300px;  
    height: 30px;  
    border: none;  
    border-radius: 17px;  
    padding-left: 7px;  
    color: blue;  

  
} span{  
    color: white;  
    font-size: 17px;  
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
<h3 class="alert">Product Added Successfully!</h3>
<% } %>
<%
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } %>

<div class='login'>
<form action="AddNewProductAction" method="post">



 <h3>Enter Product's Name</h3>
 <input type="text" name="name" placeholder="Enter name" required="required">
 




<h3>Enter Price</h3>
 <input type="number" name="price" placeholder="Enter price" step=".01" required="required">


<input type="submit" name = "save">
</form>
</div>

   




<br><br><br>
</body>
</html>