<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Onze Moestuin</title>
  <base href="/">
  <c:import url="partials/style.jsp" />
  <c:import url="partials/script.jsp" />
</head>
<body>


<div class="jumbotron jumbotron-fluid">
  <div class="container">
  <div class="inner">
    <h1 class="display-3">Welkom bij Onze Moestuin!</h1>
    <p>Dit is de plek om je moestuin digitaal te beheren.<br>
    Met deze app krijg je tips voor nieuwe planten en krijg je hulp om je tuin in te delen.</p>
      <p class="lead">
    <a class="btn btn-success btn-lg" href="/login">Inloggen</a>

    <a class="btn btn-success btn-lg" href="/registerUser">Registreren</a>

  </p>
  </div>
  </div>

</div>

<c:import url="partials/footer.jsp" />