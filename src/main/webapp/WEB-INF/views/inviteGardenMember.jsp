<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
    <h1 class="display-3">Iemand uitnodigen voor '${garden.gardenName}'</h1>

    <p>Voer de naam van de gebruiker in om deze uit te nodigen.</p>

    <form method="get">

        <div class="form-group">
            <label for "name">Naam</label>
            <input name="search" class="form-control" type="text" />
        </div>
        <button type="submit" class="btn btn-primary">Zoek</button>

    </form>

    <!-- Display notification if present -->
    <c:choose>
        <c:when test="${!empty message.message}">
            <p>
            <div class="alert alert-warning" role="alert">
               ${message.message}
            </div>
            </p>
        </c:when>
        <c:otherwise>
             <c:if test="${!empty foundUser}">
               <h2>Gevonden gebruiker:</h2>
               <p>${foundUser.username}</p>

               <form:form method="post" action="../${garden.gardenId}/invite" modelAttribute="foundUser">
                   <form:hidden path="userId" />
                 <form:button type="submit" name="submit_param" class="btn btn-primary">
                   Uitnodigen
                 </form:button>
               </form:form>
             </c:if>
         </c:otherwise>
   </c:choose>



<c:import url="partials/footer.jsp" />
