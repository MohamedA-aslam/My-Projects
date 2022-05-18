<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #04AA6D;
  color: white;
}

.topnav input[type=text] {
  float: right;
  padding: 6px;
  margin-top: 8px;
  margin-right: 6px;
  border: none;
  font-size: 17px;
}
.topnav input[type=text] {
    border: 1px solid #ccc;  
  }
  
  .topnav h2{  
    text-align: center;  
    color: white;  
    padding: 20px;  
} 
@media screen and (max-width: 600px) {
  .topnav a, .topnav input[type=text] {
    float: none;
    display: block;
    text-align: left;
    width: 100%;
    margin: 0;
    padding: 14px;
  }
  
  
  
</style>

</head>
    <!--Header-->
    <br>
    
   		<%  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); 
   		String email = session.getAttribute("adminEmail").toString();
   		%>
           
            <div class="topnav">

             <h2>Web Supermarket</h2>
            
            <a href="addNewProduct.jsp">Add New Product </a>
            <a href="allProductEditProduct.jsp">All Products and Edit Products </a>
            <a href="allcustomer.jsp">All Customer Details </a>
            <a href="allPurchase.jsp">All Purchases </a>
           	<a href="unreachedPurchase.jsp">Unreached Purchases </a>
           	<a href="customerMessages.jsp">Customer Messages </a>
           	<a href="newAdmin.jsp">Create new admin </a>
           	newAdmin.jsp
           	
            <a href="logout.jsp">Logout </a>
          </div>
           <br>
           
</html>