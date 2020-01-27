<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<div class="row">
	    <div class="col-sm-11">
	        <h1 class="display-3">${garden.gardenName}</h1>
        </div>
        <div class="col-sm-1 my-auto">
            <a href="${garden.gardenId}/addPlant" class="btn btn-success"><i class='fas fa-seedling'></i>&#43; </a>
        </div>
    </div>

    <table class="table table-striped">
        <tr>
            <th>Plant</th>
            <th>Verwijderen</th>
        </tr>
        <c:forEach items="${plants}" var="plant">
            <tr>
                <td>
                    <a href="../plant/${plant.plantId}">
                        <c:out value="${plant.plantInformation.plantName}" />
                    </a>
                </td>
                <td>
                    <a class="btn btn-warning" href="/plant/delete/${plant.plantId}"><i class='fas fa-trash'></i></a>
                </td>
            </tr>
        </c:forEach>
     </table>

      <!-- Tijdelijke code om tuinleden weer te geven -->
      <h2>Leden van deze tuin:</h2>
      <ul>
           <c:forEach items="${garden.gardenMembers}" var="member">
                  <li><c:out value="${member.username}" /></li>

           </c:forEach>
      </ul>
      <a href="/garden/${garden.gardenId}/invite" class="btn btn-success">
        <i class="fa fa-user-plus"></i> Lid toevoegen</a>


     <h2>Taken voor deze tuin:</h2>
     <table class="table table-striped">
        <tr>
            <th>Taak</th>
            <th>Plantsoort(plantnummer)</th>
            <th>Vervaldatum</th>
            <th>Uitvoerdatum</th>
            <th>Uitgevoerd door</th>
            <th></th>
        </tr>
        <c:forEach items="${taskPlants}" var="taskPlant">
            <tr>
                <td><c:out value="${taskPlant.taskPlantInfo.task.taskName}" /> </td>
                <td><c:out value="${taskPlant.plant.plantInformation.plantName}"/>(<c:out value="${taskPlant.plant.plantId}"/>) </td>
                <c:choose><c:when test="${empty taskPlant.completedDate}"><td class="redText"></c:when>
                    <c:otherwise><td></c:otherwise> </c:choose> <c:out value="${taskPlant.dueDate}"/></td>
                <td><c:out value="${taskPlant.completedDate}"/></td>
                <td><c:out value="${taskPlant.user.username}"/></td>
                <td align="right">
                    <c:if test="${empty taskPlant.user}">
                        <a class="completedGreenTaskButton"
                            href="/user/taskPlant/completed/<c:out value="${taskPlant.taskPlantId}" />">Taak uitvoeren</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
     </table>

      <a href="/userManageGardens" name="returntooverview" class="btn btn-success">Terug naar tuinoverzicht</a>

<c:import url="partials/footer.jsp" />
