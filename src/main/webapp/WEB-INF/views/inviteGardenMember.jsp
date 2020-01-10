<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
    <h1 class="display-3">Iemand uitnodigen voor '${garden.gardenName}'</h1>

    <p>Voer de naam van de gebruiker in om deze uit te nodigen.</p>
    <!-- JSTL form
        <form:form action="/garden/${garden.gardenId}/invite" method="get" modelAttribute = "search">
            <div class="form-group">
                <label for="name">Naam:</label>
                <form:input path="search" class="form-control" />
            </div>
            <form:button type="submit" class="btn btn-primary">Zoek</form:button>
    	</form:form> -->


    <c:if test="${!empty foundUser}">
        <h2>Gevonden gebruiker:</h2>
        <p>${foundUser.username}</p>
    </c:if>


<c:import url="partials/footer.jsp" />
