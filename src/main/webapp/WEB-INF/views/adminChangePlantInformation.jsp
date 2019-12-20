<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="partials/header.jsp" />

<!DOCTYPE html>
<head>
    <title>Verander plantinformatie</title>
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
    <h2>Pas een plant aan in plantinformatie</h2>
    <div class="container">
        <div class="form">
            <form:form action="/plantinfo/update/${plantInfoId}" modelAttribute="plantInformation">
                <form:input path="plantInfoId" type="hidden"/>
                <br/>
                <label>Nederlandse plantnaam: </label>
                <form:input path="plantName" type="text" value="${plantInformation.plantName}" class="plantName"/>
                <br/>
                <br/>
                <label>Latijnse plantnaam: </label>
                <form:input path="latinName" type="text" value="${plantInformation.latinName}" class="latinName"/>
                <br/>
                <br/>
                <label>Plantafstand (in cm's): </label>
                <form:input path="plantingDistance" type="number" value="${plantInformation.plantingDistance}" min="0" class="plantingDistance"/>
                <br/>
                <br/>
                <label>Belichting: </label>
                <form:select path="lighting" type="text" class="lighting">
                <option value="${plantInformation.lighting}">${plantInformation.lighting}</option>
                </form:select>
                <br/>
                <br/>
                <label>Grondsoort: </label>
                <form:select path="soilType" type="text" class="soilType">
                <option value="${plantInformation.soilType}">${plantInformation.soilType}</option>
                </form:select>
                <br/>
                <br/>
                <label>Zaaitijd (eerste maand): </label>
                <form:select path="sowingStart" type="text" class="sowingStart">
                <option value="${plantInformation.sowingStart}">${plantInformation.sowingStart}</option>
                </form:select>
                <br/>
                <br/>
                <label>Zaaitijd (laatste maand): </label>
                <form:select path="sowingEnd" type="text" class="sowingEnd">
                <option value="${plantInformation.sowingEnd}">${plantInformation.sowingEnd}</option>
                </form:select>
                <br/>
                <br/>
                <label>Planttijd (eerste maand): </label>
                <form:select path="plantingStart" type="text" class="plantingStart">
                <option value="${plantInformation.plantingStart}">${plantInformation.plantingStart}</option>
                </form:select>
                <br/>
                <br/>
                <label>Planttijd (laatste maand): </label>
                <form:select path="plantingEnd" type="text" class="plantingEnd">
                <option value="${plantInformation.plantingEnd}">${plantInformation.plantingEnd}</option>
                </form:select>
                <br/>
                <br/>
                <label>Oogsttijd (eerste maand): </label>
                 <form:select path="harvestingStart" type="text" class="harvestingStart">
                 <option value="${plantInformation.harvestingStart}">${plantInformation.harvestingStart}</option>
                </form:select>
                <br/>
                <br/>
                <label>Oogsttijd (laatste maand): </label>
                <form:select path="harvestingEnd" type="text" class="harvestingEnd">
                <option value="${plantInformation.harvestingEnd}">${plantInformation.harvestingEnd}</option>
                </form:select>
                <br/>
                <br/>
                <label>Groeitijd in dagen: </label>
                <form:input path="growTime" type="number" value="${plantInformation.growTime}" min="0" class="growTime"/>
                <br/>
                <br/>
                <input type="submit" value="Confirm change"/>
            </form:form>
        </div>
    </div>
    <br/>
    <a href="/adminDashboard">Terug naar taakmenu</a>
</body>

<c:import url="partials/footer.jsp" />