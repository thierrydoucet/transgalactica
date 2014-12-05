<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAnonymous()">
<spring:theme var="srcImageUtilisateur" code="transgalactica.image.picto_utilisateur"/>
<spring:theme var="srcImageMdp" code="transgalactica.image.picto_mdp"/>
<form id="connexionRapide" action="<c:url value="/j_spring_security_check"/>" method="POST">
	<input class="connexionRapideField" type="text" name="j_username">
	<span>/</span>
	<input class="connexionRapideField" type="password" name="j_password" />
	<input class="connexionRapideSubmit" type="image" src="<c:url value="${srcImageMdp}" />" />
</form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<span><sec:authentication property="principal.username" /></span> - <a
		href="<c:url value="/j_spring_security_logout"/>"><spring:message code="transgalactica.action.sedeconnecter" /></a>
</sec:authorize>
