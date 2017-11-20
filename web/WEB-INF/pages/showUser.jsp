<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Users list</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4"><h2>Users list:</h2></div>
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
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <table class="table">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Role</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" varStatus="i" items="${users}">
                <thead>
                <tr>
                    <td>${i.count}</td>
                    <td>${user.username}</td>
                    <td>${user.roleString}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/delete/client" method="get">
                            <input type="hidden" name="id" value="${user.id}">
                            <button type="submit" class="btn">Delete</button>
                        </form>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-3"></div>
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
            <div class="col-lg-1"></div>
            <div class="col-lg-10">
                <img src="${pageContext.request.contextPath}/resources/images/people.jpg">
            </div>
            <div class="col-lg-1"></div>
        </div>
</div>
</body>
</html>
