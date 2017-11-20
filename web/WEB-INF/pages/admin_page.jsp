<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<div class="row">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <img src="${pageContext.request.contextPath}/resources/images/order.jpg">
    </div>
    <div class="col-lg-4"></div>
</div>
<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"><h2>Admin page</h2></div>
        <div class="col-lg-4"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
         <form action="${pageContext.request.contextPath}/admin/order/show" method="get">
          <button type="submit" class="btn">Show orders</button>
          </form>
        </div>
        <div class="col-lg-4"></div>
    </div>
     <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
             <form action="${pageContext.request.contextPath}/admin/new/client" method="get">
              <button type="submit" class="btn">Add new user</button>
                 </form>
            </div>
            <div class="col-lg-4"></div>
        </div>
         <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
          <form action="${pageContext.request.contextPath}/admin/clients" method="get">
          <button type="submit" class="btn">Show all users</button>
            </form>
                </div>
                <div class="col-lg-4"></div>
            </div>


</div>
</body>
</html>
