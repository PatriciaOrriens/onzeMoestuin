<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
<script src="../resources/javascript/createUser.js"></script>

    <div class="container">
       <h1 class="display-3">Gebruiker toevoegen</h1>

       <form:form name="userForm" method="post" modelAttribute="user" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Gebruikersnaam:</label>
                <form:input name="username" path="username" class="form-control" />
                <p id="usernameError" class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('username') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('username').defaultMessage : ''}
                </p>

                <label for="email">E-mailadres:</label>
                <form:input name="email" path="email" type="text" class="form-control" />
                <p id="emailLengthError" class="centeredRedText"> </p>
                <p id="emailStructureError" class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('email') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('email').defaultMessage : ''}
                </p>

                <label for="password">Wachtwoord:</label>
                <form:input name="password" path="password" type="password" class="form-control" />
                <p id="passwordError" class="centeredRedText">
                    ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('password') ? requestScope['org.springframework.validation.BindingResult.user'].getFieldError('password').defaultMessage : ''}
                </p>

            </div>
            <p class="centeredRedText">${remark}</p>
            <form:button type="submit" class="btn btn-success" name="createUserButton">
                <i class='fas fa-user'></i> Toevoegen</form:button>
        </form:form>

        <br />
        <p><a href="/adminDashboard" class="btn btn-success">Terug</a></p>

    </div>

<c:import url="partials/footer.jsp" />