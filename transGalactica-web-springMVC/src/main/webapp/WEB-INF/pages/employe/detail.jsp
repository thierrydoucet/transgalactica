<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2><spring:message code="transgalactica.employe.detail.titreCaracteristiques" /></h2>

<div>
	<label for="nom"><spring:message code="transgalactica.employe.detail.nom" /></label>
	<span id="nom">${ employe.nom }</span>
</div>

<div>
	<label for="matricule"><spring:message code="transgalactica.employe.detail.matricule" /></label>
	<span id="matricule">${ employe.matricule }</span>
</div>

<div>
	<label for="dateEmbauche"><spring:message code="transgalactica.employe.detail.dateEmbauche" /></label>
	<span id="dateEmbauche">${ employe.dateEmbauche }</span>
</div>

<div>
	<label for="type"><spring:message code="transgalactica.employe.detail.type" /></label>
	<span id="type">${ employe.type }</span>
</div>

<c:if test="${ employe.type == 'PILOTE' }">
<div>
	<label for="nombreHeuresVol"><spring:message code="transgalactica.employe.detail.nombreHeuresVol" /></label>
	<span id="nombreHeuresVol">${ employe.nombreHeuresVol }</span>
</div>
</c:if>

<c:if test="${ employe.type == 'MECANICIEN' }">
<div>
	<label for="specialites"><spring:message code="transgalactica.employe.detail.specialites" /></label>
	<span id="specialites"><c:forEach var="specialite" items="${employe.specialites}" varStatus="status"><c:if test="${ status.index != 0 }">, </c:if>${ specialite.nomSpecialite }</c:forEach></span>
</div>
</c:if>

<c:if test="${ not empty employe.vaisseaux }">
	<h2><spring:message code="transgalactica.employe.detail.titrevaisseaux" /></h2>
	<table>
		<thead>
			<tr>
				<th><spring:message code="transgalactica.employe.detail.vaisseaux.immatriculation" /></th>
				<th><spring:message code="transgalactica.employe.detail.vaisseaux.modele" /></th>
				<th><spring:message code="transgalactica.employe.detail.vaisseaux.vitesse" /></th>
				<th><spring:message code="transgalactica.employe.detail.vaisseaux.autonomie" /></th>
				<th><spring:message code="transgalactica.employe.detail.vaisseaux.nombreDePassagers" /></th>
				<th><spring:message code="transgalactica.employe.detail.vaisseaux.capaciteDeFret" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vaisseau" items="${ employe.vaisseaux }" varStatus="status">
				<tr class="${ status.index % 2 == 1 ? 'a' : 'b' }">
					<td><a href="<c:url value="/vaisseaux/${ vaisseau.immatriculation }"/>">${ vaisseau.immatriculation }</a></td>
					<td>${ vaisseau.modele }</td>
					<td>${ vaisseau.vitesse }</td>
					<td>${ vaisseau.autonomie }</td>
					<td>${ vaisseau.nombreDePassagers }</td>
					<td>${ vaisseau.capaciteDeFret }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
