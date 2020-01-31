<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

    <title>Nieuwe plantinformatie</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="../resources/javascript/adminCreatePlantInformationJavaScript.js"></script>

    <div class="container">
     <h1 class="display-3">Plant toevoegen</h1>
        <div class="form">
            <form:form method="post" action="/admincreateplantinfo?${_csrf.parameterName}=${_csrf.token}" modelAttribute="plantInformation" enctype="multipart/form-data">
                <table class="table-sm table-borderless">
                    <tr>
                        <td><label>Nederlandse plantnaam:</label></td>
                        <td><form:input path="plantName" type="text" placeholder="plantnaam" id="plantName"/></td>
                    </tr>
                    <tr>
                        <td><label>Latijnse plantnaam:</label></td>
                        <td><form:input path="latinName" type="text" placeholder="Latijnse plantnaam" id="latinName"/></td>
                    </tr>
                    <tr>
                        <td><label>Plantafstand (in cm's):</label></td>
                        <td><form:input path="plantingDistance" type="number" placeholder="plantafstand" min="0" id="plantingDistance"/></td>
                    </tr>
                    <tr>
                        <td><label>Belichting:</label></td>
                        <td><form:select path="lighting" type="text" id="lighting">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Grondsoort:</label></td>
                        <td><form:select path="soilType" type="text" id="soilType">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Zaaitijd (eerste maand):</label></td>
                        <td><form:select path="sowingStart" type="text" id="sowingStart">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Zaaitijd (laatste maand):</label></td>
                        <td><form:select path="sowingEnd" type="text" id="sowingEnd">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Planttijd (eerste maand):</label></td>
                        <td><form:select path="plantingStart" type="text" id="plantingStart">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Planttijd (laatste maand):</label></td>
                        <td><form:select path="plantingEnd" type="text" id="plantingEnd">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Oogsttijd (eerste maand):</label></td>
                        <td><form:select path="harvestingStart" type="text" id="harvestingStart">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Oogsttijd (laatste maand):</label></td>
                        <td><form:select path="harvestingEnd" type="text" id="harvestingEnd">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Groeitijd in dagen:</label></td>
                        <td><form:input path="growTime" type="number" placeholder="groeitijd" min="0" id="growTime"/></td>
                    </tr>
                    <tr>
                        <td><label>Plantplaatje:</label></td>
                        <td><input type="file" name="file"/></td>
                    </tr>
                </table>
                <br/>
                <input class="btn btn-success" type="submit" value="Toevoegen"/>
                <br/>
                <br/>
                <a href="/adminDashboard" class="btn btn-success">Terug</a>
            </form:form>
        </div>
    </div>

    <c:import url="partials/footer.jsp" />
