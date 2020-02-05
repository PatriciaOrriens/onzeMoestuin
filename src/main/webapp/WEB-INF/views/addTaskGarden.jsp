<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />
<script src="../resources/javascript/addTaskGarden.js"></script>
  <div class="container">
	<h1 class="display-3">Nieuwe tuintaak aanmaken</h1>
	<!-- JSTL form -->
    <form:form name="taskGardenForm" method="post" modelAttribute="taskGarden" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="taskGardenName">Taakomschrijving:</label>
            <form:input name="taskGardenName" path="taskGardenName" class="form-control" />
            <p id="taskGardenNameError" class="centeredRedText">
                <span class="text-danger">
                    ${requestScope['org.springframework.validation.BindingResult.taskGarden'].hasFieldErrors('taskGardenName') ? requestScope['org.springframework.validation.BindingResult.taskGarden'].getFieldError('taskGardenName').defaultMessage : ''}
                </span>
            </p>


            <label for="dueDate">Vervaldatum (dd-mm-jjjj):</label>
            <form:input name="dueDate" path="dueDate" class="form-control" />
            <p id="dateLengthError" class="centeredRedText">
                <span class="text-danger">
                    ${requestScope['org.springframework.validation.BindingResult.taskGarden'].hasFieldErrors('dueDate') ? requestScope['org.springframework.validation.BindingResult.taskGarden'].getFieldError('dueDate').defaultMessage : ''}
                </span></p>
            <p id="dateStructureError" class="centeredRedText"></p>
            <p class="redText">${remark}</p>
        </div>
        <form:button type="submit" name="storeTaskGarden" class="btn btn-success">Opslaan</form:button>
	</form:form>
    
<c:import url="partials/footer.jsp" />
