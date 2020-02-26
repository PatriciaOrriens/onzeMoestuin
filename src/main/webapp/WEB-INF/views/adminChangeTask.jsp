<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

<div class="container">

    <h1 class="display-3">Pas taak aan</h1>

    <div class="row">
        <div class="col-6">
            <div class="form">
                <form:form method="post" modelAttribute="taskDescription">
                    <form:input path="taskDescriptionId" type="hidden"/>
                    <br/>
                    <label>Naam van taak: </label>
                    <form:input path="taskName" type="text" class="form-control" value="${taskDescription.taskName}" />
                    <p class="redText">
                            ${requestScope['org.springframework.validation.BindingResult.taskDescription'].hasFieldErrors('taskName') ? requestScope['org.springframework.validation.BindingResult.taskDescription'].getFieldError('taskName').defaultMessage : ''}
                    </p>

                    <input class="btn btn-success" type="submit" value="Toepassen"/>
                </form:form>
            </div>
            <br/>
            <br/>
            <a href="/adminManageTasks" class="btn btn-success">Terug</a>
        </div>
    </div>
</div>

<c:import url="partials/footer.jsp" />