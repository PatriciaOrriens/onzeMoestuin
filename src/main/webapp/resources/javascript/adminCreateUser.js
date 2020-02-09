$(document).ready(function() {

    var roleName = new Array ("USER", "ADMIN");

    for (i = 0; i < roleName.length; i++) {
        $(rights).append('<option>'+roleName[i]+'</option>')
    }
});