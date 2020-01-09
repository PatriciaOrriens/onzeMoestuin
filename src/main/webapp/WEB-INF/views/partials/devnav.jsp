<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="../resources/css/navbar.css">

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <ul class="navbar-nav">
     <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
         <li class="nav-item">
            <a class="nav-link" href="../">Home</a>
         </li>
     </security:authorize>
     <security:authorize access="hasRole('ROLE_ADMIN')">
         <li class="nav-item">
            <a class="nav-link" href="/adminDashboard">Admin dashboard</a>
         </li>
     </security:authorize>
     <security:authorize access="hasRole('ROLE_USER')">
         <li class="nav-item">
            <a class="nav-link" href="/userManageGardens">Tuin overzicht</a>
         </li>
     </security:authorize>
     <security:authorize access="hasRole('ROLE_USER')">
         <li class="nav-item">
            <a class="nav-link" href="/garden/add">Tuin toevoegen</a>
         </li>
     </security:authorize>
     <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
         <li class="nav-item">
            <a class="nav-link" href="/logout">Uitloggen</a>
         </li>
     </security:authorize>
  </ul>
</nav>