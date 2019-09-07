<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 07.09.2019
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<%@include file="header.jsp"%>

<div class="container">
    <h2>Users that belongs to ${group.name}</h2>
    <table style border="border-collapse: collapse" width="50%">
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <a href='<c:url value="/users/details?userId=${user.id}&groupId=${group.id}"/>'>Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
