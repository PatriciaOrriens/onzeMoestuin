// script to search in table with plantinformation
$(document).ready(function() {
    $("#input").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#plantsTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

});

$(document).ready(function () {
    // click on table body
    $("#tablePlants tbody tr").click(function () {
        //get row contents into an array
        var tableData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();

    });
});
