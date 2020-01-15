<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

    <head>
        <title>Taken bij plantinformatie</title>
    </head>

    <body>
        <div class="container">
            <h1 class="display-3">Taken bij plantinformatie</h1>
            <table class="table table-striped">
                <tr>
                    <th>Id</th>
                    <th>Taak</th>
                    <th>Dagen na startdatum plant</th>
                    <th>Moet herhaald worden</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${plantInfo.tasks}" var="task">
                    <tr>
                        <td>${task.taskPlantInfoId}</td>
                        <td>${task.task.taskName}</td>
                        <td>${task.daysAfterStart}</td>
                        <td>${task.repetitiveTask}</td>
                        <td><a class="btn btn-outline-primary" href="/plantinfo/${plantInfoId}/task/update/<c:out
                        value="${task.taskPlantInfoId}" />"
                            >Wijzig</a></td>
                        <td><a class="btn btn-outline-warning" href="/plantinfo/task/delete/<c:out
                        value="${task.taskPlantInfoId}" />"
                            >Verwijder</a></td>
                    </tr>
                </c:forEach>
            </table>

            <p><a class="btn btn-primary" href="/plantinfo/${plantInfo.plantInfoId}/task/add">Taak toevoegen</a></p>

<c:import url="partials/footer.jsp" />
