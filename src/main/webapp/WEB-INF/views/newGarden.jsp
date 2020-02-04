<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<h1 class="display-3">Nieuwe tuin aanmaken</h1>
	<!-- JSTL form -->
    <form:form action="/garden/add" modelAttribute="garden">
    <form:hidden path="user" />
        <div class="form-group">
            <label for="name">Naam:</label>
            <form:input path="gardenName" class="form-control" type="text" required="required"/>

            <label for="name">Lengte (m):</label>
            <form:input path="length" class="form-control" type="number" min="1" required="required"/>

            <label for="name">Breedte (m):</label>
            <form:input path="width" class="form-control" type="number" min="1" required="required"/>

        </div>
        <form:button type="submit" name="opslaanTuin" class="btn btn-success">Opslaan</form:button>
	</form:form>
    
<c:import url="partials/footer.jsp" />
