<%@ page import="java.util.List" %>
<%@ page import="ru.aston.models.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Tasks</title>
</head>
<body>

<h1>All Tasks</h1>

<!-- Display all tasks -->
<c:if test="${not empty departments}">
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Text</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${departments}">
                <tr>
                    <td>${task.id}</td>
                    <td>${task.nameTask}</td>
                    <td>${task.textTask}</td>
                    <td><a href="${pageContext.request.contextPath}/task?id=${task.id}">Details</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<!-- Form to create a new task -->
<h2>Create a New Task</h2>
<form action="${pageContext.request.contextPath}/tasks" method="post">
    <label for="NameTask">Task Name:</label>
    <input type="text" id="NameTask" name="NameTask" required>
    <br>
    <label for="TaskText">Task Text:</label>
    <textarea id="TaskText" name="TaskText" rows="4" cols="50" required></textarea>
    <br>
    <button type="submit">Create Task</button>
</form>
<a href="${pageContext.request.contextPath}/workers">Back to Workers</a>

</body>
</html>