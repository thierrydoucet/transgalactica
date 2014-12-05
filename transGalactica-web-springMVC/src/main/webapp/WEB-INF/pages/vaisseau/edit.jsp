<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
function updateVaisseau() {
	var form = document.getElementById("vaisseau");
	form.submit();
}

function deleteVaisseau() {
	if(confirm('<spring:message code="transgalactica.vaisseau.detail.confirmerSuppression" />')) {
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

<spring:url value="/vaisseaux/${ empty immatriculation ? 'new' : immatriculation }" var="actionVaisseau"/>
<form:form commandName="vaisseau" action="${ actionVaisseau }" method="POST">
	<h2><spring:message code="transgalactica.vaisseau.detail.titreVaisseau" /></h2>
	<div>
		<label for="immatriculation"><spring:message code="transgalactica.vaisseau.detail.immatriculation" /></label>
		<form:input path="immatriculation" />
		<form:errors path="immatriculation" cssClass="error" />
	</div>
	<div>
		<label for="modele"><spring:message code="transgalactica.vaisseau.detail.modele" /></label>
		<form:input path="modele" />
		<form:errors path="modele" cssClass="error" />
	</div>	
	
	<h2><spring:message code="transgalactica.vaisseau.detail.titreCaracteristiques" /></h2>
	<div>
		<label for="vitesse"><spring:message code="transgalactica.vaisseau.detail.vitesse" /></label>
		<form:input path="vitesse" />
		<form:errors path="vitesse" cssClass="error" />
	</div>
	<div>
		<label for="autonomie"><spring:message code="transgalactica.vaisseau.detail.autonomie" /></label>
		<form:input path="autonomie" />
		<form:errors path="autonomie" cssClass="error" />
	</div>
	<div>
		<label for="nombreDePassagers"><spring:message code="transgalactica.vaisseau.detail.nombreDePassagers" /></label>
		<form:input path="nombreDePassagers" />
		<form:errors path="nombreDePassagers" cssClass="error" />
	</div>
	<div>
		<label for="capaciteDeFret"><spring:message code="transgalactica.vaisseau.detail.capaciteDeFret" /></label>
		<form:input path="capaciteDeFret" />
		<form:errors path="capaciteDeFret" cssClass="error" />
	</div>
	<c:if test="${ empty immatriculation or not empty vaisseau.multiplicateurHyperdrive }">
	<div>
		<label for="multiplicateurHyperdrive"><spring:message code="transgalactica.vaisseau.detail.multiplicateurHyperdrive" /></label>
		<form:input path="multiplicateurHyperdrive" />
		<form:errors path="multiplicateurHyperdrive" cssClass="error" />
	</div>
	</c:if>
</form:form>


<div class="boutons">
	<form:form action="${ actionVaisseau }" method="GET">
		<input type="button" onclick="updateVaisseau();return true;"  value="<spring:message code="transgalactica.action.enregistrer" />" />
		<c:if test="${ not empty immatriculation }"><input type="button" onclick="return deleteVaisseau();" value="<spring:message code="transgalactica.action.supprimer" />" /></c:if>
		<input type="submit" value="<spring:message code="transgalactica.action.annuler" />" />
	</form:form>
</div>
	