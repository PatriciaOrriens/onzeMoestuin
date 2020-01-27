<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

  <div class="container">
        <h1 class="display-3">Overzicht van gebruikers </h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Naam</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td><c:out value="${user.username}" /></td>
                    <td><a class="btn btn-outline-warning" href="#removeUserModal_${user.username}" data-toggle="modal">Verwijder</a></td>
                </tr>

                <!-- Modal -->
                <div class="modal fade" id="removeUserModal_${user.username}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Gebruiker verwijderen</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                                <div class="modal-body">
                                    <p>Weet je zeker dat je gebruiker ${user.username} wilt verwijderen?</p>
                                </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                                <a type="button" class="btn btn-secondary" href="/user/delete/<c:out value="${user.username}" />" name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </table>
        <br/>
        <a href="/user/new" name="buttonGoToAdminCreateUser" class="btn btn-primary">
        <i class="fa fa-user-plus"></i> Toevoegen</a>
        <br/>
        <br/>
        <a href="/adminDashboard" name="buttonGoToAdminDashboard" class="btn btn-primary">Terug</a>

<c:import url="partials/footer.jsp" />