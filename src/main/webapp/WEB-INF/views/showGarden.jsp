<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<c:import url="partials/header.jsp" />



<c:import url="partials/garden2d.jsp" />

<br/><br/>

<div class="container">
    <div class="row">
        <div class="col-6">
            <c:import url="messages.jsp" />
        </div>
        <div class="col-6">
            <c:import url="partials/showFirstTasks.jsp" />
        </div>
    </div>
</div>

<c:import url="partials/footer.jsp" />
