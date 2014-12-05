<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:theme var="srcImage" code="transgalactica.image.logo-titre"/>
<img id="logoTitre" src="<c:url value="${srcImage}" />" alt="Trans'Galactica Logo" />
