<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <title>Takenlijst voor beheerder</title>
    </head>
    <body>
        <h1> Welkom beheerder </h1>
        <br/>
        <h2>Taken: </h2>
        <ul>
            <li><a href="/adminManageUsersAndGardens">Beheer gebruikers</a></li>
            <li><a href="/adminManagePlantInformation">Beheer plantinformatie</a></li>
        </ul>
        <br/>

        <form action="/logout"><input type="submit" class="logout" value="Logout"></form>

    </body>
    <c:import url="partials/footer.jsp" />
</html>





