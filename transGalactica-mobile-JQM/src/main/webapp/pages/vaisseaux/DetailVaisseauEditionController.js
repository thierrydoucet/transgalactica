$.using('pages.vaisseaux.DetailVaisseauEditionController', function(context) {
});

/*
 * Events
 */
$(document).on('pageinit', '#pages\\.vaisseaux\\.DetailVaisseauEditionPage', function() {
	$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.saveButton').click(pages.vaisseaux.DetailVaisseauEditionController.save);
});

$(document).on('pageshow', '#pages\\.vaisseaux\\.DetailVaisseauEditionPage', function() {
	pages.vaisseaux.DetailVaisseauEditionController.modelToview();
});

/*
 * Methodes
 */
pages.vaisseaux.DetailVaisseauEditionController.save = function() {
	var immatriculation = pages.getUrlParameterByName('immatriculation');
	var vaisseau = pages.vaisseaux.DetailVaisseauEditionController.viewToModel();
	data.VaisseauRepository.persistVaisseau(immatriculation, vaisseau, function() {
		//TODO : pas trés atomique tout ca...
		if (immatriculation === null) {
			var hangar = data.PreferenceRepository.getHangar();
			data.HangarRepository.addVaisseau(hangar.numero, vaisseau.immatriculation, function() {
				$.mobile.navigate('/transGalactica-web-JQM/pages/vaisseaux/DetailVaisseauPage.html?immatriculation=' + vaisseau.immatriculation);
			});
		} else {
			$.mobile.navigate('/transGalactica-web-JQM/pages/vaisseaux/DetailVaisseauPage.html?immatriculation=' + vaisseau.immatriculation);
		}
	});
};

pages.vaisseaux.DetailVaisseauEditionController.modelToview = function() {
	var immatriculation = pages.getUrlParameterByName('immatriculation');
	if (immatriculation != null) {
		$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.title').text(immatriculation);
		data.VaisseauRepository.getVaisseau(immatriculation, function(data) {
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.immatriculation').val(data.immatriculation);
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.modele').val(data.modele);
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.vitesse').val(data.vitesse);
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.autonomie').val(data.autonomie);
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.nombreDePassagers').val(data.nombreDePassagers);
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.capaciteDeFret').val(data.capaciteDeFret);
			$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.multiplicateurHyperdrive').val(data.multiplicateurHyperdrive);
		});
	} else {
		$('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.title').text('Création d\'un vaisseau');
	}
};

pages.vaisseaux.DetailVaisseauEditionController.viewToModel = function() {
	return {
		immatriculation : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.immatriculation').val(),
		modele : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.modele').val(),
		vitesse : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.vitesse').val(),
		autonomie : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.autonomie').val(),
		nombreDePassagers : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.nombreDePassagers').val(),
		capaciteDeFret : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.capaciteDeFret').val(),
		multiplicateurHyperdrive : $('#pages\\.vaisseaux\\.DetailVaisseauEditionPage\\.multiplicateurHyperdrive').val()
	};
};