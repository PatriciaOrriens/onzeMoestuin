<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <title>Create a new user</title>
    </head>
    <body>
        <h1>Create a new user</h1>
        <form:form action="/user/new" modelAttribute="user">
            <table>
                <tr>
                    <td>Name:</td>
                    <td>
                        <form:input path="username" />
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <form:input path="password" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="Store user" />
                    </td>
                </tr>
            </table>
        </form:form>
        <h1></h1>
                <h1></h1>
                <a href="/adminDashboard">Back to task menu</a>
    </body>
</html>