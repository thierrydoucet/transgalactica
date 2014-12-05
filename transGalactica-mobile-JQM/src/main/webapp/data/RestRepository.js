$.using('data.RestRepository', function(ctx) {
});

/**
 * Librairie de gestion des méthode d'accès REST .
 */
data.RestRepository.GET = function(url, callback) {
	data.RestRepository.ajax(url, 'GET', callback);
};

data.RestRepository.POST = function(url, value, callback) {
	data.RestRepository.ajax(url, 'POST', callback, value);
};

data.RestRepository.PUT = function(url, value, callback) {
	data.RestRepository.ajax(url, 'PUT', callback, value);
};

data.RestRepository.DELETE = function(url, callback) {
	data.RestRepository.ajax(url, 'DELETE', callback);
};

data.RestRepository.ajax = function(url, method, callback, value) {
	var credential = data.PreferenceRepository.getCredential();
	var auth = window.btoa(credential.username + ':' + credential.password);
	var settings = {
			url : url,
			type : method,
			contentType : "application/json; charset=utf-8",
			crossDomain : true,
			beforeSend : function(request) {
				request.setRequestHeader("Authorization", "Basic " + auth);
			},
			success : callback,
		    error: function (request, status, error) {
		    	pages.showError('Erreur lors de l\'accès au serveur'  + (error === '' ? '' : (' (' + error + ')')) + '.');
		    }
		};
	if(method === 'GET') { 
		settings.dataType = 'json'; 
	}
	if(value !== undefined && value !== null) {
		settings.data = JSON.stringify(value);
	}
	$.ajax(settings);
};
