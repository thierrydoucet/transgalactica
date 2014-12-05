<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>trans'Galactica Rest - Referentiel HR : Type d'employés</title>
</head>

<body>

	<h1>Referentiel HR : Type d'employés</h1>

	<table id="employeTypes" border="1">
		<thead>
			<tr>
				<th>Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="type" items="${ employeTypeDtos.employeTypes }" varStatus="status">
				<tr>
					<td>${ type }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
