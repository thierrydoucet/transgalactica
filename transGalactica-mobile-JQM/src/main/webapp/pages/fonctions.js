$.using('pages', function(context) {
});

pages.getUrlParameterByName = function(name) {
	//window.location.search renvoi vide sur HTC wildfire
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location);
	return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
};

pages.showInfo = function(message) {
	pages.showMessage(message, 'infoBox');
};

pages.showError = function(message) {
	pages.showMessage(message, 'errorBox');
};

pages.showMessage = function(message, style) {
	$('.infoBox').remove();
	$('.errorBox').remove();
	$('div[data-role="content"]:visible').prepend('<p class="' + style + '">' + message + '</p>');
};