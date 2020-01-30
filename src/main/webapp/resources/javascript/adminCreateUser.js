function validateForm() {
  var x = document.forms["myForm"]["username"].value;
  if (x.length < 4) {
    alert("Gebruikersnaam moet minimaal 3 tekens lang zijn");
    return false;
  }
}
