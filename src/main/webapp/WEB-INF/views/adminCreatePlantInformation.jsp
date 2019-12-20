<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<head>
    <title>NewPlantInformation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
            var lightingList = new Array ("zon", "halfschaduw", "schaduw");

            $(document).ready(function() {
                for (i = 0; i < lightingList.length; i++) {
                    $('.lighting').append('<option>'+lightingList[i]+'</option>')
                }
            });

            var classList = new Array (".sowingStart", ".sowingEnd", ".plantingStart", ".plantingEnd", ".harvestingStart", ".harvestingEnd");

            var monthList = new Array ("januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september",
                "oktober", "november", "december");

            $(document).ready(function() {
                for (j = 0; j < monthList.length; j++) {
                    for (k = 0; k < classList.length; k++) {
                        $(classList[k]).append('<option>'+monthList[j]+'</option>')
                    }
                }
            });

            var soilList = new Array ("standaard tuingrond", "kleigrond", "zandgrond", "kalkrijke grond");

            $(document).ready(function() {
                for (m = 0; m < lightingList.length; m++) {
                    $('.soilType').append('<option>'+soilList[m]+'</option>')
                }
            });
    </script>
</head>
<body>
    <c:import url="partials/header.jsp" />
    <h1>Voeg een nieuwe plant toe aan plantinformatie</h1>
    <div class="container">
        <div class="form">
            <form:form method="post" modelAttribute="plantInformation">
                <label>Nederlandse plantnaam: </label>
                <form:input path="plantName" type="text" placeholder="plant name" class="plantName"/>
                <br/>
                <br/>
                <label>Latijnse plantnaam: </label>
                <form:input path="latinName" type="text" placeholder="Latin name" class="latinName"/>
                <br/>
                <br/>
                <label>Plantafstand (in cm's): </label>
                <form:input path="plantingDistance" type="number" placeholder="plantingdistance" min="0" class="plantingDistance"/>
                <br/>
                <br/>
                <label>Lichtcriteria: </label>
                <form:select path="lighting" type="text" class="lighting">
                </form:select>
                <br/>
                <br/>
                <label>Grondsoort: </label>
                <form:select path="soilType" type="text" class="soilType">
                </form:select>
                <br/>
                <br/>
                <label>Zaaitijd (eerste maand): </label>
                <form:select path="sowingStart" type="text" class="sowingStart">
                </form:select>
                <br/>
                <br/>
                <label>Zaaitijd (laatste maand): </label>
                <form:select path="sowingEnd" type="text" class="sowingEnd">
                </form:select>
                <br/>
                <br/>
                <label>Planttijd (eerste maand): </label>
                <form:select path="plantingStart" type="text" class="plantingStart">
                </form:select>
                <br/>
                <br/>
                <label>Planttijd (laatste maand): </label>
                <form:select path="plantingEnd" type="text" class="plantingEnd">
                </form:select>
                <br/>
                <br/>
                <label>Oogsttijd (eerste maand): </label>
                 <form:select path="harvestingStart" type="text" class="harvestingStart">
                </form:select>
                <br/>
                <br/>
                <label>Oogsttijd (laatste maand): </label>
                <form:select path="harvestingEnd" type="text" class="harvestingEnd">
                </form:select>
                <br/>
                <br/>
                <label>Groeitijd in dagen: </label>
                <form:input path="growTime" type="number" placeholder="growtime" min="0" class="growTime"/>
                <br/>
                <br/>
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
    </div>
    <h1></h1>
    <h1></h1>
    <a href="/adminDashboard">Terug naar taakmenu</a>
    <c:import url="partials/footer.jsp" />
</body>