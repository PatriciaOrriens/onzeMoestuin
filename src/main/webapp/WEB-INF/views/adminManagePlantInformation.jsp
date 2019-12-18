<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Plant Information Overview</title>
    </head>
    <body>
        <h1>Plant Information Overview </h1>
        <table>
                    <tr><th>Id </th><th>Name of plant</th><th>Latin name</th></tr>
        <c:forEach items="${plantInformation}" var="plant">
            <tr>
                <td><c:out value="${plant.plantInfoId}" /></td>
                <td><c:out value="${plant.plantName}" /></td>
                <td><c:out value="${plant.latinName}" /></td>
                <td><td><a href="/deleteplantinfo/<c:out value="${plant.plantName}" />">Delete</a></td></td>
            </tr>
        </c:forEach>
        </table>
        <a href="adminchangeplantinfo">Add new plant information</a>
                <h1></h1>
                <h1></h1>
        <a href="/adminDashboard">Back to task menu</a>
</html>