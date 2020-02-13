<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">
    <h1 class="display-3">Pas tuin aan</h1>
    <div class="row">
        <div class="col-8">
    <div class="form">
        <c:import url="partials/gardenForm.jsp" />
    </div>
        </div>
<%--        TODO: list of members placed here temporarily in temporary format. Does not work yet either.--%>
        <div class="col-4">
    <c:import url="partials/showMembersOfGarden.jsp" />
        </div>
    </div>
    <br/>
    <a href="/userManageGardens" class="btn btn-success">Terug</a>
</div>

<c:import url="partials/footer.jsp" />