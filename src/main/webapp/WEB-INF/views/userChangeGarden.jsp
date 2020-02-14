<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<div class="container">
    <h1 class="display-3">Pas tuin aan</h1>

    <div class="form">
        <c:import url="partials/gardenForm.jsp" />
    </div>

    <br/>
    <a href="/userManageGardens" class="btn btn-success">Terug</a>
</div>

<c:import url="partials/footer.jsp" />