<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <ul class="navbar-nav">
     <li class="nav-item">
        <a class="nav-link" href="../">Home</a>
      </li>
    <li class="nav-item">
      <a class="nav-link" href="/garden/add">Tuin toevoegen</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/registerUser">Registreren</a>
    </li>
    <li class="nav-item">
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a class="nav-link" href="/adminDashboard">Admin dashboard</a>
        </security:authorize>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/userManageGardens">Tuin overzicht</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/login">Inloggen</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/logout">Uitloggen</a>
    </li>

   </ul>
</nav>