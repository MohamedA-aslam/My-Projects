<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
  
  .topnav input[type=text] {
    border: 1px solid #ccc;  
  }
  

</style>
<script src='https://kit.fontawesome.com/a076d05399.js'>
	</script>
</head>
 <body>
 <%  response.setHeader("Cache-Control","no-cache , no-store , must-revalidate");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
if(session.getAttribute("email")==null)
	response.sendRedirect("error.jsp");%>
    <br>
    <div class="topnav">
    
            <h2>Supreme Super Market</h2>
            <a href="home.jsp">Home<i class="fa fa-home" aria-hidden="true"></i></a>
            <a href="myCart.jsp">My Cart<i class='fas fa-cart-arrow-down'></i></a>
            <a href="changeDetails.jsp">Change Details <i class="fa fa-edit"></i></a>
            <a href="purchaseHistory.jsp">Purchase History <i class="fa fa-edit"></i></a>
            <a href="messageUs.jsp">Message Us <i class='fas fa-comment-alt'></i></a>
            
            <a href="logout.jsp">Logout <i class='fas fa-share-square'></i></a>
            
            
             <form action="searchHome.jsp"method="post">
             <button type="submit" style="float: right;padding: 6px;margin-top: 8px;"><i class="fa fa-search"></i></button>
             	<input type="text" placeholder="Search" name="search">
				
              </form>  
             
            </div>
         
           <br>
         
</body>