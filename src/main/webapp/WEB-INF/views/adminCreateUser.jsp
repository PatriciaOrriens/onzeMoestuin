<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../resources/javascript/adminCreateUser.js"></script>

    <div class="container">
       <h1 class="display-3">Gebruiker toevoegen</h1>
       <form:form method="post" modelAttribute="user">
            <div class="form-group">
                <label for="username">Gebruikersnaam:</label>
                <form:input path="username" class="form-control" />

                <label for="email">E-mailadres:</label>
                <form:input path="email" type="text" class="form-control" value="${invitation.emailAddress}" />

                <label for="firstName">Voornaam:</label>
                <form:input path="firstName" type="text" class="form-control" />

                <label>Rol:</label>
                <form:select path="${roleName}" name="roleName" type="text" value="${roleName}" class="form-control" id="rights"></form:select>

                <label for="password">Wachtwoord:</label>
                <form:input path="password" type="password" class="form-control" />
            </div>
            <form:button type="submit" class="btn btn-success" name="createUserButton">
                <i class='fas fa-user'></i> Toevoegen</form:button>
        </form:form>

        <br />
        <p><a href="/adminDashboard" class="btn btn-success">Terug</a></p>

    </div>

<c:import url="partials/footer.jsp" />