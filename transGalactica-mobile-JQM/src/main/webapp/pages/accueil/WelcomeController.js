$.using('pages.accueil.WelcomeController', function(context) {
});

/*
 * Events
 */

$(document).on('pageshow', '#pages\\.accueil\\.WelcomePage', function() {
    pages.accueil.WelcomeController.setWelcomeMessage();
    pages.accueil.WelcomeController.enableHangarButton();
});

/*
 * Methodes
 */

pages.accueil.WelcomeController.setWelcomeMessage = function() {
    var credential = data.PreferenceRepository.getCredential();
    if (credential.username != null) {
    	$('#pages\\.accueil\\.WelcomePage\\.message').text('Welcome ' + credential.username + '!');
    } else {
    	$('#pages\\.accueil\\.WelcomePage\\.message').text('Welcome to Trans\'Galactica Mobile!');
    }
};

pages.accueil.WelcomeController.enableHangarButton = function() {
    var hangar = data.PreferenceRepository.getHangar();
    var hangarSet = hangar.numero != null;
    var btn = $('#pages\\.accueil\\.WelcomePage\\.hangarButton');
    btn.attr('disabled', !hangarSet);
    if (hangarSet) {
    	btn.text(hangar.localisation);
    	btn.click(function (event) {
    	    $.mobile.navigate('/transGalactica-web-JQM/pages/hangars/DetailHangarPage.html?numero=' + hangar.numero);
    	});
    } else {
    	pages.showInfo('Aucun hangar choisi.<br />Veuillez choisir un hangar via le bouton présent dans le pied de page.');
    }
};