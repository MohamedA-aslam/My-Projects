<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<style>

body  
{  
    margin: 0;  
    padding: 0;  
    background-color:#6abadeba;  
    font-family: 'Arial';  
}  

.button{

margin-left:auto;
  margin-right:auto;

}

.login{  
        width: 382px;  
        overflow: hidden;  
        margin: auto;  
        margin: 20 0 0 450px;  
        padding: 80px;  
        background: #23463f;  
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
   
}  
span{  
    color: white;  
    font-size: 17px;  
}  
 

a:link, a:visited {
  background-color: #277582;
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a:hover, a:active {
  background-color: #23463f;
}

#outer { width: 600px; }

</style>

</head>
<body><br><br><h2>Online Super Market </h2><hr><br><br>

  <div class='login'>
  	<form action="LoginAction" method="post">
  	
     	<input autocomplete="off" type="email" name="email" placeholder="Enter Your Email Address" class="form-control" required="required">
   		<br>
   
   		<input  autocomplete="off" type="password" name="password" placeholder="Enter Your Password" class="form-control" required="required">
   		<br>
   		<input type="submit" class="btn btn-success" name = "Login">
   	</form>
   	</div>
   	<br>
   <center>	<div id="outer"> 
     <a href="register.jsp">SignUp</a>
      
      <a href="forgotPassword.jsp">Forgot Password?</a>
       </div>
  </center>
  <div >
  
  <%
  String msg = request.getParameter("msg");
  if("notexist".equals(msg))
  {
  %>
  <h2>Incorrect Username or Password</h2>
<% } %>
<%
if("invalid".equals(msg))
{
%>
<h2>Some thing Went Wrong! Try Again !</h2>
<% } %>
    
</div>
<script>
$(window).bind("pageshow", function() {
    var form = $('form'); 
    // let the browser natively reset defaults
    form[0].reset();
});</script>
</body>
</html>