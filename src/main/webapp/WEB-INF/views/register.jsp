<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
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
                    <input type="submit" value="Register"/>
                </form:form>
            </div>
        </div>
    </body>
</html>