function ajaxGetUser(userId) {
    $.ajax({
        type: "GET",
        url: "../api/getUser/" + userId,
        dataType: 'json',
        success: function(response) {
            userHTML(response);
            $('#userModal').modal('show')
        },
    });
}

function userHTML(userData) {
    var rawTemplate = document.getElementById("userTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var ourGeneratedHTML = compiledTemplate(userData);
    var userContainer = document.getElementById("userContainer");
    userContainer.innerHTML = ourGeneratedHTML;
}



