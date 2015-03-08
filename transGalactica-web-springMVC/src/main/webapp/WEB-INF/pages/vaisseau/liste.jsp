<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2><spring:message code="transgalactica.vaisseau.liste.criteres.titre" /></h2>
<form:form commandName="criteresRechercheVaisseau">
	<div class="error"><form:errors cssClass="errormark" /></div>
	
	<label for="immatriculation"><spring:message code="transgalactica.vaisseau.liste.criteres.immatriculation" /></label>
	<form:input path="immatriculation" />
	<form:errors path="immatriculation" cssClass="error" />
	
	<label for="modele"><spring:message code="transgalactica.vaisseau.liste.criteres.modele" /></label>
	<form:input path="modele" />
	<form:errors path="modele" cssClass="error" />
	
	<label for="intergalactique"><spring:message code="transgalactica.vaisseau.liste.criteres.intergalactique" /></label>
	<form:checkbox path="intergalactique" />
	<form:errors path="intergalactique" cssClass="error" />
	<ul class="boutons">
		<li><input type="submit" value="<spring:message code="transgalactica.action.rechercher" />" /></li>
	</ul>
</form:form>

<c:if test="${ not empty vaisseaux }">
	<h2><spring:message code="transgalactica.vaisseau.liste.resultat.titre" /></h2>
	<table id="resultatsRechercheVaisseau">
		<thead>
			<tr>
				<th><spring:message code="transgalactica.vaisseau.liste.resultat.immatriculation" /></th>
				<th><spring:message code="transgalactica.vaisseau.liste.resultat.modele" /></th>
				<th><spring:message code="transgalactica.vaisseau.liste.resultat.localisationHangar" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vaisseau" items="${vaisseaux}" varStatus="status">
				<tr class="${ status.index % 2 == 1 ? 'a' : 'b' }">
					<td><a href="<c:url value="/vaisseaux/${ vaisseau.immatriculationVaisseau }"/>">${ vaisseau.immatriculationVaisseau }</a></td>
					<td>${ vaisseau.modeleVaisseau }</td>
					<td>${ vaisseau.localisationHangar }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
