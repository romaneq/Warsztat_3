<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 07.09.2019
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User details</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h2>User details</h2>
    <br>
    <h3>Username: </h3>
    <p>${user.username}</p>
    <h3>Email: </h3>
    <p>${user.email}</p>
    <h3>Group: </h3>
    <p>${group.name}</p>
    <table style border="border-collapse: collapse" width="50%">
        <tr>
            <th>Exercise</th>
            <th>Solution</th>
            <th>Created</th>
            <th>Updated</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${solutions}" var="solution" varStatus="status">
            <tr>
                <td>${exercises.get(status.index).title}</td>
                <td>${solution.description}</td>
                <td>${solution.created}</td>
                <td>${solution.updated}</td>
                <td>
                    <a href='<c:url value="/solution/details?id=${solution.id}"/>'>Details</a>
                    <a href='<c:url value="/solution/edit?id=${solution.id}"/>'>Edit</a>
                    <a href='<c:url value="/solution/delete?id=${solution.id}"/>'>Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
