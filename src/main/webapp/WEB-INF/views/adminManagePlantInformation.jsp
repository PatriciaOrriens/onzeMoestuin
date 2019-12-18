<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>User Overview </h1>
        <table>
                    <tr><th>Name </th><th></th></tr>
        <c:forEach items="${plantInformation}" var="plant">
            <tr>
                <td><c:out value="${plant.plantName}" /></td>
                <td><td><a href="/user/delete/<c:out value="${plant.plantName}" />">Delete</a></td></td>
            </tr>
        </c:forEach>
        </table>
        <a href="adminchangeplantinfo">Add new plant information</a>
                <h1></h1>
                <h1></h1>
        <a href="/adminDashboard">Back to task menu</a>
</html>