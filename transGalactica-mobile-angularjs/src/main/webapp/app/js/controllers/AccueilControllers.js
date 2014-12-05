'use strict';

/* Accueil controllers */

var tgAccueilCtrl = angular.module('transGalacticaApp.controllers.AccueilControllers', ['transGalacticaApp.services.PreferencesServices', 'transGalacticaApp.services.RestServices']);

tgAccueilCtrl.controller('WelcomeController', [ '$scope', 'PreferencesRepository', function($scope, PreferencesRepository) {
	$scope.credential = PreferencesRepository.getCredential();

	var hangar = PreferencesRepository.getHangar();
	if (hangar.numero == null) {
		// TODO ajout sur scope 'global' d'un message + gestion dans template a
		// voir dans la page
		// index.
		alert('Aucun hangar choisi.<br />Veuillez choisir un hangar via le bouton présent dans le pied de page.');
	}
	$scope.hangar = hangar;
} ]);

(function() {

	var _testConnection = function($scope, $http) {
		// TODO : via une methode "OPTION" ? (ou url de ping)
		var url = $scope.server.url + '/hangars/search.json?localisation=%25';
		var auth = window.btoa($scope.credential.username + ':' + $scope.credential.password);

		$http.defaults.headers.common.Authorization = 'Basic ' + auth;
		$http.get(url).success(function() {
			// TODO utiliser api de message
			alert('Connexion reussie.');
		}).error(function(data, status, headers, config) {
			// TODO utiliser api de message
			// TODO : modifier erreur rest, pour retourner le message dans le corps de la page (data) plutot que la page par defaut tomcat.
			alert('Erreur lors de la connexion (' + status + ' : ' + data + ' )');
		});
	},

	_savePreferences = function($scope, $location, PreferenceRepository) {
		PreferenceRepository.persistCredential($scope.credential);
		PreferenceRepository.persistServer($scope.server);
		$location.path('#/accueil');
	};

	tgAccueilCtrl.controller('ConfigurationController', [ '$scope', '$location', '$http', 'PreferencesRepository', function($scope, $location, $http, PreferencesRepository) {
		$scope.credential = PreferencesRepository.getCredential();
		$scope.server = PreferencesRepository.getServer();

		$scope.testConnection = function() {
			_testConnection($scope, $http);
		};

		$scope.savePreferences = function() {
			_savePreferences($scope, $location, PreferenceRepository);
		};
	} ]);

}());

tgAccueilCtrl.controller('ChoixHangarController', [ '$scope', '$location', 'PreferencesRepository', 'HangarRepository', function($scope, $location, PreferenceRepository, HangarRepository) {
	$scope.hangars = HangarRepository.query();
	$scope.hangar = PreferenceRepository.getHangar();

	$scope.saveDefaultHangar = function(hangar) {
		PreferenceRepository.persistHangar(hangar);
		$location.path('#/accueil');
	};
} ]);
