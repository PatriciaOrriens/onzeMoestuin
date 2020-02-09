$(document).ready(function() {

    var roleName = new Array ("ROLE_USER", "ROLE_ADMIN");

    for (i = 0; i < roleName.length; i++) {
        $(rights).append('<option>'+roleName[i]+'</option>')
    }
});