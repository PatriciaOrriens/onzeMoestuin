<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

     <div class="container">
        <h1 class="display-3">Inloggen</h1>
       <form:form method="post" modelAttribute="user">
            <div class="form-group">
                <label for="name">Gebruikersnaam:</label>
                <form:input path="username" class="form-control" required="required" />
            </div>
            <div>
                <label for="password">Wachtwoord:</label>
                <form:input path="password" type="password" class="form-control" required="required" />
            </div>
            <br/>
            <form:button type="submit" class="btn btn-success" name="inlogbutton">Inloggen</form:button>
        </form:form>

<c:import url="partials/footer.jsp" />