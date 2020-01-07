<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

    <div class="container">
       <h1 class="display-3">Gebruiker toevoegen</h1>

       <form:form method="post" modelAttribute="user">
            <div class="form-group">
                <label for="name">Gebruikersnaam:</label>
                <form:input path="username" class="form-control" />

                <label for="password">Wachtwoord:</label>
                <form:input path="password" type="password" class="form-control" />
            </div>
            <form:button type="submit" class="btn btn-primary" name="createUserButton" >Toevoegen</form:button>
        </form:form>

        <br />
        <p><a href="/adminDashboard" class="btn btn-primary">Terug</a></p>

    </div>

<c:import url="partials/footer.jsp" />