<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Orders:</title>
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
        <div class="col-lg-4"><h2>Make new order:</h2></div>
        <div class="col-lg-4"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <a href="${pageContext.request.contextPath}/user">Go to user page</a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
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
                <c:forEach var="orders" items="${orders}">
                <thead>
                <tr>
                    <td>${orders.id+1}</td>
                    <td>${orders.itemName}</td>
                    <td>${orders.diameter}</td>
                    <td>${orders.price}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/order/quantity?id=${orders.id}&quantity=${orders.quantity-1}">-</a>
                    ${orders.quantity}
                        <a href="${pageContext.request.contextPath}/user/order/quantity?id=${orders.id}&quantity=${orders.quantity+1}">+</a>
                    </td>
                    <td>${orders.sumPrice}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/user/delete/order" method="get">
                            <input type="hidden" name="id" value="${orders.id}">
                            <input type="submit" class="btn" value="Delete">
                        </form>
                    </td>
                    <td>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-2">
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"><h2>Sum price of order: <c:out value="${sum}"/></h2></div>
        <div class="col-lg-4"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-3">
            <form action="${pageContext.request.contextPath}/user/confirm/order" method="get">
                <input type="submit" class="btn" value="Confirm order">
            </form>
        </div>
        <div class="col-lg-5">
        </div>
    </div>
</div>
</body>
</html>
