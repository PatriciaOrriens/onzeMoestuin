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
        <c:forEach items="${task}" var="task">
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
    <h1 class="display-4">Voeg nieuwe taak toe</h1>

    <form:form method="post" modelAttribute="task">
        <div class="form-group">
            <label>Naam van taak: </label>

        </div>
        <br/>
            <input class="btn btn-primary" type="submit" value="Toevoegen"/>
        <br/>
    </form:form>
    <br/>
    <br/>
    <a class="btn btn-primary" href="/adminManagePlantInformation">Terug</a>
</div>

</body>
    <c:import url="partials/footer.jsp" />
