<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<head>
    <title>NewPlantInformation</title>
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
    <h1>Add new plant information</h1>
    <div class="container">
        <div class="form">
            <form:form method="post" modelAttribute="plantInformation">
                <label>Enter a plantname: </label>
                <form:input path="plantName" type="text" placeholder="plant name" class="plantName"/>
                <br/>
                <br/>
                <label>Enter the plant's Latin name: </label>
                <form:input path="latinName" type="text" placeholder="Latin name" class="latinName"/>
                <br/>
                <br/>
                <label>Enter the plantingdistance (in cm's): </label>
                <form:input path="plantingDistance" type="number" placeholder="plantingdistance" min="0" class="plantingDistance"/>
                <br/>
                <br/>
                <label>Enter the lighting conditions: </label>
                <form:select path="lighting" type="text" class="lighting">
                </form:select>
                <br/>
                <br/>
                <label>Enter the soiltype: </label>
                <form:select path="soilType" type="text" class="soilType">
                </form:select>
                <br/>
                <br/>
                <label>Enter the month in which the plant should be sown: </label>
                <form:select path="sowingStart" type="text" class="sowingStart">
                </form:select>
                <br/>
                <br/>
                <label>Enter the month by which the plant must have been sown: </label>
                <form:select path="sowingEnd" type="text" class="sowingEnd">
                </form:select>
                <br/>
                <br/>
                <label>Enter the month in which the plant should be planted: </label>
                <form:select path="plantingStart" type="text" class="plantingStart">
                </form:select>
                <br/>
                <br/>
                <label>Enter the month by which the plant must have been planted: </label>
                <form:select path="plantingEnd" type="text" class="plantingEnd">
                </form:select>
                <br/>
                <br/>
                <label>Enter the month in which the plant should be harvested: </label>
                 <form:select path="harvestingStart" type="text" class="harvestingStart">
                </form:select>
                <br/>
                <br/>
                <label>Enter the month in which the plant must have been harvested: </label>
                <form:select path="harvestingEnd" type="text" class="harvestingEnd">
                </form:select>
                <br/>
                <br/>
                <label>Enter the number of days the plant requires to grow: </label>
                <form:input path="growTime" type="number" placeholder="growtime" min="0" class="growTime"/>
                <br/>
                <br/>
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
    </div>
    <h1></h1>
    <h1></h1>
    <a href="/adminDashboard">Back to task menu</a>
    <c:import url="partials/footer.jsp" />
</body>