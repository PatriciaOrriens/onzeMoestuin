// GridStack script

$('.grid-stack').gridstack({

});

$('.grid-stack-item').on('click', function(e) {
    ajaxGetPlant(e.target.id);
});

// Get new height & width on resize {}
$('.grid-stack').on('gsresizestop', function(event, elem) {
    var newHeight = $(elem).attr('data-gs-height');
    var newWidth = $(elem).attr('data-gs-width');
    console.log("Height: " + newHeight + ", Width: " + newWidth);
});

$('.grid-stack').on('dragstart', function(event, ui) {
    var grid = this;
    var element = event.target;
    $(element).removeClass('onclick');
});

// Get new x/y coordinate on moving
$('.grid-stack').on('dragstop', function(event, ui) {
      var grid = this;
      var elem = event.target;
      var newX = $(elem).attr('data-gs-x');
      var newY = $(elem).attr('data-gs-y');
      console.log("New x: " + newX + ", new Y: " + newY);
});

// AJAX code

// Get plant details when clicked {}
function ajaxGetPlant(plantId) {
    $.ajax({
        type: "GET",
        url: "../api/getPlant/" + plantId,
        dataType: 'json',
        success: function(response) {
            plantHTML(response);
            $('#plantModal').modal('show')
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}
<!-- Handlebars generating HTML -->
function plantHTML(plantData) {
    // load Handlebars template from id in html file {}
    var rawTemplate = document.getElementById("plantTemplate").innerHTML;
    // create dynamic template function
    var compiledTemplate = Handlebars.compile(rawTemplate);
    // populate template with JSON data, generate string of HTML
    var ourGeneratedHTML = compiledTemplate(plantData);
    // add html to DOM
    var plantContainer = document.getElementById("plantContainer");
    plantContainer.innerHTML = ourGeneratedHTML;
}