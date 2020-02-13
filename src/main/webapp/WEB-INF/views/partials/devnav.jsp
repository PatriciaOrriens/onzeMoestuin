<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-sm bg-success navbar-dark">
  <ul class="navbar-nav">
         <li class="nav-item">
            <a class="navbar-brand" href="/">Onze Moestuin</a>
         </li>
     <security:authorize access="hasRole('ROLE_ADMIN')">
         <li class="nav-item">
            <a class="nav-link" href="/adminDashboard" name="admindashboard">Admin dashboard</a>
         </li>
     </security:authorize>
     <security:authorize access="hasRole('ROLE_USER')">
         <li class="nav-item">
            <a class="nav-link" href="/userManageGardens">Tuin overzicht</a>
         </li>
     </security:authorize>
     <security:authorize access="hasRole('ROLE_USER')">
         <li class="nav-item">
            <a class="nav-link" name="tuintoevoegen" href="/garden/add">Tuin toevoegen</a>
         </li>
     </security:authorize>
  </ul>

  <ul class="nav navbar-nav ml-auto">

    <security:authorize access="isAnonymous()">
        <li class="nav-item">
            <a class="nav-link" href="/registerUser">Registreren</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/login">Inloggen</a>
        </li>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <li class="nav-item">
            <a class="nav-link disabled">
                <i class="fa fa-user"></i> ${pageContext.request.userPrincipal.name}
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" name="logout" href="/logout">Uitloggen</a>
        </li>
    </security:authorize>

  </ul>
</nav>