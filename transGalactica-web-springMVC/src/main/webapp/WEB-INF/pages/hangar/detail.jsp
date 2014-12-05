<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2><spring:message code="transgalactica.hangar.detail.titreCaracteristiques" /></h2>

<div>
	<label for="localisation"><spring:message code="transgalactica.hangar.detail.localisation" /></label>
	<span id="localisation">${ hangar.localisation }</span>
</div>
		
<div>
	<label for="nombreEmplacements"><spring:message code="transgalactica.hangar.detail.nombreEmplacements" /></label>
	<span id="nombreEmplacements">${ hangar.nombreEmplacements }</span>
</div>

<c:if test="${ not empty hangar.vaisseaux }">
	<h2><spring:message code="transgalactica.hangar.detail.titrevaisseaux" /></h2>
	<table>
		<thead>
			<tr>
				<th><spring:message code="transgalactica.hangar.detail.vaisseaux.immatriculation" /></th>
				<th><spring:message code="transgalactica.hangar.detail.vaisseaux.modele" /></th>
				<th><spring:message code="transgalactica.hangar.detail.vaisseaux.vitesse" /></th>
				<th><spring:message code="transgalactica.hangar.detail.vaisseaux.autonomie" /></th>
				<th><spring:message code="transgalactica.hangar.detail.vaisseaux.nombreDePassagers" /></th>
				<th><spring:message code="transgalactica.hangar.detail.vaisseaux.capaciteDeFret" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vaisseau" items="${ hangar.vaisseaux }" varStatus="status">
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


<sec:authorize access="hasRole('ROLE_SUPER_GESTIONNAIRE')">
	<div class="boutons">
		<spring:url value="/hangars/${hangar.numero}/update" var="actionEdit"/>
		<form:form action="${ actionEdit }" method="GET">
			<input type="submit" value="<spring:message code="transgalactica.action.editer" />" />
		</form:form>
	</div>
</sec:authorize>

