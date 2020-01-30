<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
<script src="../resources/javascript/adminCreateUser.js"></script>

    <div class="container">
       <h1 class="display-3">Gebruiker toevoegen</h1>

       <form name="myForm" method="post" modelAttribute="user" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Gebruikersnaam:</label>
                <input name="username" path="username" class="form-control" />

                <label for="password">Wachtwoord:</label>
                <input path="password" type="password" class="form-control" />
            </div>
            <button type="submit" class="btn btn-success" name="createUserButton">
                <i class='fas fa-user'></i> Toevoegen</button>
        </form>

        <br />
        <p><a href="/adminDashboard" class="btn btn-success">Terug</a></p>

    </div>

<c:import url="partials/footer.jsp" />