<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="project.ConnectionProvider"%>
<%@page import="project.Encryption"%>
<%@page import="java.sql.*"%>
<%@include file = "header.jsp" %>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


<title>Change Details</title>
<style>
</style>
</head>
<body>
<% 

String email = session.getAttribute("email").toString();
String msg = request.getParameter("msg");
if("done".equals(msg))
{
%>
<h3 class="alert">Details Updated Successfully!</h3>
<% } %>
<%
if("invalid".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } 
%>

<%
ResultSet rs = null;
Connection con = ConnectionProvider.getConnection();
try(Statement st = con.createStatement()){
	rs = st.executeQuery("select * from customers where email='"+email+"'");
    		while(rs.next())
    		{
      %> 
      
    <div class="container">
<form action="ChangeDetailsAction" method="post">

 <h3>Edit your Name</h3>
<input type="text" name="newName" class="form-control" value="<%= rs.getString(2) %>" required="required">
<hr>


 

<h3>Edit your number</h3>
<input type="tel" name="newNumber" class="form-control" value="<%= rs.getString(3) %>" pattern="[6-9]{1}[0-9]{9}" required="required">
<% String hashPassword = Encryption.decrpyt(rs.getString(4)); %>
<h3>Enter your new password</h3>
<input type="password" name="firstPassword" class="form-control" id="txtPassword" value="<%= hashPassword %>" required="required"> 

<h3>confirm your new password</h3>
<input type="password" name="newPassword" class="form-control" id="txtConfirmPassword" value="<%= hashPassword %>" required="required">


<%
    		}
      }catch(Exception e){System.out.println(e+" allproduct");}

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

<br>
<input type="submit" class="btn btn-success" onclick="return Validate()" name = "save">
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


      <br>
      <br>
      <br>

</body>
</html>