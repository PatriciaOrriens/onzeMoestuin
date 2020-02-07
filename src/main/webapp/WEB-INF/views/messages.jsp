<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link id="gardenId" data-gardenId="${garden.gardenId}" />

<div class="container mt-3">
    <h2 id="messageToggle">Berichten bij de tuin:</h2>
    <div id="messageDiv">

    <!-- AJAX error -->
    <div class="alert alert-danger" id="message-error"><p></p></div>
    <div class="alert alert-info" id="new-messages-alert"><p></p></div>

        <div id="message-container"></div>

        <button type="button" class="btn btn-success" id="msgNextBtn">Oudere berichten</button>

        <button type="button" class="btn btn-success" data-toggle="collapse" data-target="#newMessageForm">Nieuw bericht</button>
        <button type="button" class="btn btn-success" id="legen">Reset</button>

        <br/><br/>
        <div id="newMessageForm" class="collapse">
            <textarea id="messageText" rows="2" class="form-control"></textarea><br />
            <button type="button" class="btn btn-success" id="postMsgBtn">Versturen</button>

        </div>
        </div>

<script id="messageTemplate" type="text/x-handlebars-template">
    <table class="table table-striped">
    {{#each this}}
        <tr>
            <td class="msg-header"><h5>{{sender.username}}</h5><small>{{formattedDateTime}}</small> </td>
            <td class="msg-body">{{messageBody}}</td>
        </tr>
    {{/each}}
    </table>
</script>

<script id="newMessageTemplate" type="text/x-handlebars-template">
    <table class="table table-striped new-message">
        <tr>
            <td class="msg-header"><h5>{{sender.username}}</h5><small>{{formattedDateTime}}</small> </td>
            <td class="msg-body">{{messageBody}}</td>
        </tr>
    </table>
</script>

<script src="../resources/javascript/messages.js"></script>

