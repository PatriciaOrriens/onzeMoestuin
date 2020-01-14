<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">

    <h1 class="display-3">Hallootjes</h1>


    <!-- JSTL form -->
    <form:form action="/plantinfo/${plantInfo.plantInfoId}/task/add" modelAttribute="newTask">

        <div class="form-group">
            <select name="task" class="form-control">
            <c:forEach items="${allTasks}" var="task">
            <option value="${task.taskId}">${task.taskName}</option>
            </c:forEach>
            </select>
        </div>

       <form:button type="submit" class="btn btn-primary">Voeg toe</form:button>
    </form:form>

<c:import url="partials/footer.jsp" />
