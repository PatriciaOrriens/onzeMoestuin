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

        <table class="table table-striped">
            <tr>
                <th>Plant</th>
                <th>Verwijderen</th>
            </tr>
            <c:forEach items="${plants}" var="plant">
            <tr>
                <td>
                    <a href="../plant/${plant.plantId}" name="plantlink"><c:out value="${plant.plantInformation.plantName}" /></a>
                </td>
                <td>
                    <a class="btn btn-warning" href="#removePlantModal_${plant.plantId}" data-toggle="modal"><i class='fas fa-trash-alt'></i></a>
                </td>
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


        <h2>Leden</h2>
        <ul>
            <c:forEach items="${garden.gardenMembers}" var="member">
                <li><c:out value="${member.username}" /></li>
            </c:forEach>
        </ul>
        <a href="/garden/${garden.gardenId}/invite" class="btn btn-success">
        <i class="fa fa-user-plus"></i> Lid toevoegen</a>

        <br/>
        <br/>
        <c:import url="messages.jsp" />
        <br/>
        <h2>Meest dringende taken</h2>
        <c:import url="partials/showTask.jsp" />
        <a href="/userTaskOverview/${gardenId}" name="userTaskOverview" class="btn btn-success">Toon alle taken</a>
<c:import url="partials/footer.jsp" />
