<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

     <div class="container">
        <h1 class="display-3">Registreren</h1>

        <c:if test="${not empty invitation}">
            <div class="alert alert-primary" role="alert">
            Je bent door <strong>${invitation.user.firstName}</strong> uitgenodigd om lid te worden van zijn of haar tuin <strong>${invitation.garden.gardenName}</strong>. Registreer je eerst om dit te kunnen doen.
            </div>
        </c:if>

       <form:form method="post" modelAttribute="user">
            <div class="form-group">
                <label for="username">Gebruikersnaam:</label>

                <form:input path="username" class="form-control" required="required" />
                <span class="text-danger">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('username') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('username').defaultMessage : ''}
                </span>

                <label for="email">E-mailadres:</label>
                <form:input path="email" type="text" class="form-control" value="${invitation.emailAddress}" />
                <span class="text-danger">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('email') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('email').defaultMessage : ''}
                </span>

                <label for="firstName">Voornaam:</label>
                <form:input path="firstName" type="text" class="form-control" />
                <span class="text-danger">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('firstName') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('firstName').defaultMessage : ''}
                </span>

                <label for="password">Wachtwoord:</label>
                <form:input path="password" type="password" class="form-control" required="required" />
                <span class="text-danger">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('password') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('password').defaultMessage : ''}
                </span>

            </div>
            <form:button type="submit" class="btn btn-primary" name="registerbutton">Registreer</form:button>
        </form:form>

  <c:import url="partials/footer.jsp" />
