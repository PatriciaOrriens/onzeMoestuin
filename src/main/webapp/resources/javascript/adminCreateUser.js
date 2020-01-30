function validateForm() {
  var x = document.forms["userForm"]["username"].value;
  if (x.length < 3) {
    alert("Gebruikersnaam moet minimaal 3 tekens lang zijn");
    return false;
  }
}
