<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

<html>
    <head>
        <title>Overzicht van plantinformatie</title>
    </head>
    <body>
        <h2>Overzicht van plantinformatie </h2>
        <table class="table table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Nederlandse naam</th>
                        <th>Latijnse plantnaam</th>
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
                <td><a class="btn btn-outline-warning" href="/plantinfo/delete/<c:out value="${plant.plantInfoId}" />"
                    >Verwijder</a></td></td>
            </tr>
        </c:forEach>
        </table>

        <a class="btn btn-primary" href="admincreateplantinfo">Voeg nieuwe plantinformatie toe</a>
        <br/>
        <br/>
        <a class="btn btn-primary" href="/adminDashboard">Terug naar taakmenu</a>
</html>

<c:import url="partials/footer.jsp" />