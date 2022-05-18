
<!DOCTYPE html>
<html>
<head>
<title>Sign-up</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<style >

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
</style>

</head>
<body>
<br><br><h2>Online Super Market </h2><hr><br><br>

  
  <div class='login'>
   	<form action="SignupAction" method="post">
   	<input type="text" name="name" placeholder="Enter Your Name" class="form-control" required="required"><br>
   	<input type="tel" name="phoneNumber" placeholder="Enter Your Phone Number" class="form-control" pattern="[6-9]{1}[0-9]{9}" required="required"><br>
   	<input type="email" name="email" placeholder="Enter Your Email Address"  class="form-control" required="required"><br>
   	<input type="password" name="initialPassword" id="txtPassword" placeholder="Set Your Password" class="form-control" required="required"><br>
   	<input type="password" name="password" id="txtConfirmPassword" placeholder="confirm Your Password" class="form-control"  required="required"><br>
   	
   	<input type="submit" class="btn btn-success" onclick="return Validate()" value="signup">
   	</form> 
 	<script type="text/javascript">
    function Validate() {
        var password = document.getElementById("txtPassword").value;
        var confirmPassword = document.getElementById("txtConfirmPassword").value;
        if (password != confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>

  </div>
    <center> <div> <h2><a href="login.jsp">Login</a></h2> </div></center>
  <div >
<%
String msg = request.getParameter("msg");
if("invalid".equals(msg))
{
%>
<h2>Some thing Went Wrong! Try Again !</h2>
<%}

if("wrongno".equals(msg))
{
%>
<h2> match your password !</h2>
<%} %>
    </div>

</body>
</html>