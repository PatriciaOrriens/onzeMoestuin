<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
	<h1 class="display-3">Nieuwe tuin aanmaken</h1>
	<!-- JSTL form -->
    <form:form action="/garden/add" modelAttribute="garden">
    <form:hidden path="user" />
        <div class="form-group">
            <label for="name">Naam:</label>
            <form:input path="gardenName" class="form-control" type="text" required="required"/>
            <p class="centeredRedText">
                ${requestScope['org.springframework.validation.BindingResult.garden'].hasFieldErrors('gardenName') ? requestScope['org.springframework.validation.BindingResult.garden'].getFieldError('gardenName').defaultMessage : ''}
            </p>

            <label for="name">Lengte (m):</label>
            <form:input path="length" class="form-control" type="number" min="1" required="required"/>
            <p class="centeredRedText">
                ${requestScope['org.springframework.validation.BindingResult.garden'].hasFieldErrors('length') ? requestScope['org.springframework.validation.BindingResult.garden'].getFieldError('length').defaultMessage : ''}
            </p>

            <label for="name">Breedte (m):</label>
            <form:input path="width" class="form-control" type="number" min="1" required="required"/>
            <p class="centeredRedText">
                ${requestScope['org.springframework.validation.BindingResult.garden'].hasFieldErrors('width') ? requestScope['org.springframework.validation.BindingResult.garden'].getFieldError('width').defaultMessage : ''}
            </p>

        </div>
        <form:button type="submit" name="opslaanTuin" class="btn btn-success">Opslaan</form:button>
	</form:form>
    
<c:import url="partials/footer.jsp" />
