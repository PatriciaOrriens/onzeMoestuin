<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2><i class="fa fa-calendar"></i> Eerstvolgende taken</h2>

<table class="table table-striped">
    <tr>
        <th>Taak</th>
        <th>Plant (id)</th>
        <th>Doen voor:</th>
        <th>Afvinken</th>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>
                <c:catch var="catchException">
                    <c:out value="${task.taskPlantInfo.taskDescription.taskName}" />
                </c:catch>
                <c:catch var="catchException">
                    <c:out value="${task.taskGardenName}" />
                </c:catch>
            </td>
            <td>
                <c:catch var="catchException">
                    <c:out value="${task.plant.plantInformation.plantName}"/>(<c:out value="${task.plant.plantId}"/>)
                </c:catch>
            </td>
            <td><c:out value="${task.dueDate}"/>
            </td>
            <td>
                <a class="btn btn-success" href="/user/task/completed/<c:out value="${task.taskId}" />"><i class="fa fa-check"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/userTaskOverview/${gardenId}" name="userTaskOverview" class="btn btn-success">Toon alle taken</a>
<a href="/garden/${garden.gardenId}/addTaskGarden" name="goToAddTaskGarden" class="btn btn-success">Tuintaak toevoegen</a>
