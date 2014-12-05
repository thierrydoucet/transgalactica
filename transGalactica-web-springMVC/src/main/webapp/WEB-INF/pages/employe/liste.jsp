<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>
	<spring:message code="transgalactica.employe.liste.criteres.titre" />
</h2>
<form:form commandName="criteresRechercheEmploye">
	<label for="nomEmploye"><spring:message code="transgalactica.employe.liste.criteres.nom" /></label>
	<form:input path="nomEmploye" />
	<form:errors path="nomEmploye" cssClass="error" />

	<label for="dateEmbaucheEmployeDebut"><spring:message code="transgalactica.employe.liste.criteres.dateEmbaucheDebut" /></label>
	<form:input path="dateEmbaucheEmployeDebut" />
	<form:errors path="dateEmbaucheEmployeDebut" cssClass="error" />

	<label for="dateEmbaucheEmployeFin"><spring:message code="transgalactica.employe.liste.criteres.dateEmbaucheFin" /></label>
	<form:input path="dateEmbaucheEmployeFin" />
	<form:errors path="dateEmbaucheEmployeFin" cssClass="error" />

	<ul class="boutons">
		<li><input type="submit" value="<spring:message code="transgalactica.action.rechercher" />" /></li>
	</ul>
</form:form>

<c:if test="${ not empty employes }">
	<h2>
		<spring:message code="transgalactica.employe.liste.resultat.titre" />
	</h2>
	<table>
		<thead>
			<tr>
				<th><spring:message code="transgalactica.employe.liste.resultat.nom" /></th>
				<th><spring:message code="transgalactica.employe.liste.resultat.matricule" /></th>
				<th><spring:message code="transgalactica.employe.liste.resultat.dateEmbauche" /></th>
				<th><spring:message code="transgalactica.employe.liste.resultat.type" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employe" items="${employes}" varStatus="status">
				<tr class="${ status.index % 2 == 1 ? 'a' : 'b' }">
					<td>${ employe.nomEmploye }</td>
					<td><a href="<c:url value="/employes/${ employe.matriculeEmploye }"/>">${ employe.matriculeEmploye }</a></td>
					<td>${ employe.dateEmbaucheEmploye }</td>
					<td>${ employe.typeEmploye }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
