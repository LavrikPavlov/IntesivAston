<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Management List</title>
</head>
<body>
    <h2>Management List</h2>
    <ul>
        <c:forEach var="manager" items="${managerList}">
            <li>${manager.getNameCompany()}</li>
        </c:forEach>
    </ul>
    <hr>
    <h2>Create New Manager</h2>
    <form action="${pageContext.request.contextPath}/management" method="post">
        <label for="nameCompany">Name Company:</label>
        <input type="text" id="nameCompany" name="nameCompany" required><br>

        <label for="addressFull">Address Full:</label>
        <input type="text" id="addressFull" name="addressFull" required><br>

        <button type="submit">Create Manager</button>
    </form>
</body>
</html>