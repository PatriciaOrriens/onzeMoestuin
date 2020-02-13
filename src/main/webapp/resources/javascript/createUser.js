function validateForm() {
    document.getElementById("usernameError").innerHTML = "";
    document.getElementById("emailLengthError").innerHTML = "";
    document.getElementById("emailStructureError").innerHTML = "";
    document.getElementById("passwordError").innerHTML = "";
    var isValid = true;
    var username = document.forms["userForm"]["username"].value;
    if (username.length < 3) {
        document.getElementById("usernameError").innerHTML = "Gebruikersnaam moet minimaal 3 tekens lang zijn";
        isValid = false;
    }
     var email = document.forms["userForm"]["email"].value;
    if (email.length < 3) {
         document.getElementById("emailLengthError").innerHTML = "E-mailadres moet minimaal 3 tekens lang zijn";
         isValid = false;
    }
    var token = true;
    for (i = 0; i < email.length; i++) {
        if (email[i] === "@") {
            token = false;
        }
    }
    if (token) {
        document.getElementById("emailStructureError").innerHTML = "E-mailadres moet @ teken bevatten";
        isValid =  false;
    }
    var password = document.forms["userForm"]["password"].value;
        if (password.length < 3) {
            document.getElementById("passwordError").innerHTML = "Wachtwoord moet minimaal 3 tekens lang zijn";
            isValid = false;
    }
    return isValid;
}
