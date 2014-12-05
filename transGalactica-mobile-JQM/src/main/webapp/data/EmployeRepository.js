$.using('data.EmployeRepository', function(ctx) {
});

/**
 * Librairie de gestion des accès REST des Hangars.
 */

data.EmployeRepository.searchEmployeByVaisseau = function(immatriculation, callback) {
    var url = data.PreferenceRepository.getServer().url + '/employes/search?immatriculationVaisseau=' + immatriculation;
    data.RestRepository.GET(url, callback);
};