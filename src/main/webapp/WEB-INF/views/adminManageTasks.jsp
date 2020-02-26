<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">
    <h1 class="display-3">Overzicht van taken</h1>
    <table class="table table-striped">
        <tr>
            <th>Taaknaam</th>
            <th>Wijzig taak</th>
            <th>Verwijder taak</th>
        </tr>
        <c:forEach items="${allTasks}" var="taskDescription">
            <tr>
                <td><c:out value="${taskDescription.taskName}" /></td>
                <td><a class="btn btn-success" href="/task/update/<c:out value="${taskDescription.taskDescriptionId}" />">
                    <i class='far fa-edit'></i></a></td>
                <td><a class="btn btn-warning" href="#removeTaskModal_${taskDescription.taskDescriptionId}" data-toggle="modal"><i class='fas fa-trash-alt'></i></a></td>
            </tr>

            <!-- Modal -->
            <div class="modal fade" id="removeTaskModal_${taskDescription.taskDescriptionId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Taak verwijderen</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Weet je zeker dat je de ${taskDescription.taskName} taak wilt verwijderen?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                            <a type="button" class="btn btn-secondary" href="/task/delete/<c:out value="${taskDescription.taskDescriptionId}" />" name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
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
    <div class="row">
        <div class="col-5">
            <h3 class="display-5">Voeg nieuwe taak toe</h3>

            <form:form method="post" modelAttribute="taskDescription">
                <div class="form-group">
                    <label>Naam van taak: </label>
                    <form:input path="taskName" class="form-control" type="text" value="${taskDescription.taskName}" required="required" />
                    <p class="redText">
                            ${requestScope['org.springframework.validation.BindingResult.taskDescription'].hasFieldErrors('taskName') ? requestScope['org.springframework.validation.BindingResult.taskDescription'].getFieldError('taskName').defaultMessage : ''}
                    </p>
                    <form:button type="submit" class="btn btn-success" name="opslaanTaakOmschrijving">Toevoegen</form:button>
                </div>
                <br/>
            </form:form>
        </div>
    </div>

    <a class="btn btn-success" href="/adminDashboard">Terug</a>
</div>

<c:import url="partials/footer.jsp" />
