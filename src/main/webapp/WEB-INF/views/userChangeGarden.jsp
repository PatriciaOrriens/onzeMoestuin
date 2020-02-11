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
                    <form:input path="gardenName" class="form-control" type="text" value="${garden.gardenName}"
                        required="required"/>
                        <p class="centeredRedText">
                            ${requestScope['org.springframework.validation.BindingResult.garden'].hasFieldErrors('gardenName') ? requestScope['org.springframework.validation.BindingResult.garden'].getFieldError('gardenName').defaultMessage : ''}
                        </p>

                    <label>Lengte (m): </label>
                    <form:input path="length" class="form-control" type="number"  min="1" value="${garden.length}"
                        required="required" />
                        <p class="centeredRedText">
                            ${requestScope['org.springframework.validation.BindingResult.garden'].hasFieldErrors('length') ? requestScope['org.springframework.validation.BindingResult.garden'].getFieldError('length').defaultMessage : ''}
                        </p>

                    <label>Breedte (m): </label>
                    <form:input path="width" class="form-control" type="number" min="1" value="${garden.width}"
                         required="required" />
                         <p class="centeredRedText">
                            ${requestScope['org.springframework.validation.BindingResult.garden'].hasFieldErrors('width') ? requestScope['org.springframework.validation.BindingResult.garden'].getFieldError('width').defaultMessage : ''}
                         </p>
                </div>

                <form:button type="submit" name="opslaanTuin" class="btn btn-success">Toepassen</form:button>
            </form:form>
        </div>
        <br/>
        <a href="/userManageGardens" class="btn btn-success">Terug</a>
</div>

<c:import url="partials/footer.jsp" />