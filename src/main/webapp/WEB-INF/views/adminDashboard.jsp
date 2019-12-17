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
            <tr>
                <form action="/doTask" method="post">
                    <td>Task options:</td>
                    <td>
                        <select id="welcome" type='text' name="menuOption">
                            <c:forEach items="${menuOptions}" var="menuOption">
                                <option value=${menuOption}>${menuOption.toString()}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="submit" class="button" value="OK">
                    </td>
                </form>
            </tr>
            <tr>
                <td>
                    <form action="/logout">
                        <input type="submit" name="logoutButton" value="Logout">
                    </form>
                </td>
                <td>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </body>
</html>




