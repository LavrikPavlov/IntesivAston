<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Address Page</title>
</head>
<body>
    <h2>Address List</h2>
    <ul>
        <c:forEach var="address" items="${addressList}">
            <li>${address.getAddressFull()}</li>
        </c:forEach>
    </ul>

    <h2>Create New Address</h2>
    <form action="${pageContext.request.contextPath}/addressServlet" method="post">
        <label for="addressFull">Address Full:</label>
        <input type="text" id="addressFull" name="addressFull" required><br>

        <label for="personId">Person ID:</label>
        <input type="text" id="personId" name="personId"><br>

        <button type="submit">Create Address</button>
    </form>

    <%-- Display the result message from the servlet --%>
    <div>${resultMessage}</div>
</body>
</html>