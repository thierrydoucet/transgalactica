package org.transgalactica.web.hangar.controller;

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
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;

@Controller
@RequestMapping(value = "/hangars/search")
public class HangarListeController {

	private static final String HANGAR_VIEW = "hangar/liste";

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private HangarService hangarService;

	protected HangarListeController() {
	}

	@ModelAttribute("criteresRechercheHangar")
	public HangarSearchCriteria createCriteresRechercheHangar() {
		return beanFactory.getBean(HangarSearchCriteria.class);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return HANGAR_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search(@Valid @ModelAttribute("criteresRechercheHangar") HangarSearchCriteria criteres,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return HANGAR_VIEW;
		}
		try {
			List<HangarSummary> hangars = hangarService.rechercherHangars(criteres);
			model.addAttribute("hangars", hangars);
			if (hangars.isEmpty()) {
				BusinessMessageUtils.addBusinessMessage(model, "transgalactica.hangar.liste.resultat.vide");
			}
		}
		catch (BusinessException e) {
			BusinessMessageUtils.addBusinessException(model, e);
		}
		return HANGAR_VIEW;
	}
}
