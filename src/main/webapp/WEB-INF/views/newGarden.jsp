<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

	<h1>Nieuwe tuin aanmaken</h1>
	<!-- JSTL form -->
    <form:form action="/garden/add" modelAttribute="garden">
        <div class="form-group">
            <label for="name">Naam:</label>
            <form:input path="gardenName" class="form-control" />

            <label for="name">Lengte:</label>
            <form:input path="length" class="form-control" />

            <label for="name">Breedte:</label>
            <form:input path="width" class="form-control" />            

        </div>
        <form:button type="submit" class="btn btn-primary">Opslaan</form:button>
	</form:form>
    
<c:import url="partials/footer.jsp" />
