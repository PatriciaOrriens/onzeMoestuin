<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">

    <h1 class="display-3">Taak toevoegen voor ${plantInfo.plantName}</h1>

    <!-- JSTL form -->
    <form:form action="/plantinfo/${plantInfo.plantInfoId}/task/add" modelAttribute="newTask">

        <div class="row">
            <div class="col-md-9">
                <select name="taskId" class="form-control">
                    <option selected>Selecteer de taak</option>
                    <c:forEach items="${allTasks}" var="task">
                    <option value="${task.taskId}">${task.taskName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        </br>

        <div class="row">
            <div class="col-md-2">
                <label for="daysAfterStart">Dagen na start:</label>
            </div>
            <div class="col-md-2">
                <input type="number" class="form-control" value="daysAfterStart">
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <div class="custom-control custom-checkbox mb-3">
                    <input type="checkbox" class="custom-control-input" id="repetitiveTask">
                    <label class="custom-control-label" for="repetitiveTask">Herhalend</label>
                </div>
            </div>
        </div>
        <form:button type="submit" class="btn btn-primary">Voeg toe</form:button>
            </form:form>

</div>


<c:import url="partials/footer.jsp" />
