<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 07.09.2019
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solution Details</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h2>Solution details</h2>
    <br>
    <h3>Name of exercise: </h3>
    <p>${exercise.title}</p>
    <h3>Solution: </h3>
    <p>${solution.description}</p>
    <h3>User: </h3>
    <p>${user.username}</p>
    <h3>Created:</h3>
    <p>${solution.created}</p>
    <h3>Updated:</h3>
    <p>${solution.updated}</p>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
