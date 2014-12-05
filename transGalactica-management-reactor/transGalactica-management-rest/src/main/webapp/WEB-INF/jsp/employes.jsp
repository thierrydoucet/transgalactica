<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Employés</title>
</head>

<body>

	<h1>Employés</h1>

	<table id="employes" border="1">
		<thead>
			<tr>
				<th>Matricule</th>
				<th>Nom</th>
				<th>Date d'embauche</th>
				<th>Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employe" items="${ employeDtos.employes }" varStatus="status">
				<tr>
					<td><a href="<c:url value="/employes/${ employe.matricule }"/>">${ employe.matricule }</a></td>
					<td>${ employe.nom }</td>
					<td>${ employe.dateEmbauche }</td>
					<td>${ employe.typeEmploye }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
