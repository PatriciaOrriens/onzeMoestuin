<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <link href="resources/css/register.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="form">
                <form:form method="post" modelAttribute="user">
                    <form:input path="username" type="text" placeholder="username"/>
                    <br/>
                    <br/>
                    <form:input path="password" type="password" placeholder="password"/>
                    <br/>
                    <br/>
                    <input type="submit" class="registerbutton" value="Register"/>
                </form:form>
            </div>
        </div>
    </body>
</html>