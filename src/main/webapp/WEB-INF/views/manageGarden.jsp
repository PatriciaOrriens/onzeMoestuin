<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Tuin Overzicht </h1>
        <table>
            <tr><th>Tuinnaam </th><th></th><th></th><th></th></tr>
            <c:forEach items="${allYourGardens}" var="garden">
                <tr>
                    <td><c:out value="${garden.gardenName}" /></td>
                    <td></td>
                    <td><a href="/garden/<c:out value="${garden.gardenId}" />">Toon</a></td>
                    <td></td>
                    <td><a href="/user/garden/delete/<c:out value="${garden.gardenId}" />">Verwijder</a></td>
                </tr>
            </c:forEach>
        </table>
        <h1></h1>
        <a href="/garden/add">Voeg tuin toe</a>
        <h1></h1>
        <h1></h1>
        <form action="/logout"><input type="submit" class="btn btn-primary" value="Logout"></form>
        <c:import url="partials/footer.jsp" />
    </body>
</html>