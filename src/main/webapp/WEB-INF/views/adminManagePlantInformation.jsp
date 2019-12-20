<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

<html>
    <head>
        <title>Overzicht van plantinformatie</title>
    </head>
    <body>
        <h2>Overzicht van plantinformatie </h2>
        <table>
                    <tr><th>Id </th><th>Name of plant</th><th>Latin name</th><th></th><th></th></tr>
        <c:forEach items="${plantInformation}" var="plant">
            <tr>
                <td><c:out value="${plant.plantInfoId}" /></td>
                <td><c:out value="${plant.plantName}" /></td>
                <td><c:out value="${plant.latinName}" /></td>
                <td><a href="/plantinfo/update/<c:out value="${plant.plantInfoId}" />">Update</a></td>
                <td><td><a href="/plantinfo/delete/<c:out value="${plant.plantInfoId}" />">Delete</a></td></td>
            </tr>
        </c:forEach>
        </table>
        <a href="admincreateplantinfo">Add new plant information</a>
                <h1></h1>
                <h1></h1>
        <a href="/adminDashboard">Back to task menu</a>
</html>

<c:import url="partials/footer.jsp" />