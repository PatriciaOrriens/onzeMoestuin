$(document).ready(function() {

    var userRights = new Array ("USER", "ADMIN");

    for (i = 0; i < userRights.length; i++) {
        $(rights).append('<option>'+userRights[i]+'</option>')
    }
});