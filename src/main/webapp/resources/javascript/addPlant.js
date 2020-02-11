// script to search in table with plantinformation
$(document).ready(function() {
    $("#input").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#plantsTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

});