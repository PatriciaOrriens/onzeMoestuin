<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

<div class="container">
    <h1 class="display-3">Pas tuin aan</h1>
        <div class="form">
            <form:form action="/garden/update/${gardenId}" modelAttribute="garden">
                <form:hidden path="user" />
                <div class="form-group">
                    <label>Naam: </label>
                    <form:input path="gardenName" type="text" value="${garden.gardenName}" class="form-control"/>

                    <label>Lengte (m): </label>
                    <form:input path="length" type="text" value="${garden.length}" class="form-control"/>

                    <label>Breedte (m): </label>
                    <form:input path="width" type="text" value="${garden.width}" class="form-control"/>

                </div>

                <form:button type="submit" name="opslaanTuin" class="btn btn-success">Toepassen</form:button>
            </form:form>
        </div>
        <br/>
        <a href="/userManageGardens" class="btn btn-success">Terug</a>
</div>

<c:import url="partials/footer.jsp" />