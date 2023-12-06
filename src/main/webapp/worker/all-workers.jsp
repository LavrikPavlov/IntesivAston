<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Workers</title>
</head>
<body>
    <h1>All Workers</h1>

    <!-- Отображение списка работников в виде ссылок на их профили -->
    <table border="1">
        <thead>
            <tr>
                <th>Login</th>
                <th>Name</th>
                <th>Department</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="worker" items="${workers}">
                <tr>
                    <td>
                        <a href="<c:url value='/profile?id=${worker.id}'/>">${worker.login}</a>
                    </td>
                    <td> ${worker.nameWorker}</td>
                    <td>
                        <c:choose>
                            <c:when test="${worker.department ne null}">
                                ${worker.department.nameDepart}
                            </c:when>
                            <c:otherwise>
                                No Department
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <!-- Форма для создания нового работника -->
    <h2>Create New Worker</h2>
    <form action="${pageContext.request.contextPath}/workers" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br>

        <label for="nameWorker">Name:</label>
        <input type="text" id="nameWorker" name="nameWorker" required><br>

        <label for="department">Department:</label>
        <select id="department" name="department" required>
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}">${department.nameDepart}</option>
            </c:forEach>
        </select><br>

        <button type="submit">Create Worker</button>
    </form>
    <a href="${pageContext.request.contextPath}/departments">Departments</a>
    <a href="${pageContext.request.contextPath}/tasks">Tasks</a>
</body>
</html>