<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2><spring:message code="transgalactica.hangar.liste.criteres.titre" /></h2>
<form:form commandName="criteresRechercheHangar">
	<label for="localisationHangar"><spring:message code="transgalactica.hangar.liste.criteres.localisation" /></label>
	<form:input path="localisationHangar" />
	<form:errors path="localisationHangar" cssClass="error" />
	<ul class="boutons">
		<li><input type="submit" value="<spring:message code="transgalactica.action.rechercher" />" /></li>
	</ul>
</form:form>

<c:if test="${ not empty hangars }">
	<h2><spring:message code="transgalactica.hangar.liste.resultat.titre" /></h2>
	<table id="resultatsRechercheHangar">
		<thead>
			<tr>
				<th><spring:message code="transgalactica.hangar.liste.resultat.numeroHangar" /></th>
				<th><spring:message code="transgalactica.hangar.liste.resultat.localisationHangar" /></th>
				<th><spring:message code="transgalactica.hangar.liste.resultat.nombreEmplacementsHangar" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="hangar" items="${hangars}" varStatus="status">
				<tr class="${ status.index % 2 == 1 ? 'a' : 'b' }">
					<td><a href="<c:url value="/hangars/${ hangar.numeroHangar }"/>">${ hangar.numeroHangar }</a></td>
					<td>${ hangar.localisationHangar }</td>
					<td>${ hangar.nombreEmplacementsHangar }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
