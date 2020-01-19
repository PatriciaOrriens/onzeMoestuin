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
                for (m = 0; m < soilList.length; m++) {
                    $('.soilType').append('<option>'+soilList[m]+'</option>')
                }
            });
    </script>
</head>
<body>
    <div class="container">
        <h1 class="display-4">Plant toevoegen</h1>
        <div class="form-group row">
            <br/>
            <form:form method="post" modelAttribute="plantInformation">
                <label>Nederlandse plantnaam: </label>
                <form:input path="plantName" type="text" placeholder="plantnaam" class="plantName"/>
                <br/>
                <br/>
                <label>Latijnse plantnaam: </label>
                <form:input path="latinName" type="text" placeholder="Latijnse plantnaam" class="latinName"/>
                <br/>
                <br/>
                <label>Plantafstand (in cm's): </label>
                <form:input path="plantingDistance" type="number" placeholder="plantafstand" min="0" class="plantingDistance"/>
                <br/>
                <br/>
                <label>Belichting: </label>
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
                <form:input path="growTime" type="number" placeholder="groeitijd" min="0" class="growTime"/>
                <br/>
                <br/>
                <input class="btn btn-primary" type="submit" value="Toevoegen"/>
            </form:form>
        </div>
            <a href="/adminDashboard" class="btn btn-primary">Terug</a>

    </div>

    <c:import url="partials/footer.jsp" />
