<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="partials/header.jsp" />

	<h1>${garden.gardenName}</h1>

    <table>

        <c:forEach items="${plants}" var="plant">
        <tr>
            <td>
                <c:out value="${plant.plantInformation.plantName}" />
            </td>
        </tr>
        </c:forEach>

    </table>

<c:import url="partials/footer.jsp" />
