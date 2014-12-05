<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="<c:url value="/j_spring_security_check"/>" method="POST">
	<c:if test="${ not empty param.login_error }">
	<div class="error">
		<span class="errormark"><c:out value="${ SPRING_SECURITY_LAST_EXCEPTION.message }"/></span>
	</div>
	</c:if>
	<div>
		<label for="j_username"><spring:message code="transgalactica.connexion.utilisateur" /></label>
		<input type="text" name="j_username" value="${ SPRING_SECURITY_LAST_USERNAME }" />
	</div>
	<div>
		<label for="j_password"><spring:message code="transgalactica.connexion.mdp" /></label>
		<input type="password" name='j_password'/>
	</div>
	<div>
		<label for="_spring_security_remember_me"><spring:message code="transgalactica.connexion.remember" /></label>
		<input type='checkbox' name='_spring_security_remember_me'/>
	</div>
		
	<div class="boutons">	
    	<input type="submit" value="<spring:message code="transgalactica.action.seconnecter" />" />
    </div>
</form>
