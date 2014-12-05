<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Referentiel HR : Spécialités mécanicien</title>
</head>

<body>

	<h1>Referentiel HR : Spécialités mécanicien</h1>

	<table id="mecanicienSpecialites" border="1">
		<thead>
			<tr>
				<th>Nom</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="nom" items="${ mecanicienSpecialiteDtos.mecanicienSpecialites }" varStatus="status">
				<tr>
					<td>${ nom }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
