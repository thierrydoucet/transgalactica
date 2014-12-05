<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Hangar ${ hangarDetailDto.numero }</title>
</head>

<body>

	<h1>Hangar</h1>

	<h2>Caractéristiques</h2>
	<div>
		<label for="numero">Numéro : </label><span id="numero">${ hangarDetailDto.numero }</span>
	</div>

	<div>
		<label for="localisation">Localisation : </label><span id="localisation">${ hangarDetailDto.localisation }</span>
	</div>

	<div>
		<label for="nombreEmplacements">Nombre d'emplacements : </label><span id="nombreEmplacements">${ hangarDetailDto.nombreEmplacements }</span>
	</div>

	<h2>Vaisseaux</h2>
	<div>
		<table id="vaisseaux" border="1">
			<thead>
				<tr>
					<th>Immatriculation</th>
					<th>Modèle</th>
					<th>Vitesse</th>
					<th>Rayon d'action</th>
					<th>Nombre de passagers</th>
					<th>Capacite de fret</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vaisseau" items="${ hangarDetailDto.vaisseaux }">
					<tr>
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
	</div>

</body>
</html>