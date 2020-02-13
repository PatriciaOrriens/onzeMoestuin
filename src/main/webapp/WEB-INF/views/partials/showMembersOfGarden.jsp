<!-- Tijdelijke code om tuinleden weer te geven -->
<div class="container">
<h2>Leden</h2>
<ul>
    <c:forEach items="${garden.gardenMembers}" var="member">
        <li><c:out value="${member.username}" /></li>

    </c:forEach>
</ul>
<a href="/garden/${garden.gardenId}/invite" class="btn btn-success">
    <i class="fa fa-user-plus"></i> Lid toevoegen</a>

</div>