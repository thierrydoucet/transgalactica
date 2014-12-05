$.using('data.VaisseauRepository', function(ctx) {
});

/**
 * Librairie de gestion des accès REST des Vaisseaux.
 */

data.VaisseauRepository.getVaisseau = function(immatriculation, callback) {
    var url = data.PreferenceRepository.getServer().url + '/vaisseaux/' + immatriculation;
    data.RestRepository.GET(url, callback);
};

data.VaisseauRepository.persistVaisseau = function(immatriculation, vaisseau, callback) {
    if (immatriculation === null) {
	var url = data.PreferenceRepository.getServer().url + '/vaisseaux';
	data.RestRepository.POST(url, vaisseau, callback);
    } else {
	var url = data.PreferenceRepository.getServer().url + '/vaisseaux/' + immatriculation;
	data.RestRepository.PUT(url, vaisseau, callback);
    }
};

data.VaisseauRepository.deleteVaisseau = function(immatriculation, callback) {
    var url = data.PreferenceRepository.getServer().url + '/vaisseaux/' + immatriculation;
    data.RestRepository.DELETE(url, callback);
};
