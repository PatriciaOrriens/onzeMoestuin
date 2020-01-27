<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<div class="row">
	    <div class="col-sm-11">
	        <h1 class="display-3">${garden.gardenName}</h1>
        </div>
        <div class="col-sm-1 my-auto">
            <a href="${garden.gardenId}/addPlant" class="btn btn-outline-success">&#43; Plant</a>
        </div>
    </div>

    <table class="table table-striped">
        <c:forEach items="${plants}" var="plant">
            <tr>
                <td><a href="../plant/${plant.plantId}"><c:out value="${plant.plantInformation.plantName}" /></a><td>
                <td><a class="btn btn-outline-warning" href="#removePlantModal_${plant.plantId}" data-toggle="modal">Verwijderen</a></td>
            </tr>

            <!-- Modal -->
            <div class="modal fade" id="removePlantModal_${plant.plantId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Plant verwijderen</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                            <div class="modal-body">
                                <p>Weet je zeker dat je plant ${plant.plantId} wilt verwijderen?</p>
                            </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                            <a type="button" class="btn btn-secondary" href="/plant/delete/${plant.plantId}" name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
     </table>

      <!-- Tijdelijke code om tuinleden weer te geven -->
      <h2>Leden van deze tuin:</h2>
      <ul>
           <c:forEach items="${garden.gardenMembers}" var="member">
                  <li><c:out value="${member.username}" /></li>

           </c:forEach>
      </ul>
      <a href="/garden/${garden.gardenId}/invite" class="btn btn-primary">
        <i class="fa fa-user-plus"></i>Lid toevoegen</a>


      <!-- Tijdelijke code om taken weer te geven -->
            <h2>Taken voor deze tuin:</h2>
            <ul>
                 <c:forEach items="${taskPlants}" var="taskPlant">
                        <li><c:out value="${taskPlant.taskPlantInfo.task.taskName}" />
                        (plantnummer: <c:out value="${taskPlant.plant.plantId}"/>;
                        plantnaam: <c:out value="${taskPlant.plant.plantInformation.plantName}"/>;
                        vervaldatum: <c:out value="${taskPlant.dueDate}"/>)</li>
                 </c:forEach>
            </ul>


      <a href="/userManageGardens" class="btn btn-primary">Terug naar tuinoverzicht</a>

<c:import url="partials/footer.jsp" />
