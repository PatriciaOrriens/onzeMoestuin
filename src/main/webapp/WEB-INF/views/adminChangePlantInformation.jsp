<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html xmlns:form="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>
    <link href="resources/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="form">
        <form:form method="post" modelAttribute="plantInformation">
            <form:input path="plantName" type="text" placeholder="plant name" class="plantName"/>
            <br/>
            <br/>
            <form:input path="latinName" type="text" placeholder="Latin name" class="latinName"/>
            <br/>
            <br/>
            <form:input path="plantingDistance" type="number" placeholder="planting distance (cm)" class="plantingDistance"/>
            <br/>
            <br/>
            <form:select path="lighting" type="text" placeholder="lighting" class="lighting">
                <select id="myList">
                    <option value="sun">sun</option>
                    <option value="shade">shade</option>
                </select>
            </form:select>
            <br/>
            <br/>
            <form:select path="sowingStart" type="text" placeholder="sowing start" class="sowingStart">
                <form:options items="${monthList}" itemValue="month" itemLabel="month" />
            </form:select>
            <br/>
            <br/>
            <input type="submit" value="Login"/>
        </form:form>
    </div>
</div>
</body>
</html>