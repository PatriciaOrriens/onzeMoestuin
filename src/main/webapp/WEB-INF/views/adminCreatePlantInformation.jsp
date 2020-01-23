<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

<head>
    <title>Nieuwe plantinformatie</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
            var lightingList = new Array ("zon", "halfschaduw", "schaduw");

            $(document).ready(function() {
                for (i = 0; i < lightingList.length; i++) {
                    $(lighting).append('<option>'+lightingList[i]+'</option>')
                }
            });

            var soilList = new Array ("standaard tuingrond", "kleigrond", "zandgrond", "kalkrijke grond");

            $(document).ready(function() {
                for (j = 0; j < soilList.length; j++) {
                    $(soilType).append('<option>'+soilList[j]+'</option>')
                }
            });

            var classList = new Array (".sowingStart", ".sowingEnd", ".plantingStart", ".plantingEnd", ".harvestingStart", ".harvestingEnd");

            var monthList = new Array ("januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september",
                "oktober", "november", "december");

            $(document).ready(function() {
                for (k = 0; k < classList.length; k++) {
                    for (m = 0; m < monthList.length; m++) {
                        $(classList[k]).append('<option>'+monthList[m]+'</option>')
                    }
                }
            });
    </script>
</head>
<body>
    <div class="container">
        <div class="form-group row">
            <form:form method="post" modelAttribute="plantInformation">
                <h1 class="display-4">Plant toevoegen</h1>
                <table>
                    <tr>
                        <td><label>Nederlandse plantnaam: </label></td>
                        <td><form:input path="plantName" type="text" placeholder="plantnaam" id="plantName"/></td>
                    </tr>
                    <tr>
                        <td><label>Latijnse plantnaam: </label></td>
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
                        <td><form:select path="sowingStart" type="text" class="sowingStart">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Zaaitijd (laatste maand):</label></td>
                        <td><form:select path="sowingEnd" type="text" class="sowingEnd">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Planttijd (eerste maand):</label></td>
                        <td><form:select path="plantingStart" type="text" class="plantingStart">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Planttijd (laatste maand):</label></td>
                        <td><form:select path="plantingEnd" type="text" class="plantingEnd">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Oogsttijd (eerste maand):</label></td>
                        <td><form:select path="harvestingStart" type="text" class="harvestingStart">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Oogsttijd (laatste maand):</label></td>
                        <td><form:select path="harvestingEnd" type="text" class="harvestingEnd">
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Groeitijd in dagen:</label></td>
                        <td><form:input path="growTime" type="number" placeholder="groeitijd" min="0" class="growTime"/></td>
                    </tr>
                </table>
                 <br/>
                <input class="btn btn-primary" type="submit" value="Toevoegen"/>
                <br/>
                <br/>
                <a href="/adminDashboard" class="btn btn-primary">Terug</a>
            </form:form>
        </div>
    </div>

    <c:import url="partials/footer.jsp" />
