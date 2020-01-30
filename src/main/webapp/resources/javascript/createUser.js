function validateForm() {
    var x = document.forms["userForm"]["username"].value;
    if (x.length < 3) {
        alert("Gebruikersnaam moet minimaal 3 tekens lang zijn");
        return false;
    }
    var y = document.forms["userForm"]["password"].value;
    if (y.length < 3) {
        alert("Wachtwoord moet minimaal 3 tekens lang zijn");
        return false;
    }
}
