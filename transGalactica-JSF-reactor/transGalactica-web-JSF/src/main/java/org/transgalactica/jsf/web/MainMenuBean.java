package org.transgalactica.jsf.web;

import javax.annotation.PostConstruct;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuBean {

	@Autowired
	private Submenu[] subMenus;

	private MenuModel model;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		for (Submenu subMenu : subMenus) {
			model.addElement(subMenu);
		}
	}

	public MenuModel getModel() {
		return model;
	}
}
