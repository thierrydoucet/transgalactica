$.using('pages.hangars.DetailHangarController', function(context) {
});

/*
 * Events
 */
$(document).on('pageshow', '#pages\\.hangars\\.DetailHangarPage', function() {
    pages.hangars.DetailHangarController.modelToview();
});

/*
 * Methodes
 */
pages.hangars.DetailHangarController.modelToview = function() {
	var numero = pages.getUrlParameterByName('numero');
	data.HangarRepository.getHangar(numero,	function(data) {
	    // header
	    $('#pages\\.hangars\\.DetailHangarPage\\.localisation').text(data.localisation);
	    // content
	    var list = $('#pages\\.hangars\\.DetailHangarPage\\.vaisseaux');
	    list.empty();
	    $.each(data.vaisseaux, function(index, vaisseau) {
		list.append('<li><a href="/transGalactica-web-JQM/pages/vaisseaux/DetailVaisseauPage.html?immatriculation='
			+ vaisseau.immatriculation
			+ '"><h2>'
			+ vaisseau.immatriculation
			+ '</h2><p>'
			+ vaisseau.modele + '</p></a></li>');
	    });
	    list.listview().listview('refresh');
	});
};