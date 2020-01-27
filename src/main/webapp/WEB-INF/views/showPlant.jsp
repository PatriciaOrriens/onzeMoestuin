<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
    <div class="container">
	<div class="row">
	        <h1 class="display-3">${plant.plantInformation.plantName}</h1>
    </div>

    <div class="row">
        <h2>Algemene informatie</h2>

      <table class="table table-bordered">
        <tr>
            <td>Latijnse naam:</td>
            <td>${plant.plantInformation.latinName}</td>
        </tr>
        <tr>
            <td>Plantafstand (cm):</td>
            <td>${plant.plantInformation.plantingDistance}</td>
        </tr>
      </table>
      <p><a href="/plantinformationoverview/${plant.plantInformation.plantInfoId}" class="btn btn-success">Meer informatie</a>
      <br/>
      <br/>
      <a href="/userManageGardens" class="btn btn-success">Terug naar tuinoverzicht</a></p>

      </div>

<c:import url="partials/footer.jsp" />
