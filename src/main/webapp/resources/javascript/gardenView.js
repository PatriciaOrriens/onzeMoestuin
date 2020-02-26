// Make Spring Security allow AJAX
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});


// Load GridStack & get plants
$(document).ready(function() {
    loadGrid();
    ajaxGetUnstartedPlants();
});


// Add plant to garden grid
$("body").on("click", ".plantStartBtn", function(e) {
    // Set default values
    var plant = {
        plantId: $(this).attr('data-newPlantId'),
        width: 1,
        height: 1
    };

    // Generate element HTML
    //TODO use templating to improve maintainability
    var el = $.parseHTML("<div class=\"gid-stack-item\" data-gs-locked=\"yes\" data-plantId=\"" + plant.plantId +
    "\"><div class=\"grid-stack-item-content\"><section class=\"vertical-align-grid-icon\" id=\"" + plant.plantId + "\">" +
     "<img src=\"/plant/" + plant.plantId + "/image\", name=\"image\" height=\"50%\" id=\"" + plant.plantId + "\">" +
     "<br />" + $(this).attr('data-plantName') + "</div></div>");

    var grid = $('.grid-stack').data('gridstack');
    // Define new widget, set location values to null to make GridStack automatically find a free spot
    var newWidget = grid.addWidget(el, null, null, plant.width, plant.height, true);
    var node = newWidget.data('_gridstack_node');
    Object.assign(plant, {xCoordinate: node.x, yCoordinate: node.y});

    // Add to grid & refresh
    $.when(ajaxStartPlant(plant)).then(ajaxGetUnstartedPlants());
    loadGrid();
});


// Load GridStack script
function loadGrid() {

    var options = {
        acceptWidgets: '.newWidget',
        cellHeight: 'auto',
        itemClass: 'grid-stack-item',
        cellHeightUnit:'px',
    };

    $('.grid-stack').gridstack(options);

    // explicitly set the number of columns
    columns = $('.grid-stack').attr('data-gs-column');
    $('.grid-stack').data('gridstack').setColumn(columns);


    // Open plant details modal on click
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
        // Prevent opening modal when dragging
        $(element).removeClass('onclick');
    });


    // Get new x/y coordinate on dragging
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



// AJAX functions

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
        error: function(xhr, status, error) {
             var errorMessage = xhr.status + ": " + xhr.statusText;
             alert("Error: " + errorMessage);
        }
    });
}


// Get plants added but not yet placed on grid
function ajaxGetUnstartedPlants() {
    return $.ajax({
        type: "GET",
        url: "../api/garden/" + $(gardenId).attr("data-gardenId") + "/getPlannedPlants",
        dataType: 'json',
        success: function(response) {
            // Check if plants are returned
            if (response.length > 0) {
                // View HTML container that displays planned plants
                $("#plannedPlants").show();
                plannedPlantsHTML(response);

            } else {
                $("#plannedPlants").hide();
            }
        },
        error: function(xhr, status, error) {
             var errorMessage = xhr.status + ": " + xhr.statusText;
             alert("Error: " + errorMessage);
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
       error: function(xhr, status, error) {
            var errorMessage = xhr.status + ": " + xhr.statusText;
            alert("Error: " + errorMessage);
       }
   });
}


// Update plant when added to garden grid
function ajaxStartPlant(plant) {
   return $.ajax({
       type: "POST",
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       url: "/api/plant/start",
       data: JSON.stringify(plant),
       success: function(response) {
            ajaxGetUnstartedPlants();
       },
       error: function(xhr, status, error) {
          var errorMessage = xhr.status + ": " + xhr.statusText;
          alert("Error: " + errorMessage);
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
        error: function(xhr, status, error) {
             var errorMessage = xhr.status + ": " + xhr.statusText;
             alert("Error: " + errorMessage);
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

    harvestBtn.addEventListener("click", function() {
        $("#harvestDiv").slideToggle();
    });
}