<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 07.09.2019
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group list</title>
</head>
<body>
<%@include file="header.jsp"%>

<div class="container">
    <h2>Group list</h2>
    <table style border="border-collapse: collapse" width="50%">
        <tr>
            <th>Group name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${groups}" var="group" varStatus="status">
            <tr>
                <td>${group.name}</td>
                <td>
                    <a href='<c:url value="/group/users?id=${group.id}"/>'>Users</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
