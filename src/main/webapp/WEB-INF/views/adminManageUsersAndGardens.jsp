<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<html>
    <head>
        <title>gebruikers</title>
    </head>
    <body>
        <h1>Overzicht van gebruikers </h1>
        <table>
            <tr><th>Naam </th><th></th></tr>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td><c:out value="${user.username}" /></td>
                    <td><td><a href="/user/delete/<c:out value="${user.username}" />">Delete</a></td></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/user/new">Add new user</a>
        <h1></h1>
        <h1></h1>
        <a href="/adminDashboard">Back to task menu</a>
    </body>
</html>