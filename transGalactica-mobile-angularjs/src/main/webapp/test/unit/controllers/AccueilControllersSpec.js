'use strict';

describe('Trans\'galactica App Accueil Controllers', function() {

	beforeEach(module('transGalacticaApp.controllers.AccueilControllers'));

	var mockPreferencesRepository = {
		getCredential : function() {
			return {
				username : 'TestUser',
				password : 'TestPass'
			};
		},
		getHangar : function(content) {
			return {
				numero : 99,
				localisation : 'TestPlanet'
			};
		},
		getServer : function() {
			return {
				url : 'test://server',
			};
		}
	};
	var mockHangarRepository = {
		query : function() {
			return [ {
				numero : 99,
				localisation : 'TestPlanet'
			}, {
				numero : 999,
				localisation : 'TestPlanet2'
			} ];
		}
	};

	// start tests
	describe('WelcomeController', function() {
		it('should retrieve user and Hangar preferences', inject(function($rootScope, $controller) {
			var scope = $rootScope.$new();
			$controller('WelcomeController', {
				$scope : scope,
				PreferencesRepository : mockPreferencesRepository
			});

			expect(scope.credential.username).toEqual('TestUser');
			expect(scope.hangar.localisation).toEqual('TestPlanet');
		}));
	});

	describe('ConfigurationController', function() {
		var scope, ctrl;
		beforeEach(inject(function($rootScope, $controller) {
			scope = $rootScope.$new();
			ctrl = $controller('ConfigurationController', {
				$scope : scope,
				PreferencesRepository : mockPreferencesRepository
			});
		}));

		it('should retrieve user preferences', inject(function() {
			expect(scope.credential.username).toEqual('TestUser');
			expect(scope.credential.password).toEqual('TestPass');
			expect(scope.server.url).toEqual('test://server');
		}));
	});

	describe('ChoixHangarController', function() {
		var scope, ctrl;
		beforeEach(inject(function($rootScope, $controller) {
			scope = $rootScope.$new();
			ctrl = $controller('ChoixHangarController', {
				$scope : scope,
				PreferencesRepository : mockPreferencesRepository,
				HangarRepository : mockHangarRepository
			});
		}));

		it('should list all hangars', inject(function() {
			expect(scope.hangars).toEqual([ {
				numero : 99,
				localisation : 'TestPlanet'
			}, {
				numero : 999,
				localisation : 'TestPlanet2'
			} ]);
		}));

		it('should retrieve users prefered Hangar', inject(function() {
			expect(scope.hangar.numero).toEqual(99);
			expect(scope.hangar.localisation).toEqual('TestPlanet');
		}));
	});

});
