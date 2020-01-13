<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<!DOCTYPE html>
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
                <c:forEach items="${allTasksPlantInfo}" var="taskPlantInfo">
                    <tr>
                        <td><c:out value="${taskPlantInfo.taskPlantInfoId}" /></td>
                        <td><c:out value="${taskPlantInfo.daysAfterStart}" /></td>
                        <td><c:out value="${taskPlantInfo.repetitiveTask}" /></td>
                       <td><a class="btn btn-outline-primary" href="/plantinfo/task/update/<c:out value="${taskPlantInfo.taskPlantInfoId}" />"
                            >Wijzig</a></td>
                        <td><a class="btn btn-outline-warning" href="/plantinfo/task/delete/<c:out value="${taskPlantInfo.taskPlantInfoId}" />"
                            >Verwijder</a></td></td>
                    </tr>
                </c:forEach>
            </table>
            <br/>

            </table>
                    <br/>

                    <br/>
                    <br/>
                    <a href="/adminDashboard" class="btn btn-primary">Terug naar taakmenu</a>
            </div>


    </body>
</html>