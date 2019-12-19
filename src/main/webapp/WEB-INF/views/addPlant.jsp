<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

	<h1>Plant toevoegen aan ${garden.gardenName}</h1>

		<table class="table">
    		<tr>
    			<th>Naam</th>
    		</tr>
    		<c:forEach items="${allPlantInformation}" var="plantInfo">
    			<tr>
    				<td>
    					<c:out value="${plantInfo.plantName}" />
    				</td>
    				<td>
    				    <a href="/garden/<c:out value="${garden.gardenId}" />/<c:out value="${plantInfo.plantInfoId}" />">Toevoegen</a>
    				</td>
    			</tr>
    		</c:forEach>
    	</table>

	<!-- JSTL form -->
    <form:form action="/addplant" modelAttribute="plant">
        <div class="form-group">
        </div>
        <form:button type="submit" class="btn btn-primary">Voeg toe</form:button>
	</form:form>

<c:import url="partials/footer.jsp" />
