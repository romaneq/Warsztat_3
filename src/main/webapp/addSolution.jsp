<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 07.09.2019
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add solution</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h2>Add new solution</h2>
    <form class="text-center" action="/solution/add" method="post">
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description"
                      placeholder="Solution description"></textarea>
        </div>
        <div class="form-group">
            <label for="chooseExercise">Exercise</label>
            <select class="form-control" id="chooseExercise" name="chooseExercise">
                <c:forEach items="${exercises}" var="exercise">
                    <option value="${exercise.id}">${exercise.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="chooseUser">User</label>
            <select class="form-control" id="chooseUser" name="chooseUser">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-color rounded-0" type="submit">Add solution</button>
    </form>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
