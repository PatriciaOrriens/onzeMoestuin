<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link id="gardenId" data-gardenId="${garden.gardenId}" />


<div class="container mt-3">
<h2>Berichten bij de tuin:</h2>
   <!-- <table class="table table-striped">

        <c:forEach items="${messages}" var="message">
            <tr>
                <td><h5><c:out value="${message.sender.username}" /></h5><small><c:out value="${message.dateTime}" /></small> </td>
                <td><c:out value="${message.messageBody}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <br/> -->

    <div id="message-container"></div>


    <button type=""button" class="btn btn-success" data-toggle="collapse" data-target="#newMessageForm">Nieuw bericht</button>
    <br/><br/>
    <div id="newMessageForm" class="collapse">
        <!-- JSTL form -->
        <form:form action="/garden/${garden.gardenId}/newMessage" modelAttribute="newMessage" >
            <div class="form-group">
                <label>Schrijf jouw bericht:</label>
                <form:textarea path="messageBody" rows="4" class="form-control"/>
            </div>
        <form:button type="submit" name="sendMessage" class="btn btn-success">Versturen</form:button>
        </form:form>
    </div>
</div>

<script id="messageTemplate" type="text/x-handlebars-template">
    <table class="table table-striped">
        {{#each this}}
            <tr>
                <td><h5>{{sender.username}}</h5><small>{{dateTime}}</small> </td>
                <td>{{messageBody}}</td>
            </tr>
        {{/each}}
    </table>
</script>


<script src="../resources/javascript/messages.js"></script>

