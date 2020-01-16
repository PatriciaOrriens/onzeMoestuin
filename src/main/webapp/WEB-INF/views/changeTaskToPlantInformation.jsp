<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">

    <h1 class="display-3">Taak wijzigen voor ${taskPlantInfo.plantInformation.plantName}</h1>

    <!-- JSTL form -->
    <form:form action="/plantinfo/${plantInfoId}/task/update/${taskPlantInfoId}" modelAttribute="taskPlantInfo">

        <div class="row">
            <div class="col-md-9">
                <select name="taskId" class="form-control">

                    <c:forEach items="${allTasks}" var="task">
                    <option value="${task.taskId}">${task.taskName}</option>
                    </c:forEach>
                    <option value="${taskPlantInfo.task.taskId}" selected>${taskPlantInfo.task.taskName}</option>
                </select>
            </div>
        </div>
        </br>

        <div class="row">
            <div class="col-md-2">
                <label for="daysAfterStart">Dagen na start:</label>
            </div>
            <div class="col-md-2">
                <form:input class="form-control" type="number" min="0" path="daysAfterStart" value="${taskPlantInfo.daysAfterStart}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <div class="form-check">
                    <form:checkbox class="form-check-input" path="repetitiveTask" value="${taskPlantInfo.repetitiveTask}"/>
                    <label class="form-check-label" for="repetitiveTask">Herhalend</label>
                </div>
                </br>
            </div>
        </div>

        <form:button type="submit" class="btn btn-primary">Voeg toe</form:button>
            </form:form>

</div>


<c:import url="partials/footer.jsp" />