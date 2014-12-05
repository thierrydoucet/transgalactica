<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p><spring:message code="transgalactica.erreur.message" /></p>
<ul class="error">
	<c:forEach items="${exceptionMessages}" var="message">
		<li class="errormark"><spring:message message="${message}" /></li>
	</c:forEach>
</ul>
<p><spring:message code="transgalactica.erreur.support" /></p>
