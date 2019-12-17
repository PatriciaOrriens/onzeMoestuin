<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>User Overview </h1>
        <table>
            <tr><th>Name </th><th></th></tr>
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