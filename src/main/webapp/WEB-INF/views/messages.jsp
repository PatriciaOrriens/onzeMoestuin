<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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