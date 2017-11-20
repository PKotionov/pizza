<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Shop</title>
  <style>
    body {
      background: url("/resources/images/pizza.jpg");
      color: #fff;
      text-align: center;
    }
  </style>
</head>
<body>
<form action="/login" method="post">
  Login:<br>
  <input type="text" name="username"><br>
  Password:<br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>