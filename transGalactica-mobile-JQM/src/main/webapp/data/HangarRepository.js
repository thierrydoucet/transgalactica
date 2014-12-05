$.using('data.HangarRepository', function(ctx) {
});

/**
 * Librairie de gestion des accès REST des Hangars.
 */

data.HangarRepository.getHangars = function(callback) {
    var url = data.PreferenceRepository.getServer().url +  '/hangars/search?localisation=%25';
    data.RestRepository.GET(url, callback);
};

data.HangarRepository.getHangar = function(numero, callback) {
    var url = data.PreferenceRepository.getServer().url +  '/hangars/' + numero;
    data.RestRepository.GET(url, callback);
};

data.HangarRepository.addVaisseau = function(numero, immatriculation, callback) {
    var url = data.PreferenceRepository.getServer().url +  '/hangars/' + numero + '/vaisseaux/' + immatriculation;
    data.RestRepository.POST(url, null, callback);
};
