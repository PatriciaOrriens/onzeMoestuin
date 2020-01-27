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
            <th>Taaknaam</th>
            <th>Wijzig taak</th>
            <th>Verwijder taak</th>
        </tr>
        <c:forEach items="${allTasks}" var="task">
            <tr>
                <td><c:out value="${task.taskName}" /></td>
                <td><a class="btn btn-success" href="/task/update/<c:out value="${task.taskId}" />">
                    <i class='far fa-edit'></i></a></td>
                <td><a class="btn btn-warning" href="#removeTaskModal_${task.taskId}" data-toggle="modal"><i class='fas fa-trash-alt'></i></a></td>
            </tr>

            <!-- Modal -->
            <div class="modal fade" id="removeTaskModal_${task.taskId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Taak verwijderen</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                            <div class="modal-body">
                                <p>Weet je zeker dat je de ${task.taskName} taak wilt verwijderen?</p>
                            </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                            <a type="button" class="btn btn-secondary" href="/task/delete/<c:out value="${task.taskId}" />" name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
                        </div>
                    </div>
                </div>
            </div>

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
            <input class="btn btn-success" type="submit" value="Toevoegen"/>
            </div>
        <br/>
    </form:form>

    <a class="btn btn-success" href="/adminDashboard">Terug</a>
</div>

</body>
    <c:import url="partials/footer.jsp" />
