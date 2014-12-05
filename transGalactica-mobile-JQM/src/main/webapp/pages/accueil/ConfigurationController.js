$.using('pages.accueil.ConfigurationController', function(context) {
});

/*
 * Events
 */

$(document).on('pageinit', '#pages\\.accueil\\.ConfigurationPage', function(event) {
	$('#pages\\.accueil\\.ConfigurationPage\\.testButton').click(pages.accueil.ConfigurationController.testConnection);
	$('#pages\\.accueil\\.ConfigurationPage\\.saveButton').click(pages.accueil.ConfigurationController.save);
});

$(document).on('pageshow', '#pages\\.accueil\\.ConfigurationPage', function(event) {
	pages.accueil.ConfigurationController.modelToview();
});

/*
 * Methodes
 */

pages.accueil.ConfigurationController.testConnection = function() {
	// TODO : via une methode "OPTION" ? (ou url de ping)
	var url = $('#pages\\.accueil\\.ConfigurationPage\\.server').val() + '/hangars/search.json?localisation=%25';
	var credential = {
		username : $('#pages\\.accueil\\.ConfigurationPage\\.username').val(),
		password : $('#pages\\.accueil\\.ConfigurationPage\\.password').val()
	};
	var auth = window.btoa(credential.username + ':' + credential.password);
	$.ajax({
		url : url,
		dataType : 'json',
		crossDomain : true,
		beforeSend : function(request) {
			request.setRequestHeader("Authorization", "Basic " + auth);
		},
		success : function() {
			pages.showInfo('Connexion reussie.');
		},
		error : function(request, status, error) {
			pages.showError('Erreur lors de la connexion' + (error === '' ? '' : (' (' + error + ')')) + '.');
		}
	});
};

pages.accueil.ConfigurationController.save = function() {
	pages.accueil.ConfigurationController.viewToModel();
	$.mobile.navigate('/transGalactica-web-JQM/pages/accueil/WelcomePage.html');
};

pages.accueil.ConfigurationController.viewToModel = function() {
	var credential = {
		username : $('#pages\\.accueil\\.ConfigurationPage\\.username').val(),
		password : $('#pages\\.accueil\\.ConfigurationPage\\.password').val()
	};
	data.PreferenceRepository.persistCredential(credential);

	var server = {
		url : $('#pages\\.accueil\\.ConfigurationPage\\.server').val()
	};
	data.PreferenceRepository.persistServer(server);
};

pages.accueil.ConfigurationController.modelToview = function() {
	var credential = data.PreferenceRepository.getCredential();
	$('#pages\\.accueil\\.ConfigurationPage\\.username').val(credential.username);
	$('#pages\\.accueil\\.ConfigurationPage\\.password').val(credential.password);

	var server = data.PreferenceRepository.getServer();
	$('#pages\\.accueil\\.ConfigurationPage\\.server').val(server.url);
};