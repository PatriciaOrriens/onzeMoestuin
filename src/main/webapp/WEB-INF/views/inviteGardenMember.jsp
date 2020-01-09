<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
    <h1 class="display-3">Iemand uitnodigen voor '${garden.gardenName}'</h1>

    <p>Voer de naam van de gebruiker in om deze uit te nodigen.</p>
    <!-- JSTL form -->
        <form:form action="/garden/${garden.gardenId}/invite">
            <div class="form-group">
                <label for="name">Naam:</label>
                <form:input class="form-control" />
            </div>
            <form:button type="submit" class="btn btn-primary">Uitnodigen</form:button>
    	</form:form>

<c:import url="partials/footer.jsp" />
