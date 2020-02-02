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


    <div class="grid-stack" data-gs-column="${garden.width}" data-gs-current-row="${garden.length}" data-gs-max-row="${garden.length}">
        <c:forEach items="${plants}" var="plant">
            <div class="grid-stack-item" data-gs-x="${plant.xCoordinate}" data-gs-y="${plant.yCoordinate}" data-gs-width="${plant.width}" data-gs-height="${plant.height}" data-gs-locked="yes">
                <div class="grid-stack-item-content" onclick="ajaxGetPlant(${plant.plantId})">${plant.plantInformation.plantName}</div>
            </div>
        </c:forEach>
    </div>

    <!-- Modal to show Plant details-->
    <div id="plantModal" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">
         <div id="plantContainer"></div>
     </div>
    </div>

    <!-- comment old code out
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
            </tr> -->

            <!-- Modal
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
     </table> -->

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

        <!-- TODO: temporary location for GridStack script -->
       <script type="text/javascript">
              $('.grid-stack').gridstack({

              });

              // Get new height & width on resize {}
              $('.grid-stack').on('gsresizestop', function(event, elem) {
                var newHeight = $(elem).attr('data-gs-height');
                var newWidth = $(elem).attr('data-gs-width');
                console.log("Height: " + newHeight + ", Width: " + newWidth);
              });

              // Get new x/y coordinate on moving
              $('.grid-stack').on('dragstop', function(event, ui) {
                  var grid = this;
                  var elem = event.target;
                  var newX = $(elem).attr('data-gs-x');
                  var newY = $(elem).attr('data-gs-y');
                  console.log("New x: " + newX + ", new Y: " + newY);
              });
        </script>

        <!-- TODO: temporary location for AJAX code -->
        <script>
            // Get plant details when clicked {}
            function ajaxGetPlant(plantId) {
                $.ajax({
                    type: "GET",
                    url: "../api/getPlant/" + plantId,
                    success: function(response) {
                        plantHTML(response);
                        $('#plantModal').modal('show')
                    },
                    error : function(e) {
						console.log("ERROR: ", e);
                    }
                });
            }
            <!-- Handlebars generating HTML -->
            function plantHTML(plantData) {
                // load Handlebars template from id in html file {}
                var rawTemplate = document.getElementById("plantTemplate").innerHTML;
                // create dynamic template function
                var compiledTemplate = Handlebars.compile(rawTemplate);
                // populate template with JSON data, generate string of HTML
                var ourGeneratedHTML = compiledTemplate(plantData);
                // add html to DOM
                var plantContainer = document.getElementById("plantContainer");
                plantContainer.innerHTML = ourGeneratedHTML;
            }
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

<c:import url="partials/footer.jsp" />
