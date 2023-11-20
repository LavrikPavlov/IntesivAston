<%@ page import="ru.aston.models.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Task Details</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script>
            $(document).ready(function () {
                // Обработчик события при изменении текста задачи
                $("#taskText").change(function () {
                    var selectedTaskText = $(this).val();
                    console.log("Selected Task Text: " + selectedTaskText);
                });

                // Обработчик события при изменении названия задачи
                $("#taskName").change(function () {
                    var selectedTaskName = $(this).val();
                    console.log("Selected Task Name: " + selectedTaskName);
                });
            });
    </script>
</head>
<body>

<c:choose>
    <c:when test="${empty Task}">
        <p>Invalid task ID or task not found.</p>
    </c:when>
    <c:otherwise>
        <h1>Task Details</h1>
        <p>ID: ${Task.id}</p>
        <p>Name: ${Task.nameTask}</p>
        <p>Text: ${Task.taskText}</p>
         <h2>Сотрудники работающие на задаче</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Login</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="worker" items="${Task.workers}">
                            <tr>
                                <td>${worker.id}</td>
                                <td><a href="${pageContext.request.contextPath}/profile?id=${worker.id}">${worker.login}</a></td>
                                <td>${worker.nameWorker}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

        <h2>Update Task</h2>
        <form id="updateTaskForm" action="${pageContext.request.contextPath}/task" method="post">
            <label for="taskName">New Task Name:</label>
            <input type="text" id="taskName" name="taskName" value="${Task.nameTask}">
            <br>
            <label for="taskText">New Task Text:</label>
            <textarea id="taskText" name="taskText" rows="4" cols="50">${Task.taskText}</textarea>
            <br>
            <button type="button" onclick="performAction('updateTask')">Update Task</button>

            <input type="hidden" name="id" value="${Task.id}">
            <input type="hidden" name="action" id="action" value="updateTask">
        </form>

        <h2>Delete Task</h2>
        <form id="deleteTaskForm" action="${pageContext.request.contextPath}/task" method="post">
            <button type="button" onclick="performAction('deleteTask')">Delete Task</button>
            <input type="hidden" name="id" value="${Task.id}">
            <input type="hidden" name="action" id="actionDelete" value="deleteTask">
        </form>

        <a href="${pageContext.request.contextPath}/tasks">Back to Tasks</a>

        <script>
            function performAction(action) {
                $("#action").val(action);
                $("#updateTaskForm").submit();
            }
        </script>
    </c:otherwise>
</c:choose>

</body>
</html>