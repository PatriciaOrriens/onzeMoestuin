function validateForm() {
    var username = document.forms["userForm"]["username"].value;
    if (username.length < 3) {
        alert("Gebruikersnaam moet minimaal 3 tekens lang zijn");
        return false;
    }
    var email = document.forms["userForm"]["email"].value;
    if (email.length < 3) {
        alert("E-mailadres moet minimaal 3 tekens lang zijn");
        return false;
    }
    var token = true;
    for (i = 0; i < email.length; i++) {
        if (email[i] === "@") {
            token = false;
        }
    }
    if (token) {
        alert("E-mailadres moet @ teken bevatten");
        return false;
    }
    var password = document.forms["userForm"]["password"].value;
    if (password.length < 3) {
        alert("Wachtwoord moet minimaal 3 tekens lang zijn");
        return false;
    }
}
