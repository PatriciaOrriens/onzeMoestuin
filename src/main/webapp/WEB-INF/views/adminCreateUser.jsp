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

                <label for="password">Wachtwoord:</label>
                <form:input name="password" path="password" type="password" class="form-control" />
            </div>
            <form:button type="submit" class="btn btn-success" name="createUserButton">
                <i class='fas fa-user'></i> Toevoegen</form:button>
        </form:form>

        <br />
        <p><a href="/adminDashboard" class="btn btn-success">Terug</a></p>

    </div>

<c:import url="partials/footer.jsp" />