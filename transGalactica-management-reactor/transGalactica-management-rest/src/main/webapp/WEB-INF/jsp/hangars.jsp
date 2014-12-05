<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Hangars</title>
</head>

<body>

	<h1>Hangars</h1>

	<table id="hangars" border="1">
		<thead>
			<tr>
				<th>Numéro</th>
				<th>Localisation</th>
				<th>Nombre d'emplacements</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="hangar" items="${ hangarDtos.hangars }" varStatus="status">
				<tr>
					<td><a href="<c:url value="/hangars/${ hangar.numero }"/>">${ hangar.numero }</a></td>
					<td>${ hangar.localisation }</td>
					<td>${ hangar.nombreEmplacements }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
