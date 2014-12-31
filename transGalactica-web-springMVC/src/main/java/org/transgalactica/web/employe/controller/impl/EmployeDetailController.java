package org.transgalactica.web.employe.controller.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.transgalactica.management.business.hr.service.EmployeService;
import org.transgalactica.management.data.people.bo.EmployeEntity;

@Controller
@RequestMapping(value = "/employes")
public class EmployeDetailController {

	private static final String EMPLOYE_VIEW = "employe/detail";

	@Inject
	private EmployeService employeService;

	protected EmployeDetailController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "redirect:/employes/search";
	}

	@RequestMapping(value = "/{matricule}", method = RequestMethod.GET)
	public String getById(@PathVariable Long matricule, Model model) {
		EmployeEntity employe = employeService.chargerEmploye(matricule);
		model.addAttribute("employe", employe);
		return EMPLOYE_VIEW;
	}
}
