<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
<div class="container">

    <div class="row">
            <div class="col-sm-10">
                <h1 class="display-3">Taken</h1>
            </div>
            <div class="col-sm-2 my-auto" align="right">
                 <a href="/garden/${garden.gardenId}/addTaskGarden" name="goToAddTaskGarden" class="btn btn-success">
                    <i class="fa fa-plus"></i> Taak toevoegen</a>
            </div>
        </div>


    <c:import url="partials/showAllTasks.jsp" />

    <a href="/garden/${garden.gardenId}" class="btn btn-success">Terug naar tuin</a>

</div>
<c:import url="partials/footer.jsp" />
