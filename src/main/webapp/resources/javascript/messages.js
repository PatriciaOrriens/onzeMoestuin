$(document).ready(function() {
    var page = 0;
    ajaxGetMessages();


    // Get next messages for garden {}
    function ajaxGetMessages() {
        $.ajax({
            type: "GET",
            url: "../api/garden/" + $(gardenId).attr("data-gardenId") + "/recentMessages",
            dataType: 'json',
            success: function(response) {
                messageHTML(response);
            },
        });
    }

    function messageHTML(messageData) {
        // load Handlebars template from id in html file {}
        var rawTemplate = document.getElementById("messageTemplate").innerHTML;
        // create dynamic template function
        var compiledTemplate = Handlebars.compile(rawTemplate);
        // populate template with JSON data, generate string of HTML
        var ourGeneratedHTML = compiledTemplate(messageData);
        // add html to DOM
        var messageContainer = document.getElementById("message-container");
        messageContainer.innerHTML = ourGeneratedHTML;

    }

});