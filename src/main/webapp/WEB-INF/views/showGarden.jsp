<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<div class="row">
	    <div class="col-sm-11">
	        <h1 class="display-3">${garden.gardenName}</h1>
        </div>
        <div class="col-sm-1 my-auto">
            <a href="${garden.gardenId}/addPlant" class="btn btn-outline-success">&#43; Plant</a>
        </div>
    </div>

    <c:forEach items="${plants}" var="plant">
        <div class="container p-3 my-3 border">
            <a href="../plant/${plant.plantId}">
                <c:out value="${plant.plantInformation.plantName}" />
            </a>
        </div>
    </c:forEach>

      <!-- Tijdelijke code om te testen -->

      <h2>Leden van deze tuin:</h2>
      <ul>
           <c:forEach items="${garden.gardenMembers}" var="member">
                  <li><c:out value="${member.username}" /></li>
           </c:forEach>
      </ul>


      <a href="/userManageGardens" class="btn btn-primary">Terug naar tuinoverzicht</a>

<c:import url="partials/footer.jsp" />
