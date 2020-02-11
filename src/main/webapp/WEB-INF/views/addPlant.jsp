<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

      <div class="container">
          <h1 class="display-3">Plant toevoegen aan ${garden.gardenName}</h1>
            <br/>
          <h5>Filter tabel</h5>
          <p>Zoek naar planten in de tabel:</p>
          <input id="input" type="text" placeholder="Zoek...">
          <br><br>

              <table class="table table-striped" id="tablePlants">
                  <thead>
                  <tr>
                      <th>Plant in tuin</th>
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
                      <td><a href="/garden/${garden.gardenId}/addPlant/${plantInfo.plantInfoId}" name="plantInfoId" class="btn btn-success"><i class='fas fa-seedling'></i>&#43;</a></td>
                      <td><c:out value="${plantInfo.plantName}" /></td>
                      <td><c:out value="${plantInfo.latinName}" /></td>
                      <td><c:out value="${plantInfo.sowingStart}"/> t/m <c:out value="${plantInfo.sowingEnd}" /></td>
                      <td><c:out value="${plantInfo.plantingStart}" /> t/m <c:out value="${plantInfo.plantingEnd}" /></td>
                      <td><a href="/plantinformationoverview/${plantInfo.plantInfoId}" class="btn btn-success">Meer informatie</a></td>
                  </tr>
                  </c:forEach>
                  </tbody>
              </table>

          <p><a class="btn btn-success" href="/garden/${garden.gardenId}">Terug naar de tuin</a></p>
      </div>

<script src="../resources/javascript/addPlant.js"></script>

<c:import url="partials/footer.jsp" />
