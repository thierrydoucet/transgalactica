$.using('data.PreferenceRepository', function(ctx) {
});

/**
 * Librairie de gestion des préférences utilisateur basée sur le
 * <code>LocalStorage</code> HTML5.
 */

data.PreferenceRepository.checkStorage = function() {
    if (typeof (Storage) == 'undefined') {
	throw 'Storage not available';
    }
};

data.PreferenceRepository.getCredential = function() {
    return data.PreferenceRepository._get('prefs.user', {
	username : null,
	password : null
    });
};

data.PreferenceRepository.persistCredential = function(credential) {
    data.PreferenceRepository._persist('prefs.user', credential);
};

data.PreferenceRepository.getHangar = function() {
    return data.PreferenceRepository._get('prefs.hangar', {
	numero : null,
	localisation : null
    });
};

data.PreferenceRepository.persistHangar = function(hangar) {
    data.PreferenceRepository._persist('prefs.hangar', hangar);
};

data.PreferenceRepository.getServer = function() {
    return data.PreferenceRepository._get('prefs.server', {
	url : null
    });
};

data.PreferenceRepository.persistServer = function(server) {
    data.PreferenceRepository._persist('prefs.server', server);
};

//
// fonctions privée
//

data.PreferenceRepository._get = function(key, defaultValue) {
    data.PreferenceRepository.checkStorage();
    var value = localStorage.getItem(key);
    return value == null ? defaultValue : JSON.parse(value, {});
};

data.PreferenceRepository._persist = function(key, value) {
    data.PreferenceRepository.checkStorage();
    localStorage.setItem(key, JSON.stringify(value));
};