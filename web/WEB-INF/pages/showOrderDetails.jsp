<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<html>
<head>
    <title>All Orders:</title>
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
        <div class="col-lg-4"><h2>Order details:</h2></div>
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
            <table class="table">
                <thead>
                <tr>
                    <td>№</td>
                    <td>Pizza</td>
                    <td>Diameter</td>
                    <td>Price</td>
                    <td>Quantity</td>
                    <td>Sum</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orders" varStatus="i" items="${orders}">
                <thead>
                <tr>
                    <td>${i.count}</td>
                    <td>${orders.itemName}</td>
                    <td>${orders.diameter}</td>
                    <td>${orders.price}</td>
                    <td>${orders.quantity}</td>
                    <td>${orders.sumPrice}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-4"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"><h2>Sum of orders: <c:out value="${sum}"/></h2></div>
        <div class="col-lg-4"></div>
    </div>
</div>
</body>
</html>
