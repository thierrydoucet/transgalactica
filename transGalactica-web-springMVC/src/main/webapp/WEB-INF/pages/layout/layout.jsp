<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:useAttribute name="title"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title><spring:message code="transgalactica.application.nom"/> - <spring:message code="${title}"/></title>
<spring:theme var="urlStyleBase" code="transgalactica.styleSheet.base"/>
<spring:theme var="urlStyleTheme" code="transgalactica.styleSheet.theme"/>
<link rel="stylesheet" href="<c:url value="${urlStyleBase}" />" type="text/css"/>
<link rel="stylesheet" href="<c:url value="${urlStyleTheme}" />" type="text/css"/>
<link rel="stylesheet" href="lib/wiky.css" type="text/css"/>
<link rel="icon" type="image/png" href="favicon.png" />
<script src="lib/jquery-1.10.2.min.js"></script>
<script src="lib/wiky.js"></script>
</head>
<body>
<div id="header"><tiles:insertAttribute name="header" /></div>

<div id="usersInfo"><tiles:insertAttribute name="usersInfo" /></div>

<div id="breadcrumbs">
	<tiles:useAttribute name="path"/>
	<tiles:insertAttribute name="breadcrumbs">
		<tiles:putAttribute name="path" value="${path}"/>
	</tiles:insertAttribute>
</div>
<div id="menu"><tiles:insertAttribute name="menu" /></div>

<div id="content">
	<h1 id="title"><spring:message code="${title}"/></h1>
	<div id="messages">
		<tiles:insertAttribute name="messages" />
	</div>
	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>
</div>
<div id="footer"><tiles:insertAttribute name="footer" /></div>
</body>
</html>
