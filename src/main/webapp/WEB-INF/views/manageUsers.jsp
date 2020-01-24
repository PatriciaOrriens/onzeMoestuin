<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

  <div class="container">
        <h1 class="display-3">Overzicht van gebruikers </h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Naam</th>
                <th>Verwijder gebruiker</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td><c:out value="${user.username}" /></td>
                    <td><a class="btn btn-warning" href="/plantinfo/delete/<c:out value="${plant.plantInfoId}" />"
                        ><i class='fas fa-trash-alt'></i></a></td></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="/user/new" name="buttonGoToAdminCreateUser" class="btn btn-success">
        <i class="fa fa-user-plus"></i> Toevoegen</a>
        <br/>
        <br/>
        <a href="/adminDashboard" name="buttonGoToAdminDashboard" class="btn btn-success">Terug</a>
    </body>
</html>