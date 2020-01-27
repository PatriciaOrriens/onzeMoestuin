<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Onze Moestuin</title>
    <c:import url="partials/style.jsp" />
</head>
<body>
    <c:import url="partials/devnav.jsp" />
    <!-- <div class="container"> -->

    <title>Verander plantinformatie</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="../resources/javascript/adminCreatePlantInformationJavaScript.js"></script>

    <div class="container">
        <div class="form">
            <form:form action="/plantinfo/update/${plantInfoId}" modelAttribute="plantInformation">
                <h1 class="display-3">Plant wijzigen</h1>
                <table class="table-sm table-borderless">
                    <tr>
                        <td><label>Nederlandse plantnaam:</label></td>
                        <td><form:input path="plantName" type="text" value="${plantInformation.plantName}" id="plantName"/></td>
                    </tr>
                    <tr>
                        <td><label>Latijnse plantnaam:</label></td>
                        <td><form:input path="latinName" type="text" value="${plantInformation.latinName}" id="latinName"/></td>
                    </tr>
                    <tr>
                        <td><label>Plantafstand (in cm's):</label></td>
                        <td><form:input path="plantingDistance" type="number" value="${plantInformation.plantingDistance}" min="0" id="plantingDistance"/></td>
                    </tr>
                    <tr>
                        <td><label>Belichting:</label></td>
                        <td><form:select path="lighting" type="text" id="lighting">
                        <option value="${plantInformation.lighting}">${plantInformation.lighting}</option>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Grondsoort:</label></td>
                        <td><form:select path="soilType" type="text" id="soilType">
                        <option value="${plantInformation.soilType}">${plantInformation.soilType}</option>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Zaaitijd (eerste maand):</label></td>
                        <td><form:select path="sowingStart" type="text" id="sowingStart">
                        <option value="${plantInformation.sowingStart}">${plantInformation.sowingStart}</option>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Zaaitijd (laatste maand):</label></td>
                        <td><form:select path="sowingEnd" type="text" id="sowingEnd">
                        <option value="${plantInformation.sowingEnd}">${plantInformation.sowingEnd}</option>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Planttijd (eerste maand):</label></td>
                        <td><form:select path="plantingStart" type="text" id="plantingStart">
                        <option value="${plantInformation.plantingStart}">${plantInformation.plantingStart}</option>
                        </form:select><td>
                    </tr>
                    <tr>
                        <td><label>Planttijd (laatste maand):</label></td>
                        <td><form:select path="plantingEnd" type="text" id="plantingEnd">
                        <option value="${plantInformation.plantingEnd}">${plantInformation.plantingEnd}</option>
                        </form:select></td>
                    </tr>
                        <td><label>Oogsttijd (eerste maand):</label></td>
                         <td><form:select path="harvestingStart" type="text" id="harvestingStart">
                         <option value="${plantInformation.harvestingStart}">${plantInformation.harvestingStart}</option>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Oogsttijd (laatste maand):</label></td>
                        <td><form:select path="harvestingEnd" type="text" id="harvestingEnd">
                        <option value="${plantInformation.harvestingEnd}">${plantInformation.harvestingEnd}</option>
                        </form:select><td>
                    </tr>
                    <tr>
                        <td><label>Groeitijd in dagen:</label></td>
                        <td><form:input path="growTime" type="number" value="${plantInformation.growTime}" min="0" id="growTime"/></td>
                     </tr>
                </table>
                <br/>
                <input class="btn btn-primary" type="submit" value="Toepassen"/>
                <br/>
                <br/>
                <a href="/adminManagePlantInformation" class="btn btn-primary">Terug</a>
            </form:form>
        </div>
    </div>

    <c:import url="partials/footer.jsp" />