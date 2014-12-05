'use strict';

/* App Module */

var tgApp = angular.module('transGalacticaApp', [ 'ngRoute', 'transGalacticaApp.services.PreferencesServices', 'transGalacticaApp.services.RestServices',
		'transGalacticaApp.controllers.AccueilControllers' ]);

tgApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/accueil', {
		templateUrl : 'partials/accueil/WelcomePage.html',
		controller : 'WelcomeController'
	});
	$routeProvider.when('/preferences/configuration', {
		templateUrl : 'partials/accueil/ConfigurationPage.html',
		controller : 'ConfigurationController'
	});
	$routeProvider.when('/preferences/hangar', {
		templateUrl : 'partials/accueil/ChoixHangarPage.html',
		controller : 'ChoixHangarController'
	});
	$routeProvider.otherwise({
		redirectTo : '/accueil'
	});
} ]);

// TODO : generic error handler
