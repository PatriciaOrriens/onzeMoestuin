<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
<script src="../resources/javascript/createUser.js"></script>

     <div class="container">
        <h1 class="display-3">Registreren</h1>

        <c:if test="${not empty invitation}">
            <p><div class="alert alert-success" role="alert">
            Je bent door <strong>${invitation.user.firstName}</strong> uitgenodigd om lid te worden van zijn of haar tuin <strong>${invitation.garden.gardenName}</strong>. Registreer je eerst om dit te kunnen doen.
            </div></p>
        </c:if>

       <form:form name="userForm" method="post" modelAttribute="user" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Gebruikersnaam:</label>
                <form:input name="username" path="username" class="form-control"  />
                <p id="usernameError" class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('username') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('username').defaultMessage : ''}
                </p>


                <label for="email">E-mailadres:</label>
                <form:input name="email" path="email" type="text" class="form-control" value="${invitation.emailAddress}" />
                <p id="emailLengthError" class="centeredRedText"> </p>
                <p id="emailStructureError" class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('email') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('email').defaultMessage : ''}
                </p>

                <label for="firstName">Voornaam:</label>
                <form:input path="firstName" type="text" class="form-control" />
                <p class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('firstName') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('firstName').defaultMessage : ''}
                </p>

                <label for="lastName">Achternaam:</label>
                <form:input path="lastName" type="text" class="form-control" />
                <p class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('lastName') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('lastName').defaultMessage : ''}
                </p>

                <label for="password">Wachtwoord:</label>
                <form:input name="password" path="password" type="password" class="form-control" />
                <p id="passwordError" class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('password') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('password').defaultMessage : ''}
                </p>

                <p class="centeredRedText">${remark}</p>

            </div>
            <form:button type="submit" class="btn btn-success" name="registerbutton" >Registreer</form:button>
        </form:form>

  <c:import url="partials/footer.jsp" />
