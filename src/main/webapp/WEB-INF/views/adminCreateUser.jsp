<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<html>
    <head>
        <title>Nieuwe gebruiker</title>
    </head>
    <body>
        <h2>Voeg nieuwe gebruiker toe</h2>
        <br/>
        <form action="/user/new" modelAttribute="user">
            <table>
                <tr>
                    <td>Inlognaam:</td>
                    <td>
                        <input path="username" />
                    </td>
                </tr>
                <tr>
                    <td>Wachtwoord:</td>
                    <td>
                        <input path="password" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Voeg toe" />
                    </td>
                </tr>
            </table>
        </form>
        <br/>
        <br/>
        <a href="/adminDashboard" class="btn btn-primary">Terug naar taakmenu</a>
    </body>
</html>

<c:import url="partials/footer.jsp" />