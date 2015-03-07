package org.transgalactica.web.vaisseau.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.web.message.BusinessMessageUtils;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;

@Controller
@RequestMapping(value = "/vaisseaux/search")
public class VaisseauListeController {

	private static final String VAISSEAUX_VIEW = "vaisseaux/liste";

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private VaisseauService vaisseauService;

	protected VaisseauListeController() {
	}

	@ModelAttribute("criteresRechercheVaisseau")
	public VaisseauSearchCriteria createCriteresRechercheVaisseau() {
		return beanFactory.getBean(VaisseauSearchCriteria.class);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return VAISSEAUX_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search(@Valid @ModelAttribute("criteresRechercheVaisseau") VaisseauSearchCriteria criteres,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return VAISSEAUX_VIEW;
		}
		try {
			List<VaisseauSummary> vaisseaux = vaisseauService.rechercherVaisseaux(criteres);
			model.addAttribute("vaisseaux", vaisseaux);
			if (vaisseaux.isEmpty()) {
				BusinessMessageUtils.addBusinessMessage(model, "transgalactica.vaisseau.liste.resultat.vide");
			}
		}
		catch (BusinessException e) {
			BusinessMessageUtils.addBusinessException(model, e);
		}
		return VAISSEAUX_VIEW;
	}
}
