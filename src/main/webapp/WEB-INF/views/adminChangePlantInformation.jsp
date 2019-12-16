<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<head>
    <title>ChangePlantInformation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
            var monthList = new Array ("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");

            $(document).ready(function() {
                for (i = 0; i < monthList.length; i++) {
                    $('#sowingstart').append('<option>'+monthList[i]+'</option>')
                }
            });
    </script>
</head>
<body>
    <div class="container">
        <div class="form">
            <form:form method="post" modelAttribute="plantInformation">
                <form:input path="plantName" type="text" placeholder="plant name" class="plantName"/>
                <br/>
                <br/>
                <form:input path="latinName" type="text" placeholder="Latin name" class="latinName"/>
                <br/>
                <br/>
                <form:input path="plantingDistance" type="number" placeholder="plantingdistance(cm)" class="plantingDistance"/>
                <br/>
                <br/>
                <form:select path="lighting" type="text" class="lighting">
                    <option>full sun</option>
                    <option>sun</option>
                    <option>partial shade</option>
                    <option>shade</option>
                    <option>full shade</option>
                </form:select>
                <br/>
                <br/>
                <form:select path="soilType" type="text" class="soiltype">
                    <option>optionA</option>
                    <option>optionB</option>
                </form:select>
                <br/>
                <br/>
                <form:select path="sowingStart" type="text" id="sowingstart">
                </form:select>
                <br/>
                <br/>
                <form:select path="sowingEnd" type="text" class="sowingend">
                    <option>optionA</option>
                    <option>optionB</option>
                </form:select>
                <br/>
                <br/>
                <form:select path="plantingStart" type="text" class="plantingstart">
                    <option>optionA</option>
                    <option>optionB</option>
                </form:select>
                <br/>
                <br/>
                <form:select path="plantingEnd" type="text" class="plantingend">
                    <option>optionA</option>
                    <option>optionB</option>
                </form:select>
                <br/>
                <br/>
                 <form:select path="harvestingStart" type="text" class="harvestingstart">
                    <option>optionA</option>
                    <option>optionB</option>
                </form:select>
                <br/>
                <br/>
                <form:select path="harvestingEnd" type="text" class="harvestingend">
                    <option>optionA</option>
                    <option>optionB</option>
                </form:select>
                <br/>
                <br/>
                <form:input path="growTime" type="number" placeholder="growtime(days)" class="growtime"/>
                <br/>
                <br/>
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
    </div>
</body>
</html>