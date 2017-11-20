<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Add User</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"><h2>Enter new user:</h2></div>
        <div class="col-lg-4"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <a href="${pageContext.request.contextPath}/admin">Go to admin page</a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <form action="${pageContext.request.contextPath}/admin/new/client" method="post">
                Login:<br>
                <input type="text" name="username"><br>
                Password:<br>
                <input type="password" name="password"><br>
                Confirm password:<br>
                <input type="password" name="confirmPassword"><br><br>
                <input type="submit" value="Submit">
            </form>
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/resources/images/embrion.jpg" alt="embrion">
            </div>
        </div>
    </div>
</div>
</body>
</html>
