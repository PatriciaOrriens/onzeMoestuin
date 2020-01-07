<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
  <div class="container">
        <h1 class="display-3">Tuin Overzicht </h1>

        <table class="table table-striped">
            <c:forEach items="${allYourGardens}" var="garden">
                <tr>
                    <td>
                        <a href="garden/${garden.gardenId}"><c:out value="${garden.gardenName}" /></a>
                    </td>
                    <td align="right"><a href="/user/garden/delete/${garden.gardenId}">Verwijder</a></td>
                </tr>
            </c:forEach>
        </table>

        <a href="/garden/add" class="btn btn-primary">Tuin toevoegen</a>
        <a href="../logout" class="btn btn-primary">Uitloggen</a>

<c:import url="partials/footer.jsp" />
