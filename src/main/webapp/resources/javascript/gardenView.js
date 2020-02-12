// Make Spring Security allow AJAX
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(loadGrid);

// Set size of GiridStack elements
$(document).ready(function() {
    loadGrid();
    ajaxGetUnstartedPlants();
});


$('.plantSpan').on('click', function(e) {
    console.log("lol");
//    var plant = {
//            plantId: $(this).attr('data-newPlantId'),
//            width: 1,
//            height: 1
//        };
//    //TODO use templating to improve maintainability
//    var el = $.parseHTML("<div class=\"gid-stack-item\" data-gs-locked=\"yes\" data-plantId=\"" + plant.plantId +
//    "\"><div class=\"grid-stack-item-content\"><section class=\"vertical-align-grid-icon\" id=\"" + plant.plantId + "\">" +
//     "<img src=\"/plant/" + plant.plantId + "/image\", name=\"image\" height=\"50%\" id=" + plant.plantId + "\"/>" +
//     "<br />" + $(this).text() + "</div></div>");
//
//    var grid = $('.grid-stack').data('gridstack');
//    var newWidget = grid.addWidget(el, null, null, plant.width, plant.height, true);
//    var node = newWidget.data('_gridstack_node');
//    Object.assign(plant, {xCoordinate: node.x, yCoordinate: node.y});
//    ajaxStartPlant(plant);
//    loadGrid();
});

// Load GridStack script
function loadGrid() {

    var options = {
        acceptWidgets: '.newWidget',
        // Cell/element size
        cellHeight: 'auto',
        itemClass: 'grid-stack-item',
        cellHeightUnit:'px'
    };

    $('.grid-stack').gridstack(options);

    // explicitly set the number of columns
    columns = $('.grid-stack').attr('data-gs-column');
    $('.grid-stack').data('gridstack').setColumn(columns);


    // Trigger event when clicked on grid element or child
    $('.grid-stack-item').on('click', function(e) {
        if (e.target.id) {
            ajaxGetPlant(e.target.id);
        }
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

}



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


// Get plants added but not yet placed on grid

//TODO
// Get next messages for garden {}

function ajaxGetUnstartedPlants() {
    $.ajax({
        type: "GET",
        url: "../api/garden/" + $(gardenId).attr("data-gardenId") + "/getPlannedPlants",
        dataType: 'json',
        success: function(response) {
            // Check if plants are returned
            if (response.length > 0) {
                // View HTML container that displays planned plants
                $("#plannedPlants").toggle();
                plannedPlantsHTML(response);

            }
        },
       error: function() {

       }
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

function ajaxStartPlant(plant) {
   $.ajax({
       type: "POST",
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       url: "/api/plant/start",
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

//TODO refactor into reusable function
// Handlebars generating html for planned plants {}
function plannedPlantsHTML(plantData) {
        // load Handlebars template from id in html file {}
        var rawTemplate = document.getElementById("plannedPlantsTemplate").innerHTML;
        // create dynamic template function
        var compiledTemplate = Handlebars.compile(rawTemplate);
        // populate template with JSON data, generate string of HTML
        var ourGeneratedHTML = compiledTemplate(plantData);
        // add html to DOM
        var messageContainer = document.getElementById("plannedPlants-container");
        messageContainer.innerHTML = ourGeneratedHTML;
}


// Handlebars generating HTML {}
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