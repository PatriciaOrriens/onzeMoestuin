$(document).ready(function() {

    var lightingList = new Array ("", "zon", "halfschaduw", "schaduw");

    for (i = 0; i < lightingList.length; i++) {
        $(lighting).append('<option>'+lightingList[i]+'</option>')
    }

    var soilList = new Array ("", "standaard tuingrond", "kleigrond", "zandgrond", "kalkrijke grond");

    for (j = 0; j < soilList.length; j++) {
        $(soilType).append('<option>'+soilList[j]+'</option>')
    }

    var idList = new Array ("#sowingStart", "#sowingEnd", "#plantingStart", "#plantingEnd", "#harvestingStart", "#harvestingEnd");

    var monthList = new Array ("", "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september",
        "oktober", "november", "december");

    for (k = 0; k < idList.length; k++) {
        for (m = 0; m < monthList.length; m++) {
            $(idList[k]).append('<option>'+monthList[m]+'</option>')
        }
    }
});