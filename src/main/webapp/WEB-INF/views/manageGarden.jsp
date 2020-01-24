<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
<div class="container">
    <h1 class="display-3">Tuin Overzicht</h1>

    <c:if test="${!empty invitations}">
        <c:forEach var="invitation" items="${invitations}">
            <div class="alert alert-warning" role="alert">
               <strong>Uitnodiging:</strong>
                ${invitation.user.username} heeft je uitgenodigd om lid te worden van ${invitation.garden.gardenName}!
                <a href="/garden/${invitation.garden.gardenId}/acceptInvitation">Accepteer</a> /
                <a href="/garden/${invitation.garden.gardenId}/refuseInvitation">Weiger</a>
            </div>
        </c:forEach>
    </c:if>

    <table class="table table-striped">
        <c:forEach var="garden" items="${allYourGardens}">
            <tr>
                <td><a href="garden/${garden.gardenId}">
                        <c:out value="${garden.gardenName}"/></a>
                </td>
                <td><a class="btn btn-success" href="garden/update/<c:out value="${garden.gardenId}" />"
                                    ><i class='far fa-edit'></i></a></td>
                <td>

                        <a class="btn btn-warning" href="#removeGardenModal_${garden.gardenId}" data-toggle="modal">
                            <i class='fas fa-trash-alt'></i></a>

                </td>
            </tr>
            <!-- Modal -->
            <div class="modal fade" id="removeGardenModal_${garden.gardenId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Tuin verwijderen</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Weet je zeker dat je ${garden.gardenName} wilt verwijderen?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                            <a href="/user/garden/delete/${garden.gardenId}" title="Delete">Verwijderen</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </table>
    <a href="/garden/add" class="btn btn-success">Tuin toevoegen</a>
    <a href="../logout" class="btn btn-success">Uitloggen</a>





<c:import url="partials/footer.jsp" />
