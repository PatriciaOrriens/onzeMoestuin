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
                    <td><a class="btn btn-outline-warning" href="/user/delete/<c:out value="${user.username}" />"
                        >Verwijder</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="/user/new" name="buttonGoToAdminCreateUser" class="btn btn-primary">
        <i class="fa fa-user-plus"></i> Toevoegen</a>
        <br/>
        <br/>
        <a href="/adminDashboard" name="buttonGoToAdminDashboard" class="btn btn-primary">Terug</a>
    </body>
</html>