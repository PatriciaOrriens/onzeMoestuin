<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
         <link href="resources/css/login.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="form">
                <form:form method="post" modelAttribute="user">
                    <form:input path="userName" type="text" placeholder="username"/>
                    <br/>
                    <br/>
                    <form:input path="password" type="password" placeholder="password"/>
                    <br/>
                    <br/>
                    <input type="submit" value="Login"/>
                </form:form>
            </div>
        </div>
    </body>
</html>