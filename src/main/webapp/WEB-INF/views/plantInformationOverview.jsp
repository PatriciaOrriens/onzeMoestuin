<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
    <h1 class="display-3">${plantinformation.plantName}</h1>

        <table class="table table-striped">
            <tr><td>Plantnaam</td><td>${plantinformation.plantName}</td></tr>
            <tr><td>Latijnse plantnaam</td><td>${plantinformation.latinName}</td></tr>
            <tr><td>Plantafstand</td><td>${plantinformation.plantingDistance}</td></tr>
            <tr><td>Belichting</td><td>${plantinformation.lighting}</td></tr>
            <tr><td>Grondtype</td><td>${plantinformation.soilType}</td></tr>
            <tr><td>Zaaitijd (eerste maand)</td><td>${plantinformation.sowingStart}</td></tr>
            <tr><td>Zaaitijd (laatste maand)</td><td>${plantinformation.sowingEnd}</td></tr>
            <tr><td>Planttijd (eerste maand) </td><td>${plantinformation.plantingStart}</td></tr>
            <tr><td>Planttijd (laatste maand)</td><td>${plantinformation.plantingEnd}</td></tr>
            <tr><td>Oogsttijd (eerste maand)</td><td>${plantinformation.harvestingStart}</td></tr>
            <tr><td>Oogsttijd (laatste maand)</td><td>${plantinformation.harvestingEnd}</td></tr>
            <tr><td>Groeitijd in dagen</td><td>${plantinformation.growTime}</td></tr>
        </table>



      <a href="/userManageGardens" class="btn btn-success">Terug naar tuinoverzicht</a>

<c:import url="partials/footer.jsp" />