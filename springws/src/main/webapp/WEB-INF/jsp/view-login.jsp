<html>
<body>

<h2>Login</h2>

<div>${message}</div><br/><br/>

<form action="/login" method="post">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username"><br>

  <label for="passwd">Password:</label><br>
  <input type="password" id="passwd" name="passwd"><br>

  <label for="dob">Date of Birth:</label><br>
  <input type="text" id="dob" name="dob" placeholder="yyyy-mm-dd"><br><br>

  <input type="submit" value="Login">
</form>

</body>
</html>