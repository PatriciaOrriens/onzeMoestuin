<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $("#input").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#plantsTable tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

  <%--<div class="container">
	<h1 class="display-3">Plant toevoegen aan ${garden.gardenName}</h1>

		<!-- JSTL form: ik laat het nog even staan, zolang rest nog niet klaar is -->
        <form:form action="/garden/${garden.gardenId}/addPlant" modelAttribute="plant">

            <div class="form-group">
                <select name="plantInfoId" class="form-control">
                <c:forEach items="${allPlantInformation}" var="plantInfo">
                <option value="${plantInfo.plantInfoId}">${plantInfo.plantName}</option>
                </c:forEach>
                </select>
            </div>

           <form:button type="submit" class="btn btn-success">Voeg <i class='fas fa-seedling'></i> toe</form:button>
        </form:form>--%>

      <div class="container">
          <h1 class="display-3">Plant toevoegen aan ${garden.gardenName}</h1>
            <br/>
          <h5>Filter tabel</h5>
          <p>Zoek naar planten in de tabel:</p>
          <input id="input" type="text" placeholder="Zoek...">
          <br><br>

          <form:form action="/garden/${garden.gardenId}/addPlant" modelAttribute="plant">
              <table class="table table-striped" id="tablePlants">
                  <thead>
                  <tr>
                      <th>Selecteer</th>
                      <th>Nederlandse naam</th>
                      <th>Latijnse naam</th>
                      <th>Zaaitijd</th>
                      <th>Planttijd</th>
                      <th>Meer informatie</th>
                  </tr>
                  </thead>
                  <tbody id="plantsTable">
                  <c:forEach items="${allPlantInformation}" var="plantInfo">
                  <tr>
    <%--                  tijdelijke code voor eerste kolom--%>
                      <td><input type="radio" name="chosenPlant"></td>
                      <td><c:out value="${plantInfo.plantName}" /></td>
                      <td><c:out value="${plantInfo.latinName}" /></td>
                      <td><c:out value="${plantInfo.sowingStart}"/> t/m <c:out value="${plantInfo.sowingEnd}" /></td>
                      <td><c:out value="${plantInfo.plantingStart}" /> t/m <c:out value="${plantInfo.plantingEnd}" /></td>
                      <td><a href="/plantinformationoverview/${plantInfo.plantInfoId}" class="btn btn-success">Alle informatie</a></td>
                  </tr>
                  </c:forEach>
                  </tbody>
              </table>
<%--              parameter van plantinfoId nodig bij het versturen en daarna weer laden van addPlantForm--%>
              <form:button type="submit" class="btn btn-success">Voeg <i class='fas fa-seedling'></i> toe</form:button>
          </form:form>
      </div>

<c:import url="partials/footer.jsp" />
