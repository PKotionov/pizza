<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Choose pizza!</title>
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
        <div class="col-lg-4"><h2>Choose pizza:</h2></div>
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
                    <td>Quantity of pizza</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${items}">
                <thead>
                <tr>
                    <td>${item.id}</td>
                    <td>${item.itemName}</td>
                    <td>${item.diameter}</td>
                    <td>${item.price}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/user/add/order" method="post">
                            <input type="hidden" name="id" value="${item.id}">
                            <input type="text" name="quantity">
                            <input type="submit" class="btn" value="Add">
                        </form>
                    </td>
                    </c:forEach>
                    <td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-lg-2"></div>
        <form action="${pageContext.request.contextPath}/user/orders" method="get">
            <input type="submit" class="btn" value="Go to basket">
        </form>
    </div>
    <div class="row">
        <div class="col-lg-8"></div>
        <div class="col-lg-4">
            <form action="${pageContext.request.contextPath}/user/orders" method="get">
                <input type="submit" class="btn" value="Go to basket">
            </form>
        </div>
    </div>
</div>
</body>
</html>
