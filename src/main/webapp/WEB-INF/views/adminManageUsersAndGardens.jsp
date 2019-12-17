<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h1>User Overview </h1>
    <table>
        <tr>
            <th>Name </th>
            <th></th>
        </tr>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td><c:out value="${user.username}" /></td>

                </tr>
            </c:forEach>
        </table>
        <a href="/user/add">Add new user</a>
        <h1></h1>
        <form action="/returnToDashboard" method="post"><input type="submit" value="Back to menu"></form>
    </body>

</body>
</html>