<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Person List</title>
</head>
<body>

<h1>Person List</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${persons}">
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Create New Person</h2>
<form action="${pageContext.request.contextPath}/person" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <button type="submit">Create Person</button>
</form>

<button onclick="window.location.href='${pageContext.request.contextPath}/address'">К адресам</button>
<button onclick="window.location.href='${pageContext.request.contextPath}/management'">К управляющим компаниям</button>


</body>
</html>