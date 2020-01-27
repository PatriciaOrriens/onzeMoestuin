<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
        <h1 class="display-3">Overzicht van plantinformatie </h1>
        <table class="table table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Nederlandse naam</th>
                        <th>Latijnse plantnaam</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
        <c:forEach items="${plantInformation}" var="plant">
            <tr>
                <td><c:out value="${plant.plantInfoId}" /></td>
                <td><c:out value="${plant.plantName}" /></td>
                <td><c:out value="${plant.latinName}" /></td>
                <td><a class="btn btn-outline-primary" href="/plantinfo/update/<c:out value="${plant.plantInfoId}" />"
                    >Wijzig</a></td>
                <td><a class="btn btn-outline-primary" href="/plantinfo/tasks/<c:out value="${plant.plantInfoId}" />"
                    >Beheer taken</a></td>
                <td><a class="btn btn-outline-warning" href="#removePlantModal_${plant.plantInfoId}" data-toggle="modal">Verwijder</a></td></td>
            </tr>
            <!-- Modal -->
                <div class="modal fade" id="removePlantModal_${plant.plantInfoId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Plant verwijderen</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                                <div class="modal-body">
                                    <p>Weet je zeker dat je de ${plant.plantName} wilt verwijderen?</p>
                                </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                                <a type="button" class="btn btn-secondary" href="/plantinfo/delete/<c:out value="${plant.plantInfoId}" />" name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
                            </div>
                        </div>
                    </div>
                </div>
        </c:forEach>
        </table>

        <a class="btn btn-primary" href="admincreateplantinfo">Plant toevoegen</a>
        <br/>
        <br/>
        <a class="btn btn-primary" href="/adminDashboard">Terug</a>

<c:import url="partials/footer.jsp" />