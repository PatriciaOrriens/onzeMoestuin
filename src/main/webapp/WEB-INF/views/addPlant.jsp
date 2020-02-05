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

      <div class="container">
          <h1 class="display-3">Plant toevoegen aan ${garden.gardenName}</h1>
          <table class="table table-striped" id="tablePlants">
              <tr>
                  <th>Selecteer</th>
                  <th>Nederlandse naam</th>
                  <th>Latijnse naam</th>
                  <th>Zaaitijd</th>
                  <th>Planttijd</th>
                  <th>Meer informatie</th>
              </tr>
              <c:forEach items="${allPlantInformation}" var="plantInfo">
              <tr>
<%--                  tijdelijke code voor eerste kolom--%>
                  <td><c:out value="${plantInfo.plantInfoId}" /></td>
                  <td><c:out value="${plantInfo.plantName}" /></td>
                  <td><c:out value="${plantInfo.latinName}" /></td>
                  <td><c:out value="${plantInfo.sowingStart}"/> t/m <c:out value="${plantInfo.sowingEnd}" /></td>
                  <td><c:out value="${plantInfo.plantingStart}" /> t/m <c:out value="${plantInfo.plantingEnd}" /></td>
<%--                  tijdelijke code voor laatste kolom--%>
                    <td>link</td>
              </tr>
              </c:forEach>
          </table>
      </div>






<c:import url="partials/footer.jsp" />
