'use strict';

var restServices = angular.module('transGalacticaApp.services.RestServices', [ 'ngResource', 'transGalacticaApp.services.PreferencesServices' ]);

(function() {

	var _callserver = function(PreferencesRepository, method, path, param) {
		var url = PreferencesRepository.getServer().url + 'path';
		var auth = window.btoa($scope.credential.username + ':' + $scope.credential.password);
		$http.defaults.headers.common.Authorization = 'Basic ' + auth;

		$http.get(url).success(function() {
			// TODO utiliser api de message
			alert('Connexion reussie.');
		}).error(function(data, status) {
			// TODO utiliser api de message
			alert('Erreur lors de la connexion (' + status + ')');
		});
	};
}());

/**
 * Dépot de gestion des accès REST des Hangars.
 */
restServices.factory('HangarRepository', [ '$resource', 'PreferencesRepository', function($resource, PreferencesRepository) {
	// PreferencesRepository.getServer().url +
	return $resource('http://localhost\:8081/transGalactica-rest/hangars/:numero', {}, {
		query : {
			method : 'GET',
			url : 'http://localhost\:8081/transGalactica-rest/hangars',
			headers : {
				'Authorization' : 'Basic SEFMOjIwMDFfb2R5c3NlZQ=='
			}
		},
		addVaisseau : {
			method : 'POST',
			url : 'http://localhost\:8081/transGalactica-rest/hangars/:numero/vaisseaux/:immatriculation',
		}
	});
} ]);
