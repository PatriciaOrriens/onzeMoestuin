<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
<h2>Berichten bij de tuin:</h2>
    <table class="table table-striped">
        <tr>
            <th>afzender</th>
            <th>bericht</th>
        </tr>
        <c:forEach items="${messages}" var="message">
            <tr>
                <td><c:out value="${message.sender.username}" /> </td>
                <td><c:out value="${message.messageBody}" /> </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%--

    <!-- JSTL form -->
    <form:form action="/garden/${garden.gardenId}/newMessage" >
        <div class="form-group">

            <label>Jouw bericht:</label>
            <form:textarea path="body" rows="10" class="form-control"/>

        </div>
        <form:button type="submit" name="sendMessage" class="btn btn-success">Versturen</form:button>
    </form:form>
--%>
