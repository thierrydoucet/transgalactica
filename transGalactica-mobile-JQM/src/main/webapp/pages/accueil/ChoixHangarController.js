$.using('pages.accueil.ChoixHangarController', function(context) {
});

/*
 * Events
 */
$(document).on('pageinit', '#pages\\.accueil\\.ChoixHangarPage', function(event) {
    $('#pages\\.accueil\\.ChoixHangarPage\\.OkButton').click(pages.accueil.ChoixHangarController.save);
});

$(document).on('pageshow', '#pages\\.accueil\\.ChoixHangarPage', function() {
    pages.accueil.ChoixHangarController.modelToview();
});

/*
 * Methodes
 */

pages.accueil.ChoixHangarController.save = function() {
    pages.accueil.ChoixHangarController.viewToModel();
    $('#pages\\.accueil\\.ChoixHangarPage').dialog('close');
};

pages.accueil.ChoixHangarController.viewToModel = function() {
	var hangar = {
	    numero : $('#pages\\.accueil\\.ChoixHangarPage\\.hangar').val(),
	    localisation : $('#pages\\.accueil\\.ChoixHangarPage\\.hangar :selected').text()
	};
	data.PreferenceRepository.persistHangar(hangar);
};

pages.accueil.ChoixHangarController.modelToview = function() {
	var prefs = data.PreferenceRepository.getHangar();
	data.HangarRepository.getHangars(function(data) {
	    var select = $('#pages\\.accueil\\.ChoixHangarPage\\.hangar');
	    select.empty();
	    $.each(data.hangars, function(index, hangar) {
	    	select.append('<option value="' + hangar.numero + '">' + hangar.localisation + '</option>');
	    });
	    select.val(prefs.numero);
	    select.selectmenu('refresh', true);
	});
};