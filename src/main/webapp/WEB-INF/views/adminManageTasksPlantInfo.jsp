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
                        <td><a class="btn btn-outline-primary" href="/plantinfo/task/update/<c:out value="" />"
                            >Wijzig</a></td>
                        <td><a class="btn btn-outline-warning" href="/plantinfo/task/delete/<c:out value="" />"
                            >Verwijder</a></td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
        </div>

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
        </form:form>

        <div class="container">
                <h1 class="display-5">Voeg nieuwe taak toe voor deze plant</h1>

                <form:form modelAttribute="newTask">
                    <div class="form-group">
                        <label>: </label>
                        <!-- <form:input path="..." type="text" /> -->
                        <br/>
                        <input class="btn btn-primary" type="submit" value="Toevoegen"/>
                        </div>
                    <br/>
                </form:form>
                    <br/>
                    <a href="/adminDashboard" class="btn btn-primary">Terug naar taakmenu</a>
        --%>
        </div>
        </div>

    </body>