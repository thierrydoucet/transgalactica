package org.transgalactica.jsf.ui.logistics;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.Submenu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ModuleConfig {

	@Bean
	@Order(1)
	public Submenu hangarMenu() {

		DefaultSubMenu hangarSubmenu = new DefaultSubMenu("Hangar");

		DefaultMenuItem rechercherItem = new DefaultMenuItem("Rechercher");
		rechercherItem.setIcon("ui-icon-search");
		// rechercher.setCommand("#{menuView.save}");
		// rechercher.setUpdate("messages");
		hangarSubmenu.addElement(rechercherItem);

		DefaultMenuItem creerItem = new DefaultMenuItem("Creer");
		creerItem.setIcon("ui-icon-disk");
		// item.setCommand("#{menuView.delete}");
		creerItem.setAjax(false);
		hangarSubmenu.addElement(creerItem);

		return hangarSubmenu;
	}

	@Bean
	@Order(2)
	public Submenu vaisseauMenu() {

		DefaultSubMenu hangarSubmenu = new DefaultSubMenu("Vaisseau");

		DefaultMenuItem rechercherItem = new DefaultMenuItem("Rechercher");
		rechercherItem.setIcon("ui-icon-search");
		// rechercher.setCommand("#{menuView.save}");
		// rechercher.setUpdate("messages");
		hangarSubmenu.addElement(rechercherItem);

		DefaultMenuItem creerItem = new DefaultMenuItem("Creer");
		creerItem.setIcon("ui-icon-disk");
		// item.setCommand("#{menuView.delete}");
		creerItem.setAjax(false);
		hangarSubmenu.addElement(creerItem);

		return hangarSubmenu;
	}
}
