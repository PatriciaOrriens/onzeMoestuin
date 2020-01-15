<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<!DOCTYPE html>
    <head>
        <title>Generieke taken</title>
    </head>

<body>
<div class="container">
    <h1 class="display-3">Overzicht van taken</h1>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Taaknaam</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${allTasks}" var="task">
            <tr>
                <td><c:out value="${task.taskId}" /></td>
                <td><c:out value="${task.taskName}" /></td>
                <td><a class="btn btn-outline-primary" href="/task/update/<c:out value="${task.taskId}" />"
                    >Wijzig</a></td>
                <td><a class="btn btn-outline-warning" href="/task/delete/<c:out value="${task.taskId}" />"
                    >Verwijder</a></td></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>
    </div>

<div class="container">
    <h1 class="display-5">Voeg nieuwe taak toe</h1>

    <form:form modelAttribute="newTask">
        <div class="form-group">
            <label>Naam van taak: </label>
            <form:input path="taskName" type="text" />
            <br/>
            <input class="btn btn-primary" type="submit" value="Toevoegen"/>
            </div>
        <br/>
    </form:form>

    <br/>
    <a class="btn btn-primary" href="/adminDashboard">Terug</a>
</div>

</body>
    <c:import url="partials/footer.jsp" />
