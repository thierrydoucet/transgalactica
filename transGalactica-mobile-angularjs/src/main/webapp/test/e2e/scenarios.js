'use strict';

/* https://github.com/angular/protractor/blob/master/docs/getting-started.md */

describe('Trans\'Galactica App', function() {

	beforeEach(function() {
		// workaround https://github.com/angular/protractor/issues/325
		browser.ignoreSynchronization = true;
	});

	it('should redirect index.html to index.html#/accueil', function() {
		browser.get('index.html');
		browser.switchTo().alert().accept();
		expect(browser.getLocationAbsUrl()).toMatch("/accueil");
	});

	describe('Welcome view (no init)', function() {

		it('should display "hello" generic message', function() {
			browser.get('index.html#/accueil');
			browser.switchTo().alert().accept();
			var msg = element.all(by.css('[ng-view] p')).first();
			//var msg = element(by.binding('credential.username'));
			expect(msg.getText()).toEqual('Welcome to Trans\'Galactica Mobile!');
		});

		it('should display "no hangar" alert message and empty hangar button', function() {
			browser.get('index.html#/accueil');
			
			var alert = browser.switchTo().alert();
			expect(alert.getText()).toEqual('Aucun hangar choisi.<br />Veuillez choisir un hangar via le bouton présent dans le pied de page.');
			alert.accept();
			
			var button = element.all(by.css('[ng-view] button')).first();
			expect(button.getText()).toEqual('---');
		});
	});
});
