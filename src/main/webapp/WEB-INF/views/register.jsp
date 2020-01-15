<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

     <div class="container">
        <h1 class="display-3">Registreren</h1>


       <form:form method="post" modelAttribute="user">
            <div class="form-group">
                <label for="name">Gebruikersnaam:</label>
                <form:input path="username" class="form-control" required="required" />

                <label for="password">Wachtwoord:</label>
                <form:input path="password" type="password" class="form-control" required="required" />
            </div>
            <form:button type="submit" class="btn btn-primary" name="registerbutton">Registreer</form:button>
        </form:form>

  <c:import url="partials/footer.jsp" />
