<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link id="gardenId" data-gardenId="${garden.gardenId}" />

<div>
    <div class="row">
        <div class="col-8">
            <h2 id="messageToggle"><i class="fa fa-comments"></i> Berichten</h2>
        </div>
        <div class="col-4" align="right">
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
        Er zijn nieuwe berichten! <span class="ajax-link" id="getNewMessages">Verversen</span>
    </div>

        <div id="message-container"></div>

        <button type="button" class="btn btn-success" id="msgNextBtn">Oudere berichten</button>

        </div>
</div>


<!-- Import Handlebars templates -->
<c:import url="partials/templates/newMessageTemplate.jsp" />
<c:import url="partials/templates/messageTemplate.jsp" />

<script src="../resources/javascript/messages.js"></script>

