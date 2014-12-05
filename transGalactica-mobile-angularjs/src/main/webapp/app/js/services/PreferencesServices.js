'use strict';

(function() {

	var _checkStorage = function() {
		if (typeof (Storage) == 'undefined') {
			throw 'Storage not available';
		}
	},

	_get = function(key, defaultValue) {
		_checkStorage();
		var value = localStorage.getItem(key);
		return value == 'undefined'|| value == null ? defaultValue : JSON.parse(value, {});
	},

	_persist = function(key, value) {
		_checkStorage();
		localStorage.setItem(key, JSON.stringify(value));
	};

	angular.module('transGalacticaApp.services.PreferencesServices', []).factory('PreferencesRepository', [ function() {
		return {
			getCredential : function() {
				return _get('prefs.user', {
					username : null,
					password : null
				});
			},
			persistCredential : function(credential) {
				_persist('prefs.user', credential);
			},
			getHangar : function() {
				return _get('prefs.hangar', {
					numero : null,
					localisation : null
				});
			},
			persistHangar : function(hangar) {
				_persist('prefs.hangar', hangar);
			},
			getServer : function() {
				return _get('prefs.server', {
					url : null
				});
			},
			persistServer : function(server) {
				_persist('prefs.server', server);
			}
		};
	} ]);
}());
