<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
<div class="container">
    <h1 class="display-3">Tuinoverzicht</h1>
    <br/>

<%--    invitations for garden can pop up at the top    --%>
    <c:if test="${!empty invitations}">
        <c:forEach var="invitation" items="${invitations}">
            <div class="alert alert-warning" role="alert">
               <strong>Uitnodiging:</strong>
                ${invitation.user.username} heeft je uitgenodigd om lid te worden van ${invitation.garden.gardenName}!
                <a href="/garden/${invitation.garden.gardenId}/acceptInvitation">Accepteer</a> /
                <a href="/garden/${invitation.garden.gardenId}/refuseInvitation">Weiger</a>
            </div>
    <br/>
        </c:forEach>
    </c:if>

<%--    cards for each garden with info and redirect to gardens     --%>
    <div class="card-columns">
    <c:forEach var="garden" items="${allYourGardens}">
    <div class="card">
        <img class="card-img-top" src="../resources/img/gardenPicture.jpg" alt="garden image">
        <div class="card-body">
            <h4 class="card-title"><c:out value="${garden.gardenName}"/></h4>

            <p class="card-text">Leden van deze tuin:
            <ul>
                <c:forEach items="${garden.gardenMembers}" var="member">
                    <li><c:out value="${member.username}" /></li>
                </c:forEach>
                    <li><a href="/garden/${garden.gardenId}/invite" class="btn btn-outline-success">
                        <i class="fa fa-user-plus"></i> Nieuw lid</a></li>
            </ul>
            </p>
            <br/>

            <a href="garden/${garden.gardenId}" class="btn btn-success">Bezoek tuin</a>
            <a class="btn btn-success" href="garden/update/<c:out value="${garden.gardenId}" />"
            ><i class='far fa-edit'></i></a>
            <a class="btn btn-warning" href="#removeGardenModal_${garden.gardenId}" name="verwijderen"
                   data-toggle="modal"><i class='fas fa-trash-alt'></i></a>
        </div>
    </div>

            <!-- Modal to ask confirmation before delete of garden  -->
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
                            <a type="button" class="btn btn-secondary" href="/user/garden/delete/${garden.gardenId}"
                                name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
        </c:forEach>
    </div>

    <br/>
    <a href="/garden/add" class="btn btn-success">Tuin toevoegen</a>
    <br/>
    <br/>
    <a href="../logout" class="btn btn-success">Uitloggen</a>

<c:import url="partials/footer.jsp" />
