<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

	<h1>Plant toevoegen aan ${garden.gardenName}</h1>

	<!-- JSTL form -->
    <form:form action="/addplant" modelAttribute="plant">
        <div class="form-group">



        </div>
        <form:button type="submit" class="btn btn-primary">Voeg toe</form:button>
	</form:form>

<c:import url="partials/footer.jsp" />
