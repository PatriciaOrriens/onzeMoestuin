<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
  <div class="container">
        <h1 class="display-3">Welkom beheerder </h1>
        <br/>
        <h2>Kies een taak:</h2>
        <ul>
            <li><a href="/manageUsers" name="selectManageUser">Beheer gebruikers</a></li>
            <li><a href="/adminManagePlantInformation" name="selectManagePlantInformation">Beheer plantinformatie</a></li>
            <li><a href="/adminManageTasks" name="selectManageTasks">Beheer taken</a></li>
        </ul>

<c:import url="partials/footer.jsp" />





