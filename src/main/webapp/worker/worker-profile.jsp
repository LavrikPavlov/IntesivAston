<%@ page import="ru.aston.models.Worker, ru.aston.models.Department, ru.aston.models.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Worker Profile</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            // Обработчик события при выборе задания
            $("#taskId").change(function () {
                var selectedTaskId = $(this).val();
                console.log("Selected Task ID: " + selectedTaskId);
            });

            // Обработчик события при выборе отдела
            $("#departmentId").change(function () {
                var selectedDepartmentId = $(this).val();
                console.log("Selected Department ID: " + selectedDepartmentId);
            });
        });
    </script>
</head>
<body>

<c:choose>
    <c:when test="${empty worker}">
        <p>Invalid worker ID or worker not found.</p>
    </c:when>
    <c:otherwise>
        <h1>Worker Profile</h1>
        <p>ID: ${worker.id}</p>
        <p>Login: ${worker.login}</p>
        <p>Name: ${worker.nameWorker}</p>
        <p>Department: ${worker.department.nameDepart}</p>

        <h2>Tasks</h2>
        <c:forEach var="task" items="${worker.tasks}">
            <p>${task.nameTask}</p>
        </c:forEach>

        <h2>Update Profile</h2>
        <form id="updateProfileForm" action="${pageContext.request.contextPath}/profile" method="post">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${worker.nameWorker}">
            <button type="button" onclick="performAction('updateWorker')">Update Name</button>
            <br>

            <label for="department">Select Department:</label>
            <select id="department" name="department">
                <c:forEach var="department" items="${allDepartments}">
                    <option value="${department.id}">${department.nameDepart}</option>
                </c:forEach>
            </select>
            <button type="button" onclick="performAction('changeDepart')">Change Department</button>
            <br>

            <label for="taskId">Select Task:</label>
            <select id="taskId" name="taskId">
                <c:forEach var="task" items="${allTasks}">
                    <option value="${task.id}">${task.nameTask}</option>
                </c:forEach>
            </select>
            <button type="button" onclick="performAction('assignTask')">Add Task</button>
            <button type="button" onclick="performAction('removeTask')">Remove Task</button>
            <br>

            <input type="hidden" name="id" value="${worker.id}">
            <input type="hidden" name="action" id="action" value="">
            <button type="button" onclick="performAction('deleteWorker')">Delete Worker</button>
        </form>

        <script>
            function performAction(action) {
                $("#action").val(action);
                $("#updateProfileForm").submit();
            }
        </script>
    </c:otherwise>
</c:choose>
</body>
</html>