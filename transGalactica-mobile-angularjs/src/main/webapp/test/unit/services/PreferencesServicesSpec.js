'use strict';

describe('Trans\'galactica App Preferences Services', function() {
	beforeEach(module('transGalacticaApp.services.PreferencesServices'));

	// mock localStorage
	var store;
	beforeEach(inject(function() {
		store = {};

		spyOn(localStorage, 'getItem').and.callFake(function(key) {
			return store[key];
		});
		spyOn(localStorage, 'setItem').and.callFake(function(key, value) {
			return store[key] = value;
		});
		spyOn(localStorage, 'clear').and.callFake(function() {
			store = {};
		});
	}));

	it('should save and retrieve user preferences', inject(function(PreferencesRepository) {
		expect(PreferencesRepository.getCredential()).toEqual({
			username : null,
			password : null
		});

		PreferencesRepository.persistCredential({
			username : 'testUser',
			password : 'testPass'
		});
		expect(PreferencesRepository.getCredential()).toEqual({
			username : 'testUser',
			password : 'testPass'
		});
	}));
	
	it('should save and retrieve server preferences', inject(function(PreferencesRepository) {
		expect(PreferencesRepository.getServer()).toEqual({
			url : null,
		});

		PreferencesRepository.persistServer({
			url : 'unittest://testserver',
		});
		expect(PreferencesRepository.getServer()).toEqual({
			url : 'unittest://testserver',
		});
	}));
	
	it('should save and retrieve hangar preferences', inject(function(PreferencesRepository) {
		expect(PreferencesRepository.getHangar()).toEqual({
			numero : null,
			localisation : null
		});

		PreferencesRepository.persistHangar({
			numero : 9,
			localisation : 'testPlanet'
		});
		expect(PreferencesRepository.getHangar()).toEqual({
			numero : 9,
			localisation : 'testPlanet'
		});
	}));
});
