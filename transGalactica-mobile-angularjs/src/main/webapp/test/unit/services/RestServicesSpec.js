'use strict';

describe('Trans\'galactica App Rest Services', function() {

	// add toEqualsData Jasmine Matcher
	beforeEach(function() {
		jasmine.Expectation.addMatchers({
			toEqualData : function() {
				return {
					compare : function(actual, expected) {
						return {
							pass : angular.equals(actual, expected),
							message : 'Expected ' + JSON.stringify(actual) + ' datas to be equal to ' + JSON.stringify(expected) + ' datas'
						};
					}
				};
			}
		});
	});

	beforeEach(module('transGalacticaApp.services.RestServices'));

	// mock localStorage
	beforeEach(function() {
		spyOn(localStorage, 'getItem').andCallFake(function(key) {
			return '';
		});
	});

	// mock $http
	var $httpBackend;
	beforeEach(inject(function(_$httpBackend_) {
		$httpBackend = _$httpBackend_;
		$httpBackend.expectGET('/hangars').respond([ {
			numero : 99,
			localisation : 'TestPlanet'
		}, {
			numero : 999,
			localisation : 'TestPlanet2'
		} ]);
		$httpBackend.expectGET('/hangars/99').respond({
			numero : 99,
			localisation : 'TestPlanet'
		});
	}));

	it('should retrieve all hangars', inject(function(HangarRepository) {
		var hangars = HangarRepository.query();
		
		$httpBackend.flush();
		expect(hangars).toEqualData([ {
			numero : 99,
			localisation : 'TestPlanet'
		}, {
			numero : 999,
			localisation : 'TestPlanet2'
		} ]);
	}));

	it('should retrieve one hangar', inject(function(HangarRepository) {
		var hangar = HangarRepository.get({
			numero : 99
		});

		$httpBackend.flush();
		expect(hangar).toEqualData({
			numero : 99,
			localisation : 'TestPlanet'
		});
	}));

	it('should add one vaisseau to one hangar', inject(function(HangarRepository) {
		$httpBackend.flush();
		$httpBackend.expectPOST('/hangars/99/vaisseaux/99-99-99', null).respond(201, '');
		HangarRepository.addVaisseau({
			numero : 99,
			vaisseau : '99-99-99'
		})
		$httpBackend.flush();
	}));
});
