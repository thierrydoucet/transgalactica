$.using('pages.vaisseaux.DetailVaisseauController', function(context) {
});

/*
 * Events
 */
$(document).on('pageinit', '#pages\\.vaisseaux\\.DetailVaisseauPage', function() {
    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.deleteButton').click(pages.vaisseaux.DetailVaisseauController.remove);
    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.backButton').click(pages.vaisseaux.DetailVaisseauController.backToHangar);
    
});
$(document).on('pageshow', '#pages\\.vaisseaux\\.DetailVaisseauPage', function() {
    //intialisation des tabs. Sans cet appel, les tabs ne sont pas initialisé lorsque la page est afficher par un liens direct type href.
    $("#pages\\.vaisseaux\\.DetailVaisseauPage\\.tabs").tabs().tabs('refresh');
    pages.vaisseaux.DetailVaisseauController.modelToview();
});

/*
 * Methodes
 */
pages.vaisseaux.DetailVaisseauController.remove = function() {
    var immatriculation = pages.getUrlParameterByName('immatriculation');
    if (confirm('Voulez vous vraiment supprimer le vaisseau "' + immatriculation + '" ?')) {
	    data.VaisseauRepository.deleteVaisseau(immatriculation, function() {
	    	pages.vaisseaux.DetailVaisseauController.backToHangar();	
	    });
    }
};

pages.vaisseaux.DetailVaisseauController.backToHangar = function() {
	//pas d'utilisation du 'back' : cas de lorsque l'on vient de la création
	var hangar = data.PreferenceRepository.getHangar();
	$.mobile.navigate('/transGalactica-web-JQM/pages/hangars/DetailHangarPage.html?numero=' + hangar.numero);
};

pages.vaisseaux.DetailVaisseauController.modelToview = function() {
	var immatriculation = pages.getUrlParameterByName('immatriculation');
	$('#pages\\.vaisseaux\\.DetailVaisseauPage\\.title').text(immatriculation);
	$('#pages\\.vaisseaux\\.DetailVaisseauPage\\.editButton').attr('href', '/transGalactica-web-JQM/pages/vaisseaux/DetailVaisseauEditionPage.html?immatriculation=' + immatriculation);
	// Tab detail
	data.VaisseauRepository.getVaisseau(immatriculation, function(data) {
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.immatriculation').text(data.immatriculation);
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.modele').text(data.modele);
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.vitesse').text(data.vitesse);
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.autonomie').text(data.autonomie);
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.nombreDePassagers').text(data.nombreDePassagers);
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.capaciteDeFret').text(data.capaciteDeFret);
	    $('#pages\\.vaisseaux\\.DetailVaisseauPage\\.multiplicateurHyperdrive').text(data.multiplicateurHyperdrive);
	    $("#pages\\.vaisseaux\\.DetailVaisseauPage\\.tabs").tabs().tabs('refresh');
	});
	//Tab employes
	data.EmployeRepository.searchEmployeByVaisseau(immatriculation, function(data) {
	    var list = $('#pages\\.vaisseaux\\.DetailVaisseauEmployePage\\.employes');
	    list.empty();
	    $.each(data.employes, function(index, employe) {
	    	list.append('<li><h2>' + employe.nom + '</h2><span class="ui-li-count">' + employe.matricule + '</span><p>' + employe.typeEmploye + '</p></li>');
	    });
	    list.listview().listview('refresh');
	});
};