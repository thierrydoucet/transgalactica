<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${ not empty businessErrors }">
<div id="erreurs">
<ul id="listeErreurs" class="error">
	<c:forEach items="${businessErrors}" var="message">
		<li class="errormark"><spring:message message="${message}" /></li>
	</c:forEach>
</ul>
</div>
</c:if>

<c:if test="${ not empty businessMessages }">
<div id="informations">
<ul id="listeInformations" class="info">
	<c:forEach items="${businessMessages}" var="message">
		<li class="infomark"><spring:message message="${message}" /></li>
	</c:forEach>
</ul>
</div>
</c:if>
