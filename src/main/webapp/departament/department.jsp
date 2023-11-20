<%@ page import="ru.aston.models.Department" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Department Details</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script>
            $(document).ready(function () {
                // Обработчик события при выборе отдела
                $("#nameDepart").change(function () {
                    var selectedDepartmentId = $(this).val();
                    console.log("Selected Department ID: " + selectedDepartmentId);
                });
            });
        </script>
</head>
<body>

<c:choose>
    <c:when test="${empty Department}">
        <p>Invalid department ID or department not found.</p>
    </c:when>
    <c:otherwise>
        <h1>${Department.nameDepart}</h1>

        <h2>Сотрудники в департаменте</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Login</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="worker" items="${Department.workers}">
                    <tr>
                        <td>${worker.id}</td>
                        <td><a href="${pageContext.request.contextPath}/profile?id=${worker.id}">${worker.login}</a></td>
                        <td>${worker.nameWorker}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Update Department</h2>
        <form id="updateDepartmentForm" action="${pageContext.request.contextPath}/department" method="post">
            <label for="nameDepart">New Department Name:</label>
            <input type="text" id="nameDepart" name="nameDepart" value="${Department.nameDepart}">
            <button type="button" onclick="performAction('updateDepart')">Update Department</button>
            <br>


            <input type="hidden" name="id" value="${Department.id}">
            <input type="hidden" name="action" id="action" value="updateDepart">
        </form>

        <h2>Delete Department</h2>
        <form id="deleteDepartmentForm" action="${pageContext.request.contextPath}/department" method="post">
            <button type="button" onclick="performAction('deleteDepart')">Delete Department</button>
            <input type="hidden" name="id" value="${Department.id}">
            <input type="hidden" name="action" id="actionDelete" value="deleteDepart">
        </form>

        <a href="${pageContext.request.contextPath}/department">Back to Departments</a>

        <script>
           function performAction(action) {
              $("#action").val(action);
               $("#updateDepartmentForm").submit();
           }
         </script>
    </c:otherwise>
</c:choose>

</body>
</html>