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
    <div id="plantModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div id="plantContainer"></div>
        </div>
    </div>

    <!-- Handlebars template for Plant modal -->
    <script id="plantTemplate" type="text/x-handlebars-template">
        <div class="modal-content">
            <div class="modal-header plant-modal-header">
                <h4 class="modal-title">{{plantInformation.plantName}}</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-9">
                        <table class="table table-striped">
                            <tr>
                                <td>In je tuin sinds</td>
                                <td>{{startDate}}</td>
                            </tr>
                            <tr>
                                <td>Indicatie voor de groeitijd:</td>
                                <td>{{plantInformation.growTime}} dagen</td>
                            </tr>
                            <tr>
                                <td>Oogsttijd:</td>
                                <td>{{plantInformation.harvestingStart}} t/m {{plantInformation.harvestingEnd}}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-sm-3 plant-modal-image">
                        <img src="/plant/{{plantId}}/image">
                    </div>
                </div>
                    </div>

            <div class="container">
                <div class="alert alert-success" id="harvestDiv">
                    <div class="col-sm-9">
                    Wil je deze plant oogsten en verwijderen uit je tuin?
                    </div>
                    <div class="col-sm-3 my-auto">
                        <a class="btn btn-success" href="/plant/delete/{{plantId}}">Nu oogsten</a>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-success" id="harvestBtn">Oogsten</button>
                <button type="button" class="btn btn-success" data-dismiss="modal">Sluit</button>
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
                        <p>{{plantInformation.plantName}} kan het best worden ingezaaid tussen {{plantInformation.sowingStart}} en {{plantInformation.sowingEnd}}.</p>
                        <input type="button" value="Nu inzaaien" class="btn btn-success plantStartBtn" data-newPlantId="{{plantId}}" data-plantName="{{plantInformation.plantName}}"/>
                    </div>
                </div>
            </div>
            {{/each}}
        </div>
    </script>

    <script src="../resources/javascript/gardenView.js"></script>