<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="partials/header.jsp" />

 <div class="container">
        <h1 class="display-3">Taken bij ${plantInfo.plantName}</h1>
        <table class="table table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Taak</th>
                        <th>Dagen na startdatum plant</th>
                        <th>Moet herhaald worden</th>
                        <th>Wijzig taak</th>
                        <th>Verwijder taak</th>
                    </tr>
                <c:forEach items="${plantInfo.tasks}" var="task">
                    <tr>
                        <td>${task.taskPlantInfoId}</td>
                        <td>${task.taskDescription.taskName}</td>
                        <td>${task.daysAfterStart}</td>
                        <td>${task.repetitiveTask}</td>
                        <td><a class="btn btn-success" href="/plantinfo/${plantInfoId}/task/update/<c:out
                        value="${task.taskPlantInfoId}" />"
                            ><i class='far fa-edit'></i></a></td>
                        <td><a class="btn btn-warning" href="#removeTaskPlantModal_${task.taskPlantInfoId}" data-toggle="modal"><i class='fas fa-trash-alt'></i></a></td>
                    </tr>

                    <!-- Modal -->
                    <div class="modal fade" id="removeTaskPlantModal_${task.taskPlantInfoId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Taak verwijderen</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                    <div class="modal-body">
                                        <p>Weet je zeker dat je taak ${task.taskPlantInfoId} wilt verwijderen?</p>
                                    </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Terug</button>
                                    <a type="button" class="btn btn-secondary" href="/plantinfo/task/delete/<c:out value="${task.taskPlantInfoId}" />" name="modal-verwijderen" title="Delete"><i class="fa fa-trash-o"></i>Verwijderen</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
        </table>

        <p><a class="btn btn-success" href="/plantinfo/${plantInfo.plantInfoId}/task/add">Taak toevoegen</a></p>
        <br/>
        <p><a class="btn btn-success" href="/adminManagePlantInformation">Terug</a></p>

<c:import url="partials/footer.jsp" />
