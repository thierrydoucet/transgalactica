<%@ page language="java" contentType="text/css; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

body {
	padding: 0px 0px 10px 0px;
}

body, td, select, input, li{
	font-family: Verdana, Helvetica, Arial, sans-serif;
	font-size: 13px;
}

a {
	text-decoration: none;
}

a:link {
	color:#36a;
}

a:visited {
	color:#47a;
}

a:active, a:hover {
	color:#69c;
}

h1 {
	padding: 4px 4px 4px 6px;
	border: 1px solid #999;
	color: #00447d;
	background-color: #ddd;
	font-weight:900;
	font-size: x-large;
}

h2 {
	padding: 4px 4px 4px 6px;
	border: 1px solid #aaa;
	color: #00447d;
	background-color: #eee;
	font-weight: normal;
	font-size: large;
}

h3 {
	padding: 4px 4px 4px 6px;
	border: 1px solid #bbb;
	color: #00447d;
	background-color: #fff;
	font-weight: normal;
	font-size: large;
}

h4 {
	padding: 4px 4px 4px 6px;
	color: #00447d;
	font-size: normal;
}

caption {
	text-align: left;
	font-weight: bold;
	padding-right: 5px;
}

p {
	line-height: 1.3em;
	font-size: small;
}

#connexionRapide {
	font-size: small;
	margin: 2px 0px 0px 0px;
	padding: 0px;
	vertical-align: middle;
}

.connexionRapideField {
	font-size: small;
	width: 5em;
	margin: 2px 0px 0px 0px;
	padding: 0px;
	vertical-align: middle;
}

.connexionRapideSubmit {
	font-size: small;
	width: auto;
	margin: 2px 0px 0px 0px;
	padding: 0px;
	vertical-align: middle;
}

#breadcrumbs {
	border-top: 1px solid #aaa;
	border-bottom: 1px solid #aaa;
	background-color: #ccc;
}

#menu {
	margin: 10px 0 0 5px;
	border: 1px solid #999;
	background-color: #eee;
	font-size: 1em;
	border-bottom: 1px solid #aaaaaa;
	padding-top: 2px;
	color: #000;
}

table th {
	color: white;
	background-color: #bbb;
	text-align: left;
	font-weight: bold;
}

table th, table td {
	font-size: 1em;
}

table tr.a {
	background-color: #ddd;
}

table tr.b {
	background-color: #eee;
}

.error {
	color: red;
}

.error li {
	list-style-type: none;
}

.errormark {
	background-image: url('<c:url value="/themes/default/images/icons/picto_error_15x15.png"/>');
	background-repeat:no-repeat;
	padding-left: 20px;
}

.warningmark {
	background-image: url('<c:url value="/themes/default/images/icons/picto_warning_15x15.png"/>');
	background-repeat:no-repeat;
	padding-left: 20px;
}

.successmark {
	background-image: url('<c:url value="/themes/default/images/icons/picto_success_15x15.png"/>');
	background-repeat:no-repeat;
	padding-left: 20px;
}

.info {
	color: navy;
}

.info li {
	list-style-type: none;
}

.infomark {
	background-image: url('<c:url value="/themes/default/images/icons/picto_info_15x15.png"/>');
	background-repeat:no-repeat;
	padding-left: 20px;
}
