function validateForm() {
    document.getElementById("taskGardenNameError").innerHTML = "";
    document.getElementById("dateLengthError").innerHTML = "";
    document.getElementById("dateStructureError").innerHTML = "";
    var isValid = true;
    var taskGardenName = document.forms["taskGardenForm"]["taskGardenName"].value;
    if (taskGardenName.length < 3) {
        document.getElementById("taskGardenNameError").innerHTML = "Taakomschrijving moet minimaal 3 tekens lang zijn";
        isValid = false;
    }
    var dueDate = document.forms["taskGardenForm"]["dueDate"].value;
    if (dueDate.length < 10 || dueDate.length > 10) {
         document.getElementById("dateLengthError").innerHTML = "Vervaldatum moet 10 tekens lang zijn";
         isValid = false;
    }
    var count = 0;
    if (dueDate[2] === "-") {
        count = count + 1;
    }
    if (dueDate[5] === "-") {
        count = count + 1;
    }
    if (count < 2 || count > 2) {
        document.getElementById("dateStructureError").innerHTML = "Aantal/Positionering - tekens klopt niet in vervaldatum";
        isValid =  false;
    }
    return isValid;
}