<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

<div class="container">
    <h1 class="display-3">Plant toevoegen aan ${garden.gardenName}</h1>
    <br/>

    <%-- search function for plants --%>
    <h5 >Zoek naar planten in de tabel:</h5>
    <input id="input" type="text" placeholder="Zoek plant...">
    <br><br><br>


    <%--overview of all available plants--%>
    <table class="table table-striped" id="tablePlants">
        <h4>Overzicht van beschikbare planten</h4>
        <thead>
        <tr>
            <th>Nederlandse naam</th>
            <th>Latijnse naam</th>
            <th>Zaaitijd</th>
            <th>Planttijd</th>
            <th>Meer informatie</th>
            <th>Planten in tuin</th>
        </tr>
        </thead>

        <tbody id="plantsTable">
        <c:forEach items="${allPlantInformation}" var="plantInfo">
            <tr>
                <td><c:out value="${plantInfo.plantName}" /></td>
                <td><c:out value="${plantInfo.latinName}" /></td>
                <td><c:out value="${plantInfo.sowingStart}"/> t/m <c:out value="${plantInfo.sowingEnd}" /></td>
                <td><c:out value="${plantInfo.plantingStart}" /> t/m <c:out value="${plantInfo.plantingEnd}" /></td>
                <td><a href="/plantinformationoverview/${plantInfo.plantInfoId}"
                       class="btn btn-light">Meer informatie</a></td>
                <td><a class="btn btn-success" href="#addPlantModal_${plantInfo.plantInfoId}" data-toggle="modal">
                    <i class='fas fa-seedling'></i>&#43;</a></td>
            </tr>

            <!-- Modal to ask for confirmation before adding a plant -->
            <div class="modal fade" id="addPlantModal_${plantInfo.plantInfoId}" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Plant toevoegen</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Je gaat nu nieuwe ${plantInfo.plantName} in je tuin zetten. </p>
                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-success"
                               href="/garden/${garden.gardenId}/addPlant/<c:out value="${plantInfo.plantInfoId}" />"
                               name="modal-addPlant" title="addPlant">Bevestig</a>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
        </tbody>
    </table>
    <p><a class="btn btn-success" href="/garden/${garden.gardenId}">Terug naar de tuin</a></p>
</div>
</div>
</div>

<script src="../resources/javascript/addPlant.js"></script>

<c:import url="partials/footer.jsp" />
