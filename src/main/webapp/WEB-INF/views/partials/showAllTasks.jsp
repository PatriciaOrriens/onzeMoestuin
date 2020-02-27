<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


     <table class="table table-striped">
        <tr>
            <th>Taak</th>
            <th>Plantsoort(plantnummer)</th>
            <th>Vervaldatum</th>
            <th>Uitvoerdatum</th>
            <th>Uitgevoerd door</th>
            <th>Afvinken</th>
        </tr>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>
                    <c:catch var="catchException">
                        <c:out value="${task.taskPlantInfo.taskDescription.taskName}" />
                    </c:catch>
                    <c:catch var="catchException">
                        <c:out value="${task.taskGardenName}" />
                    </c:catch>
                </td>
                <td>
                    <c:catch var="catchException">
                        <c:out value="${task.plant.plantInformation.plantName}"/>(<c:out value="${task.plant.plantId}"/>)
                    </c:catch>
                </td>
                    <c:choose>
                        <c:when test="${empty task.completedDate}"><td class="redText"></c:when>
                        <c:otherwise><td></c:otherwise>
                    </c:choose>
                    <c:out value="${task.dueDate}"/>
                </td>
                <td><c:out value="${task.completedDate}"/></td>
                <td><a onclick = "ajaxGetUser(${task.user.userId})" /><c:out value="${task.user.username}"/></a></td>
                <td>
                    <c:if test="${empty task.user}">
                        <a class="btn btn-success" href="/user/task/completed/<c:out value="${task.taskId}" />"><i class="fa fa-check"></i></a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
     </table>


    <!-- Modal to show User details-->
    <div id="userModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div id="userContainer"></div>
        </div>
    </div>

     <!-- Handlebars template for User modal -->
      <script id="userTemplate" type="text/x-handlebars-template">
         <div  class="modal-content">
           <div class="modal-header">
             <button type="button" class="close" data-dismiss="modal">&times;</button>
             <h4 class="modal-title">{{task.user.username}}</h4>
           </div>
           <div class="modal-body">
           <table class="table table-striped">
             <tr><th>Gebruikersnaam:</td><td>{{username}}</td></tr>
             <tr><th>E-mailadres:</th><td>{{email}}</td>
             <tr><th>Voornaam:</th><td>{{firstName}}</td></tr>
             <tr><th>Achternaam:</th><td>{{lastName}}</td></tr>
           </table>
           </div>
           <div class="modal-footer">
             <button type="button" class="btn btn-default" data-dismiss="modal">Sluit</button>
           </div>
         </div>
    </script>



<script src="../resources/javascript/showTask.js"></script>

