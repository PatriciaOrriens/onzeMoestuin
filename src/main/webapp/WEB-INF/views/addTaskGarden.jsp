<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<h1 class="display-3">Nieuwe tuintaak aanmaken</h1>
	<!-- JSTL form -->
    <form:form action="/garden/add" modelAttribute="taskGarden">
    <form:hidden path="user" />
        <div class="form-group">
            <label for="name">Taak Omschrijving:</label>
            <form:input path="taskGardenName" class="form-control" />

            <label for="name">VervalDatum (dd-mm-jjjj):</label>
            <form:input path="dueDate" class="form-control" />

        </div>
        <form:button type="submit" name="storeTaskGarden" class="btn btn-success">Opslaan</form:button>
	</form:form>
    
<c:import url="partials/footer.jsp" />
