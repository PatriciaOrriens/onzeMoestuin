<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Stuur een uitnodiging naar ${invitationMail.recipient}</h2>

<!-- JSTL form -->
<form:form action="/garden/${garden.gardenId}/sendEmailInvite" modelAttribute="invitationMail">
    <div class="form-group">
        <form:hidden path="recipient" />
        <label for="name">Onderwerp:</label>
        <form:input path="subject" class="form-control" value="Onze Moestuin: Je bent uitgenodigd voor ${garden.gardenName}!"/>

        <label for="name">Uitnodiging:</label>
        <form:textarea path="body" rows="10" class="form-control"/>

    </div>
    <form:button type="submit" name="sendMail" class="btn btn-primary">Versturen</form:button>
</form:form>
