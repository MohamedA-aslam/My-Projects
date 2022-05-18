<%@include file = "header.jsp" %>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Message Us</title>
</head>
<body>

<div class="container">
  <form action="MessageUsAction">

    <label for="fname">First Name</label>
    <input type="text" id="fname" name="firstname" class="form-control" placeholder="Your name..">

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="lastname" class="form-control" placeholder="Your last name..">

    <label for="country">Country</label>
    <input type="text" id="country" name="country" class="form-control" placeholder="Your Country..">
    
    <label for="subject">Subject</label>
    <textarea id="subject" name="subject" class="form-control" placeholder="Write something.." style="height:200px"></textarea>
	<br>
    <input type="submit" class="btn btn-success" value="Submit">

  </form>
</div>
</body>
</html>