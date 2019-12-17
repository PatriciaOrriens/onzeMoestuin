<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome Menu</title>
    </head>
    <body>
        <h1> Welcome, administrator </h1>
        <table>
            <tr><td>Tasks:</td><td></td><td></td></tr>
            <tr><td></td><td><a href="/adminManageUsersAndGardens">Manage Users</a></td><td></td></tr>
            <tr><td></td><td><a href="/adminManagePlantInformation">Manage Plant Information</a></td><td></td></tr>
            <tr><td></td><td>.</td><td></td></tr>
            <tr><td><form action="/logout"><input type="submit" class="logout" value="Logout"></form></td><td></td><td></td></tr>
        </table>
    </body>
</html>




