<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</div>

    <!-- Modal to show Plant details-->
    <div id="plantModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div id="plantContainer"></div>
        </div>
    </div>

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
        <br/>
        <h5>Deze planten staan klaar om te planten in je moestuin</h5>
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
                        <ul>
                            <li>{{plantInformation.plantName}} kan het best worden ingezaaid tussen <strong>{{plantInformation.sowingStart}}</strong> en <strong>{{plantInformation.sowingEnd}}</strong></li>
                            <li>Hanteer een <strong>plantafstand</strong> van ongeveer <strong>{{plantInformation.plantingDistance}}</strong> cm</li>
                            <li>Houdt van een plekje in de <strong>{{plantInformation.lighting}}</strong></li>
                            <li>Als alles goed gaat kun je {{plantInformation.plantName}} oogsten tussen <strong>{{plantInformation.harvestingStart}}</strong> en <strong>{{plantInformation.harvestingEnd}}</strong></li>
                        </ul>

                        <input type="button" value="Nu inzaaien" class="btn btn-success plantStartBtn" data-newPlantId="{{plantId}}" data-plantName="{{plantInformation.plantName}}"/>
                    </div>
                </div>
            </div>
            {{/each}}
        </div>
    </script>

    <script src="../resources/javascript/gardenView.js"></script>