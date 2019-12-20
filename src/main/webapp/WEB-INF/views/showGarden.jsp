<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

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

<c:import url="partials/footer.jsp" />
