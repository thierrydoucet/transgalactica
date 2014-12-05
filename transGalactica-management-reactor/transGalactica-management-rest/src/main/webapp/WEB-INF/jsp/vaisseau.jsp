<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Vaisseau ${ IVaisseauDetailDto.immatriculation }</title>
</head>

<body>

	<h1>Vaisseau</h1>

	<h2>Identification</h2>
	<div>
		<label for="immatriculation">Immatriculation : </label><span id="immatriculation">${ vaisseauDetailDto.immatriculation }</span>
	</div>
	<div>
		<label for="modele">Modèle : </label><span id="modele">${ vaisseauDetailDto.modele }</span>
	</div>

	<h2>Caractéristiques</h2>
	<div>
		<label for="vitesse">Vitesse : </label><span id="vitesse">${ vaisseauDetailDto.vitesse }</span>
	</div>
	<div>
		<label for="autonomie">Rayon d'action : </label><span id="autonomie">${ vaisseauDetailDto.autonomie }</span>
	</div>
	<div>
		<label for="nombreDePassagers">Nombre de passagers : </label><span id="nombreDePassagers">${ vaisseauDetailDto.nombreDePassagers }</span>
	</div>
	<div>
		<label for="capaciteDeFret">Capacite de fret : </label><span id="capaciteDeFret">${ vaisseauDetailDto.capaciteDeFret }</span>
	</div>
		<div>
			<label for="multiplicateurHyperdrive">Multiplicateur d'hyperdrive : </label> <span id="multiplicateurHyperdrive">${ vaisseauDetailDto.multiplicateurHyperdrive }</span>
		</div>

	<c:if test="${ not empty vaisseauDetailDto.numeroHangar }">
	<h2>Hangar</h2>
	<div>
		<label for="hangar">Hangar : </label><span id="hangar"><a href="<c:url value="/hangars/${ vaisseauDetailDto.numeroHangar }"/>">${ vaisseauDetailDto.localisationHangar }</a></span>
	</div>
	</c:if>

</body>
</html>