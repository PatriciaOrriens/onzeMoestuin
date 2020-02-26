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

    <div id="garden-grid">
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

<!-- Include Handlebars templates -->
<c:import url="partials/templates/plantModalTemplate.jsp" />
<c:import url="partials/templates/plannedPlantsTemplate.jsp" />

<!-- Import JS file for garden grid -->
<script src="../resources/javascript/gardenView.js"></script>