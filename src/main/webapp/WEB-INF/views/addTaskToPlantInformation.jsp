<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">

    <h1>Hallootjes</h1>


    <%--   <!-- JSTL form -->
    <form:form action="/plantinfo/tasks/3" modelAttribute="newTask">

        <div class="form-group">
            <select name="task" class="form-control">
            <c:forEach items="${allPlantInformation}" var="plantInfo">
            <option value="${plantInfo.plantInfoId}">${plantInfo.plantName}</option>
            </c:forEach>
            </select>
        </div>

       <form:button type="submit" class="btn btn-primary">Voeg toe</form:button>
    </form:form> --%>

<c:import url="partials/footer.jsp" />
