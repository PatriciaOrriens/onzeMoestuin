// Make Spring Security allow AJAX
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function() {
    var page = 0;
    ajaxGetMessages();


    // Get next messages for garden {}
    function ajaxGetMessages() {
        $.ajax({
            type: "GET",
            url: "../api/garden/" + $(gardenId).attr("data-gardenId") + "/messages/" + page,
            dataType: 'json',
            success: function(response) {
                messageHTML(response);
            },
        });
    }

    function ajaxPostMessage() {
        newMessage = { messageBody: document.getElementById("messageText").value }

       $.ajax({
           type: "POST",
           contentType: 'application/json; charset=utf-8',
           dataType: 'json',
           url: "../api/garden/" + $(gardenId).attr("data-gardenId") + "/messages/add",
           data: JSON.stringify(newMessage),
           success: function(result) {
                console.log(result);
           }
       });
    }


    msgNextBtn.addEventListener("click", function() {
        ajaxGetMessages();
        page++;
    });

    postMsgBtn.addEventListener("click", function() {
        ajaxPostMessage();
    });


    function messageHTML(messageData) {
        // Hide button when there are no more messages to load
        if (messageData.length == 0) {
            $("#msgNextBtn").hide();
        } else {
            // load Handlebars template from id in html file {}
            var rawTemplate = document.getElementById("messageTemplate").innerHTML;
            // create dynamic template function
            var compiledTemplate = Handlebars.compile(rawTemplate);
            // populate template with JSON data, generate string of HTML
            var ourGeneratedHTML = compiledTemplate(messageData);
            // add html to DOM
            var messageContainer = document.getElementById("message-container");
            messageContainer.innerHTML += ourGeneratedHTML;
        }
    }

});