<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<h1 class="display-3">Plant toevoegen aan ${garden.gardenName}</h1>

		<!-- JSTL form -->
        <form:form action="/garden/${garden.gardenId}/addPlant" modelAttribute="plant">

            <div class="form-group">
                <select name="plantInfoId" class="form-control">
                <c:forEach items="${allPlantInformation}" var="plantInfo">
                <option value="${plantInfo.plantInfoId}">${plantInfo.plantName}</option>
                </c:forEach>
                </select>
            </div>

           <form:button type="submit" class="btn btn-success">Voeg <i class='fas fa-seedling'></i> toe</form:button>
        </form:form>

<c:import url="partials/footer.jsp" />
