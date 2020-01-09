<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

<!DOCTYPE html>

<head>
    <title>Taak wijzigen</title>
</head>

<body>
    <div class="container">
        <h1 class="display-3">Pas taak aan</h1>
            <div class="form">
                <form:form action="/task/update/${taskId}" modelAttribute="task">
                    <form:input path="taskId" type="hidden"/>
                    <br/>
                    <label>Naam van taak: </label>
                    <form:input path="taskName" type="text" value="${task.taskName}" class="taskName"/>
                    <br/>
                    <br/>

                    <input class="btn btn-primary" type="submit" value="Toepassen"/>
                </form:form>
            </div>
                <br />
                <a href="/adminManageTasks" class="btn btn-primary">Terug</a>
    </div>

</body>

<c:import url="partials/footer.jsp" />