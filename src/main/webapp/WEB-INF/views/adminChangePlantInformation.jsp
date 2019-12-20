<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<head>
    <title>ChangePlantInformation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
            var lightingList = new Array ("sun", "half-shade", "shade");
            $(document).ready(function() {

                for (i = 0; i < lightingList.length; i++) {
                        $('.lighting').append('<option>'+lightingList[i]+'</option>')
                }
            });

            var classList = new Array (".sowingStart", ".sowingEnd", ".plantingStart", ".plantingEnd", ".harvestingStart", ".harvestingEnd");

            var monthList = new Array ("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");

            $(document).ready(function() {
                for (j = 0; j < monthList.length; j++) {
                    for (k = 0; k < classList.length; k++) {
                        $(classList[k]).append('<option>'+monthList[j]+'</option>')
                    }
                }
            });

            var soilList = new Array ("acidic", "standard", "clay");

            $(document).ready(function() {
                for (m = 0; m < lightingList.length; m++) {
                    $('.soilType').append('<option>'+soilList[m]+'</option>')
                }
            });
    </script>
</head>
<body>
    <c:import url="partials/header.jsp" />
    <h1>Update plant information</h1>
    <div class="container">
        <div class="form">
            <form:form action="/plantinfo/update/${plantInfoId}" modelAttribute="plantInformation">
                <form:input path="plantInfoId" type="hidden"/>
                <label>Plantname: </label>
                <form:input path="plantName" type="text" value="${plantInformation.plantName}" class="plantName"/>
                <br/>
                <br/>
                <label>The plant's Latin name: </label>
                <form:input path="latinName" type="text" value="${plantInformation.latinName}" class="latinName"/>
                <br/>
                <br/>
                <label>The plantingdistance (in cm's): </label>
                <form:input path="plantingDistance" type="number" value="${plantInformation.plantingDistance}" min="0" class="plantingDistance"/>
                <br/>
                <br/>
                <label>The lighting conditions: </label>
                <form:select path="lighting" type="text" class="lighting">
                <option value="${plantInformation.lighting}">${plantInformation.lighting}</option>
                </form:select>
                <br/>
                <br/>
                <label>The soiltype: </label>
                <form:select path="soilType" type="text" class="soilType">
                <option value="${plantInformation.soilType}">${plantInformation.soilType}</option>
                </form:select>
                <br/>
                <br/>
                <label>The plant should be sown: </label>
                <form:select path="sowingStart" type="text" class="sowingStart">
                <option value="${plantInformation.sowingStart}">${plantInformation.sowingStart}</option>
                </form:select>
                <br/>
                <br/>
                <label>The month by which the plant must have been sown: </label>
                <form:select path="sowingEnd" type="text" class="sowingEnd">
                <option value="${plantInformation.sowingEnd}">${plantInformation.sowingEnd}</option>
                </form:select>
                <br/>
                <br/>
                <label>The month in which the plant should be planted: </label>
                <form:select path="plantingStart" type="text" class="plantingStart">
                <option value="${plantInformation.plantingStart}">${plantInformation.plantingStart}</option>
                </form:select>
                <br/>
                <br/>
                <label>The month by which the plant must have been planted: </label>
                <form:select path="plantingEnd" type="text" class="plantingEnd">
                <option value="${plantInformation.plantingEnd}">${plantInformation.plantingEnd}</option>
                </form:select>
                <br/>
                <br/>
                <label>The month in which the plant should be harvested: </label>
                 <form:select path="harvestingStart" type="text" class="harvestingStart">
                 <option value="${plantInformation.harvestingStart}">${plantInformation.harvestingStart}</option>
                </form:select>
                <br/>
                <br/>
                <label>The month in which the plant must have been harvested: </label>
                <form:select path="harvestingEnd" type="text" class="harvestingEnd">
                <option value="${plantInformation.harvestingEnd}">${plantInformation.harvestingEnd}</option>
                </form:select>
                <br/>
                <br/>
                <label>The number of days the plant requires to grow: </label>
                <form:input path="growTime" type="number" value="${plantInformation.growTime}" min="0" class="growTime"/>
                <br/>
                <br/>
                <input type="submit" value="Confirm change"/>
            </form:form>
        </div>
    </div>
    <h1></h1>
    <h1></h1>
    <a href="/adminDashboard">Back to task menu</a>
    <c:import url="partials/footer.jsp" />
</body>