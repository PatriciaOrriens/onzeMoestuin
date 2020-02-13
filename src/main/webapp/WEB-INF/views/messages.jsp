<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link id="gardenId" data-gardenId="${garden.gardenId}" />

<div>
    <div class="row">
        <div class="col-8">
            <h2 id="messageToggle"><i class="fa fa-comments"></i> Berichten</h2>
        </div>
        <div class="col-4">
            <button type="button" class="btn btn-success" data-toggle="collapse" data-target="#newMessageForm">Nieuw bericht</button>
        </div>
    </div>
    <div id="messageDiv">

        <div id="newMessageForm" class="collapse">
            <textarea id="messageText" rows="1" class="form-control"></textarea><br />
            <button type="button" class="btn btn-success" id="postMsgBtn">Versturen</button><br/><br/>
        </div>

    <!-- AJAX error -->
    <div class="alert alert-danger" id="message-error"><p></p></div>
    <div class="alert alert-info" id="new-messages-alert">
        <p>Er zijn nieuwe berichten! <span class="ajax-link" id="getNewMessages">Verversen</span></p>
    </div>

        <div id="message-container"></div>

        <button type="button" class="btn btn-success" id="msgNextBtn">Oudere berichten</button>

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

