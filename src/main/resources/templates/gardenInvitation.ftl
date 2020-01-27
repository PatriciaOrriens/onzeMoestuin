<!DOCTYPE html>
<html>

    <body>
        <h1>Je bent uitgenodigd voor ${garden.gardenName}</h1>
        <p>${(sender.firstName)!"Iemand"} heeft je uitgenodigd om lid te worden voor zijn of haar tuin!</p>

        <#if body?has_content>
        <p><q>${body}</q></p>
        </#if>

        <p>Klik <a href="http://localhost:8080/registerUser?token=${invitation.invitationToken}">hier</a> om te accepteren</p>
    </body>

</html>