<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wiki" uri="http://org.transgalactica.socle/web/wiki"%>
<p>
	<spring:message code="transgalactica.accueil.welcome.bienvenue" />
</p>
<p>
	<spring:message code="transgalactica.accueil.welcome.heure" arguments="${date}" />
</p>

<h2>
	<spring:message code="transgalactica.accueil.welcome.messages" />
</h2>
<div id="welcome.motd.messages">
	<spring:theme var="motdChargement" code="transgalactica.image.picto_chargement" />
	<span id="welcome.motd.messages.chargement"><img id="motdChargement" src="<c:url value="${motdChargement}" />" alt="" /> <spring:message code="transgalactica.action.chargement" /> </span>
</div>

<script type="text/javascript">
	$.ajax({
		url : '${infoMotdUrl}',
		type : 'GET',
		contentType : 'application/json; charset=UTF-8',
		crossDomain : true,
		success : function(data) {
			$('#welcome\\.motd\\.messages').empty();
			$.each(data, function(index, message) {
				var html = '<h3 id="welcome.motd.messages.' + message.id + '" >';
				html += message.titre;
				if (message.datePublication != null) {
					html += ' - ' + new Date(message.datePublication).toLocaleString();
				}
				html += '</h3>';
				html += '<div>';
				if (message.image.url != null) {
					html += '<img style="float: left; margin: 5px;" src="' + message.image.url + '"';
					if (message.image.texteAlternatif != null) {
						html += ' alt="' + message.image.texteAlternatif + '"';
					}
					html += ' />';
				}
				html += wiky.process(message.contenu);
				html += '</div>';
				html += '<div style="clear: both;"></div>';

				$('#welcome\\.motd\\.messages').append(html);
			});
		},
		error : function(request, status, error) {
			$('#welcome\\.motd\\.messages').empty();
			$('#welcome\\.motd\\.messages').append('<ul id="welcome.motd.messages.erreur" class="error">' //
					+ '<li class="errormark">Erreur lors de l\'accès au serveur' + (error === '' ? '' : (' (' + error + ')')) + '.</li>' //
					+ '</ul>');
		}
	});
</script>
