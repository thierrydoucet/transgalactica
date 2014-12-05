<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Employe ${ employeDetailDto.matricule }</title>
</head>

<body>

	<h1>Employe</h1>

	<div>
		<label for="matricule">Matricule : </label><span id="matricule">${ employeDetailDto.matricule }</span>
	</div>

	<div>
		<label for="nom">Nom : </label><span id="nom">${ employeDetailDto.nom }</span>
	</div>

	<div>
		<label for="dateEmbauche">Date d'embauche : </label><span id="dateEmbauche">${ employeDetailDto.dateEmbauche }</span>
	</div>

	<div>
		<label for="typeEmploye">Type : </label><span id="typeEmploye">${ employeDetailDto.typeEmploye }</span>
	</div>
	
	<c:if test="${ employeDetailDto.typeEmploye == 'PILOTE' }">
	<div>
		<label for="nombreHeuresDeVol">Nombre heures de vol : </label><span id="nombreHeuresDeVol">${ employeDetailDto.nombreHeuresVol }</span>
	</div>
	</c:if>
	
	<c:if test="${ employeDetailDto.typeEmploye == 'MECANICIEN' }">
	<h2>Spécialités</h2>
	<div>
		<table id="specialite" border="1">
			<thead>
				<tr>
					<th>Nom</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="specialite" items="${ employeDetailDto.specialites }">
					<tr>
						<td>${ specialite }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>

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
				<c:forEach var="vaisseau" items="${ employeDetailDto.vaisseaux }">
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