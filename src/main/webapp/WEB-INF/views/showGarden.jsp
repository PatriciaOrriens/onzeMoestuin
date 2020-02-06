<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<div class="row">
	    <div class="col-sm-11">
	        <h1 class="display-3">${garden.gardenName}</h1>
        </div>
        <div class="col-sm-1 my-auto">
            <a href="garden/${garden.gardenId}/addPlant" class="btn btn-success"><i class='fas fa-seedling'></i>&#43; </a>
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
                    <a class="btn btn-warning" href="#removePlantModal_${plant.plantId}" data-toggle="modal"><i class='fas fa-trash-alt'></i></a>
                </td>
            </tr>

            <!-- Modal to show User details-->
                <div id="userModal" class="modal fade" role="dialog">
                  <div class="modal-dialog modal-lg">
                     <div id="userContainer"></div>
                 </div>
                </div>

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
      <a href="/garden/${garden.gardenId}/invite" class="btn btn-success">
        <i class="fa fa-user-plus"></i> Lid toevoegen</a>
        <br/><br/>


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
                    <c:choose>
                        <c:when test="${empty task.completedDate}"><td class="redText"></c:when>
                        <c:otherwise><td></c:otherwise>
                    </c:choose>
                    <c:out value="${task.dueDate}"/>
                </td>
                <td><c:out value="${task.completedDate}"/></td>
                <td><a onclick = "ajaxGetUser(${task.user.userId})" /><c:out value="${task.user.username}"/></a></td>
                <td align="right">
                    <c:if test="${empty task.user}">
                        <a class="completedGreenTaskButton"
                            href="/user/taskPlant/completed/<c:out value="${task.taskId}" />">Afvinken</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
     </table>

     <a href="/userManageGardens" name="returntooverview" class="btn btn-success">Terug naar tuinoverzicht</a>
     <a href="garden/${garden.gardenId}/addTaskGarden" name="goToAddTaskGarden" class="btn btn-success">Tuintaak toevoegen</a>

     <!-- Handlebars template for User modal -->
      <script id="userTemplate" type="text/x-handlebars-template">
         <div  class="modal-content">
           <div class="modal-header">
             <button type="button" class="close" data-dismiss="modal">&times;</button>
             <h4 class="modal-title">{{task.user.username}}</h4>
           </div>
           <div class="modal-body">
           <table class="table table-striped">
             <tr><th>Gebruikersnaam:</td><td>{{username}}</td></tr>
             <tr><th>E-mailadres:</th><td>{{email}}</td>
             <tr><th>Voornaam:</th><td>{{firstName}}</td></tr>
             <tr><th>Achternaam:</th><td>{{lastName}}</td></tr>
           </table>
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-default" data-dismiss="modal">Sluit</button>
           </div>
         </div>
    </script>



<script src="../resources/javascript/showGarden.js"></script>
<c:import url="partials/footer.jsp" />
