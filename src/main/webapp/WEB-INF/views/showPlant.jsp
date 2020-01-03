<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

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
    </div>

    <div class="row">
        <h2>Zaaiperiode</h2>

        <a href="/" class="btn btn-primary">Nu inzaaien</a>
    </div>

    <div class="row">
        <h2>To Do</h2>
    </div>

    <div class="row">
        <h2>Berichten</h2>
    </div>

<c:import url="partials/footer.jsp" />
