<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 07.09.2019
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h2>Recent solution list</h2>
    <h4>  <a href='<c:url value="/solution/add"/>'>Add new solution</a> </h4>
    <table style border="border-collapse: collapse" width="100%">
        <tr>
            <th>Exercise</th>
            <th>Created</th>
            <th>User</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${solutions}" var="solution" varStatus="status">
            <tr>
                <td>${exercises.get(status.index).title}</td>
                <td>${solution.created}</td>
                <td>${users.get(status.index).username}</td>
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
