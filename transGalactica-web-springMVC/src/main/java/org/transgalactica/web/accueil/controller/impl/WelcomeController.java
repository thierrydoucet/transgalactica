package org.transgalactica.web.accueil.controller.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/", "/accueil" })
public class WelcomeController {

	@Value("#{appProperties['info.motd.url']}")
	private String infoMotdUrl;

	protected WelcomeController() {
	}

	@RequestMapping
	public String welcome(Model model) {
		model.addAttribute(new Date());
		model.addAttribute("infoMotdUrl", infoMotdUrl);
		return "accueil/welcome";
	}
}
