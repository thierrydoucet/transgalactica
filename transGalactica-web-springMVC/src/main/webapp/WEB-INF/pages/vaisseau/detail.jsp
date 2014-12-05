<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="socle" uri="http://org.transgalactica.socle/web/jstl" %>

<h2><spring:message code="transgalactica.vaisseau.detail.titreVaisseau" /></h2>
<div>
	<label for="immatriculation"><spring:message code="transgalactica.vaisseau.detail.immatriculation" /></label>
	<span id="immatriculation">${ vaisseau.immatriculation }</span>
</div>
<div>
	<label for="modele"><spring:message code="transgalactica.vaisseau.detail.modele" /></label>
	<span id="modele">${ vaisseau.modele }</span>
</div>	

<h2><spring:message code="transgalactica.vaisseau.detail.titreCaracteristiques" /></h2>
<div>
	<label for="vitesse"><spring:message code="transgalactica.vaisseau.detail.vitesse" /></label>
	<span id="vitesse">${ vaisseau.vitesse }</span>
</div>
<div>
	<label for="autonomie"><spring:message code="transgalactica.vaisseau.detail.autonomie" /></label>
	<span id="autonomie">${ vaisseau.autonomie }</span>
</div>
<div>
	<label for="nombreDePassagers"><spring:message code="transgalactica.vaisseau.detail.nombreDePassagers" /></label>
	<span id="nombreDePassagers">${ vaisseau.nombreDePassagers }</span>
</div>
<div>
	<label for="capaciteDeFret"><spring:message code="transgalactica.vaisseau.detail.capaciteDeFret" /></label>
	<span id="capaciteDeFret">${ vaisseau.capaciteDeFret }</span>
</div>
<c:if test="${ socle:hasProperty(vaisseau, 'multiplicateurHyperdrive') }">
<div>
	<label for="multiplicateurHyperdrive"><spring:message code="transgalactica.vaisseau.detail.multiplicateurHyperdrive" /></label>
	<span id="multiplicateurHyperdrive">${ vaisseau.multiplicateurHyperdrive }</span>
</div>
</c:if>

<c:if test="${ not empty vaisseau.hangar }">
	<h2><spring:message code="transgalactica.vaisseau.detail.titreHangar" /></h2>
	<div>
		<label for="hangar"><spring:message code="transgalactica.vaisseau.detail.hangar" /></label>
		<span id="hangar"><a href="<c:url value="/hangars/${ vaisseau.hangar.numero }"/>">
		${ vaisseau.hangar.numero }&nbsp;-&nbsp;${ vaisseau.hangar.localisation }</a></span>
	</div>
</c:if>

<sec:authorize access="hasRole('ROLE_SUPER_GESTIONNAIRE')">
	<div class="boutons">
		<spring:url value="/vaisseaux/${vaisseau.immatriculation}/update" var="actionEdit"/>
		<form:form action="${ actionEdit }" method="GET">
			<input type="submit" value="<spring:message code="transgalactica.action.editer" />" />
		</form:form>
	</div>
</sec:authorize>
