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
            <li><a href="/adminManageUsersAndGardens" name="selectManageUser">Beheer gebruikers</a></li>
            <li><a href="/adminManagePlantInformation" name="selectManagePlantInformation">Beheer plantinformatie</a></li>
        </ul>
        <br/>
        <a href="/logout" class="btn btn-primary" name="adminLogoutButton">Logout</a>
    </body>
    <c:import url="partials/footer.jsp" />
</html>





