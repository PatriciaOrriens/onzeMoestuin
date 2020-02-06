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
                $("#message-error").hide();
                messageHTML(response);
                page++;
            },
           error: function() {
                $("#message-error > p").html("<strong>Sorry!</strong> Berichten konden niet worden opgehaald.");
                $("#message-error").fadeIn("slow");
           }
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
           success: function(response) {
                $("#message-error").hide();
                newMessageHTML(response);
                $(".new-message").fadeIn("slow");
           },
           error: function() {
                $("#message-error > p").html("<strong>Sorry!</strong> Fout bij het plaatsen van je bericht");
                $("#message-error").fadeIn("slow");
           }
       });
    }


    msgNextBtn.addEventListener("click", function() {
        ajaxGetMessages();
    });

    postMsgBtn.addEventListener("click", function() {
        ajaxPostMessage();
    });

    legen.addEventListener("click", function() {
        $("#message-container").empty();
        page = 0;
        ajaxGetMessages();
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

    // Parse newly posted message
    function newMessageHTML(messageData) {
            // load Handlebars template from id in html file {}
            var rawTemplate = document.getElementById("newMessageTemplate").innerHTML;
            // create dynamic template function
            var compiledTemplate = Handlebars.compile(rawTemplate);
            // populate template with JSON data, generate string of HTML
            var ourGeneratedHTML = compiledTemplate(messageData);
            // add html to DOM
            var messageContainer = document.getElementById("message-container");
            messageContainer.innerHTML = ourGeneratedHTML + messageContainer.innerHTML;
    }
});