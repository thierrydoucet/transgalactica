<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Vaisseaux</title>
</head>

<body>

	<h1>Vaisseaux</h1>

	<table id="vaisseaux" border="1">
		<thead>
			<tr>
				<th>Immatriculation</th>
				<th>Modèle</th>
				<th>Localisation hangar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vaisseau" items="${ vaisseauDtos.vaisseaux }">
				<tr>
					<td><a href="<c:url value="/vaisseaux/${ vaisseau.immatriculation }"/>">${ vaisseau.immatriculation }</a></td>
					<td>${ vaisseau.modele }</td>
					<td>${ vaisseau.localisationHangar }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>