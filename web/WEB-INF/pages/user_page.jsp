<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<html>
<head>
    <title>User page</title>
    <style>
        body {
            background: url("/resources/images/pitstsa.jpg");
            color: #000000;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"><h2>User page</h2></div>
        <div class="col-lg-4"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
         <form action="${pageContext.request.contextPath}/user/confirm/order" method="get">
          <button type="submit" class="btn">Show orders</button>
          </form>
        </div>
        <div class="col-lg-4"></div>
    </div>
     <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
            <form action="${pageContext.request.contextPath}/user/items" method="get">
           <button type="submit" class="btn">Make order</button>
            </form>
            </div>
            <div class="col-lg-4"></div>
        </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <form action="${pageContext.request.contextPath}/user/cards" method="get">
                <button type="submit" class="btn">Credit cards</button>
            </form>
        </div>
        <div class="col-lg-4"></div>
    </div>
</div>
</body>
</html>
