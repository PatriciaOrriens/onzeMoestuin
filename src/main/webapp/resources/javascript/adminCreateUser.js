$(document).ready(function() {

    var userRights = new Array ("USER", "ADMIN");

    for (i = 0; i < userRights.length; i++) {
        $(role).append('<option>'+userRights[i]+'</option>')
    }

});