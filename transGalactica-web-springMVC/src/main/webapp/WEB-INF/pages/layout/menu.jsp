<%@page import="java.util.Queue"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	//TODO : get Session scoped bean from context. Cannot be done by tileview configuration
	ApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
	pageContext.setAttribute("recentHangars", context.getBean("recentHangars", Queue.class));
	pageContext.setAttribute("recentVaisseaux", context.getBean("recentVaisseaux", Queue.class));
	pageContext.setAttribute("recentEmployes", context.getBean("recentEmployes", Queue.class));
%>
<ul>
	<li><a href="<c:url value="/accueil"/>"><spring:message code="transgalactica.menu.accueil" /></a></li>
	<sec:authorize access="isAuthenticated()">
		<li>
			<ul>
				<spring:message code="transgalactica.menu.employe" />
				<li><a href="<c:url value="/employes/search"/>"><spring:message code="transgalactica.menu.employe.rechercher" /></a></li>
				<c:if test="${ not empty recentEmployes }">
					<ul>
						<c:forEach var="recentEmploye" items="${ recentEmployes }">
							<li><a href="<c:url value="/employes/${ recentEmploye.key }"/>"><spring:message code="transgalactica.menu.employe.recent" arguments="${ recentEmploye.arguments }"/></a></li>
						</c:forEach>
					</ul>
				</c:if>
			</ul>
		</li>
		<li>
			<ul>
				<spring:message code="transgalactica.menu.hangar" />
				<sec:authorize access="hasRole('ROLE_SUPER_GESTIONNAIRE')">
					<li><a href="<c:url value="/hangars/new"/>"><spring:message code="transgalactica.menu.hangar.creer" /></a></li>
				</sec:authorize>
				<li><a href="<c:url value="/hangars/search"/>"><spring:message code="transgalactica.menu.hangar.rechercher" /></a></li>
				<c:if test="${ not empty recentHangars }">
					<ul>
						<c:forEach var="recentHangar" items="${ recentHangars }">
							<li><a href="<c:url value="/hangars/${ recentHangar.key }"/>"><spring:message code="transgalactica.menu.hangar.recent" arguments="${ recentHangar.arguments }"/></a></li>
						</c:forEach>
					</ul>
				</c:if>
			</ul>
		</li>
		<li>
			<ul>
				<spring:message code="transgalactica.menu.vaisseau" />
				<sec:authorize access="hasRole('ROLE_SUPER_GESTIONNAIRE')">
					<li><a href="<c:url value="/vaisseaux/new"/>"><spring:message code="transgalactica.menu.vaisseau.creer" /></a></li>
				</sec:authorize>
				<li><a href="<c:url value="/vaisseaux/search"/>"><spring:message code="transgalactica.menu.vaisseau.rechercher" /></a></li>
				<c:if test="${ not empty recentVaisseaux }">
					<ul>
						<c:forEach var="recentVaisseau" items="${ recentVaisseaux }">
							<li><a href="<c:url value="/vaisseaux/${ recentVaisseau.key }"/>"><spring:message code="transgalactica.menu.vaisseau.recent" arguments="${ recentVaisseau.arguments }"/></a></li>
						</c:forEach>
					</ul>
				</c:if>
			</ul>
		</li>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<li><a href="<c:url value="/login"/>"><spring:message code="transgalactica.menu.connexion" /></a></li>
	</sec:authorize>
</ul>
