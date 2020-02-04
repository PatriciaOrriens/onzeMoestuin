// Make Spring Security allow AJAX
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

// Button to add new plant
var btnAddPlant = document.getElementById("btnAddPlant");
btnAddPlant.addEventListener("click", function() {
    alert("ADD PLANT");
});


// GridStack script

$('.grid-stack').gridstack({
    acceptWidgets: '.newWidget'
});

$('.grid-stack-item').on('click', function(e) {
    ajaxGetPlant(e.target.id);
});


// Get new height & width on resize {}
$('.grid-stack').on('gsresizestop', function(event, elem) {
    plant = {
        plantId: $(elem).attr('data-plantId'),
        width: $(elem).attr('data-gs-width'),
        height: $(elem).attr('data-gs-height')
    }
    resizePlant(plant);
});


$('.grid-stack').on('dragstart', function(event, ui) {
    var grid = this;
    var element = event.target;
    $(element).removeClass('onclick');
});


// Get new x/y coordinate on moving
$('.grid-stack').on('dragstop', function(event, ui) {
      var grid = this;
      var elem = $(event.target);
      var node = elem.data('_gridstack_node');
      plant = {
        plantId: $(elem).attr('data-plantId'),
        xCoordinate: node.x,
        yCoordinate: node.y
      }
      movePlant(plant);
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
//        error: function(e) {
//            console.log("ERROR: ", e);
//        }
    });
}

function resizePlant(plant) {
   $.ajax({
       type: "POST",
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       url: "/api/plant/resize",
       data: JSON.stringify(plant),
       success: function(result) {

       }
   });
}

function movePlant(plant) {
       $.ajax({
           type: "POST",
           contentType: 'application/json; charset=utf-8',
           dataType: 'json',
           url: "/api/plant/move",
           data: JSON.stringify(plant),
           success :function(result) {

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