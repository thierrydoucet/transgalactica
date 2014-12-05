<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
function updateHangar() {
	var form = document.getElementById("hangar");
	form.submit();
}

function deleteHangar() {
	if(confirm('<spring:message code="transgalactica.hangar.detail.confirmerSuppression" />')) {
		var form = document.getElementById("command");
		form.method = "POST";
		var el = document.createElement("input");
		el.type = "hidden";
		el.name = "_method";
		el.value = "DELETE";
		form.appendChild(el);
		form.submit();
		return true;
	}
	return false;
}
</script>

<spring:url value="/hangars/${ empty numeroHangar ? 'new' : numeroHangar }" var="actionHangar"/>
<form:form commandName="hangar" action="${ actionHangar }" method="POST">
	<div>
		<label for="localisation"><spring:message code="transgalactica.hangar.detail.localisation" /></label>
		<form:input path="localisation" />
		<form:errors path="localisation" cssClass="error" />
	</div>
			
	<div>
		<label for="nombreEmplacements"><spring:message code="transgalactica.hangar.detail.nombreEmplacements" /></label>
		<form:input path="nombreEmplacements" />
		<form:errors path="nombreEmplacements" cssClass="error" />
	</div>

</form:form>


<div class="boutons">
	<form:form action="${ actionHangar }" method="GET">
		<input type="button" onclick="updateHangar();return true;"  value="<spring:message code="transgalactica.action.enregistrer" />" />
		<c:if test="${ not empty numeroHangar }"><input type="button" onclick="return deleteHangar();" value="<spring:message code="transgalactica.action.supprimer" />" /></c:if>
		<input type="submit" value="<spring:message code="transgalactica.action.annuler" />" />
	</form:form>
</div>
	