<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<c:import url="partials/header.jsp" />
  <div class="container">
	<div class="row">
	    <div class="col-sm-11">
	        <h1 class="display-3">${garden.gardenName}</h1>
        </div>
        <div class="col-sm-1 my-auto">
            <a href="garden/${garden.gardenId}/addPlant" class="btn btn-success"><i name="addplant" class='fas fa-seedling'></i>&#43;</a>
        </div>
    </div>


    <div id="grid-container" class="grid-stack" data-gs-column="${garden.width}" data-gs-length="${garden.length}" data-gs-max-row="${garden.length}">

        <c:forEach items="${plants}" var="plant">
            <div class="grid-stack-item" data-gs-x="${plant.xCoordinate}" data-gs-y="${plant.yCoordinate}" data-gs-width="${plant.width}" data-gs-height="${plant.height}" data-gs-locked="yes" data-plantId="${plant.plantId}">
                <div class="grid-stack-item-content">
                    <section class="vertical-align-grid-icon" id="${plant.plantId}">
                        <img src="/plant/${plant.plantId}/image", name="image" height="50%" id="${plant.plantId}"/>
                        <br />
                        ${plant.plantInformation.plantName}
                    </section>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Container for plants not yet placed in grid -->
    <div id="plannedPlants">
        <div id="plannedPlants-container"></div>
    </div>

    <!-- Modal to show Plant details-->
    <div id="plantModal" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">
         <div id="plantContainer"></div>
     </div>
    </div>


    <!-- Modal to show User details-->
        <div id="userModal" class="modal fade" role="dialog">
          <div class="modal-dialog modal-lg">
             <div id="userContainer"></div>
         </div>
    </div>

      <!-- Tijdelijke code om tuinleden weer te geven -->
      <h2>Leden</h2>
      <ul>
           <c:forEach items="${garden.gardenMembers}" var="member">
                  <li><c:out value="${member.username}" /></li>

           </c:forEach>
      </ul>
      <a href="/garden/${garden.gardenId}/invite" class="btn btn-success">
        <i class="fa fa-user-plus"></i> Lid toevoegen</a>

      <br/><br/>
      <c:import url="messages.jsp" />
      <br/>


     <h2>Taken</h2>
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
                            href="/user/task/completed/<c:out value="${task.taskId}" />">Afvinken</a>
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

        <!-- Handlebars template for Plant modal -->
        <script id="plantTemplate" type="text/x-handlebars-template">
           <div class="modal-content">
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title">{{plantInformation.plantName}}</h4>
             </div>
             <div class="modal-body">
               <p>{{plantInformation.plantName}}</p>

               {{#if startDate}}
                <p>Gestart op {{startDate}}</p>
               {{else}}
                <p>Nog niet gestart</p>
               {{/if}}

               {{#if harvestDate}}
                <p>Al geoogst</p>
               {{else}}
                <p>Nog niet geoogst</p>
               {{/if}}
             </div>
             <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal">Sluit</button>
             </div>
           </div>
       </script>

       <!-- Handlebars template for planned plants -->
       <script id="plannedPlantsTemplate" type="text/x-handlebars-template">
            <h2>Geplande, doch ongeplante planten</h2>
            <div id="accordion" class="newPlantContainer">
                  {{#each this}}
                    <div class="card">
                        <div class="card-header">
                            <img src="/plant/{{plantId}}/image">
                            <a class="card-link" data-toggle="collapse" href="#collapse-{{plantId}}">
                                {{plantInformation.plantName}}
                            </a>
                        </div>
                        <div id="collapse-{{plantId}}" class="collapse" data-parent="#accordion">
                            <div class="card-body">
                                <p>{{plantInformation.plantName}} kan het best worden ingezaaid tussen {{plantInformation.sowingStart}} en {{plantInformation.sowingEnd}}.</p>
                                <input type="button" value="Nu inzaaien" class="btn btn-success plantStartBtn" data-newPlantId="{{plantId}}" data-plantName="{{plantInformation.plantName}}"/>
                            </div>
                        </div>
                    </div>
                  {{/each}}
            </div>
       </script>


       <script src="../resources/javascript/gardenView.js"></script>



<script src="../resources/javascript/showGarden.js"></script>
<c:import url="partials/footer.jsp" />
