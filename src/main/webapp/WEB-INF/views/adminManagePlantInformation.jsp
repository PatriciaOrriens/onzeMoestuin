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
                <td><a class="btn btn-success" href="/plantinfo/update/<c:out value="${plant.plantInfoId}" />"
                    >Wijzig</a></td>
                <td><a class="btn btn-success" href="/plantinfo/tasks/<c:out value="${plant.plantInfoId}" />"
                    >Beheer taken</a></td>
                <td><a class="btn btn-warning" href="/plantinfo/delete/<c:out value="${plant.plantInfoId}" />"
                    >Verwijder</a></td></td>
            </tr>
        </c:forEach>
        </table>

        <a class="btn btn-success" href="admincreateplantinfo">Plant toevoegen</a>
        <br/>
        <br/>
        <a class="btn btn-success" href="/adminDashboard">Terug</a>

<c:import url="partials/footer.jsp" />