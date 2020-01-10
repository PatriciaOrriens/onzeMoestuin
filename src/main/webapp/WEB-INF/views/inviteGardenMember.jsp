<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
    <h1 class="display-3">Iemand uitnodigen voor '${garden.gardenName}'</h1>

    <p>Voer de naam van de gebruiker in om deze uit te nodigen.</p>

    <form method="get">

        <div class="form-group">
            <label for "name">Naam</label>
            <input name="search" class="form-control" type="text" />
        </div>
        <button type="submit" class="btn btn-primary">Zoek</button>

    </form>



    <!-- Display user if found -->
    <c:if test="${!empty foundUser}">
        <h2>Gevonden gebruiker:</h2>
        <p>${foundUser.username}</p>
    </c:if>


<c:import url="partials/footer.jsp" />
