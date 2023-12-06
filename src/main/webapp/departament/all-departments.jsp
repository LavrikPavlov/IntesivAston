<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Departments</title>
</head>
<body>
    <h1>All Departments</h1>

    <!-- Отображение списка отделов -->
    <ul>
        <c:forEach var="department" items="${departments}">
            <li>
                 <a href="${pageContext.request.contextPath}/department?id=${department.id}">
                    ${department.nameDepart}
                </a>
            </li>
        </c:forEach>
    </ul>

    <!-- Форма для создания нового отдела -->
    <h2>Create New Department</h2>
    <form action="${pageContext.request.contextPath}/departments" method="post">
        <label for="department">Department:</label>
        <input type="text" id="department" name="department" required>
        <button type="submit">Create Department</button>
    </form>
    <a href="${pageContext.request.contextPath}/workers">Back to Workers</a>
</body>
</html>