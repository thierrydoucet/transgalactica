package org.transgalactica.web.vaisseau.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.web.message.BusinessMessageUtils;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.web.vaisseau.mapper.VaisseauMapper;
import org.transgalactica.web.vaisseau.model.VaisseauCommand;

@Controller
@RequestMapping("/vaisseaux")
public class VaisseauDetailController {

	private static final String IMMATRICULATION_MODEL = "immatriculation";

	private static final String VAISSEAU_MODEL = "vaisseau";

	private static final String VAISSEAUX_VIEW = "vaisseau/detail";

	private static final String VAISSEAU_EDIT_VIEW = "vaisseau/edit";

	private static final String VAISSEAU_REDIRECT = "redirect:/vaisseaux/";

	private static final String VAISSEAUX_REDIRECT = "redirect:/vaisseaux/search";

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private VaisseauService vaisseauService;

	@Inject
	private VaisseauMapper vaisseauMapper;

	protected VaisseauDetailController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return VAISSEAUX_REDIRECT;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		VaisseauCommand command = beanFactory.getBean(VaisseauCommand.class);
		model.addAttribute(VAISSEAU_MODEL, command);
		return VAISSEAU_EDIT_VIEW;
	}

	@RequestMapping(value = "/{immatriculation}/update", method = RequestMethod.GET)
	public String getUpdate(@PathVariable String immatriculation, Model model) {
		VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
		VaisseauCommand command = vaisseauMapper.mapToVaisseauCommand(entity);
		model.addAttribute(VAISSEAU_MODEL, command);
		model.addAttribute(IMMATRICULATION_MODEL, immatriculation);
		return VAISSEAU_EDIT_VIEW;
	}

	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.GET)
	public String getByImmatriculation(@PathVariable String immatriculation, Model model) {
		VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
		model.addAttribute(VAISSEAU_MODEL, entity);
		return VAISSEAUX_VIEW;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute(VAISSEAU_MODEL) VaisseauCommand command, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return VAISSEAU_EDIT_VIEW;
		}
		VaisseauEntity entity = createEntity(command);
		vaisseauMapper.mapVaisseauCommandToEntity(command, entity);
		try {
			vaisseauService.enregistrerVaisseau(entity);
			return VAISSEAU_REDIRECT + entity.getImmatriculation();
		}
		catch (BusinessException e) {
			BusinessMessageUtils.addBusinessException(model, e);
			return VAISSEAU_EDIT_VIEW;
		}
	}

	private VaisseauEntity createEntity(VaisseauCommand command) {
		if (command.getMultiplicateurHyperdrive() != null) {
			return beanFactory.getBean(VaisseauIntergalactiqueEntity.class.getName(),
					VaisseauIntergalactiqueEntity.class);
		}
		return beanFactory.getBean(VaisseauEntity.class.getName(), VaisseauEntity.class);
	}

	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.POST)
	public String save(@PathVariable String immatriculation,
			@Valid @ModelAttribute(VAISSEAU_MODEL) VaisseauCommand command, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(IMMATRICULATION_MODEL, immatriculation);
			return VAISSEAU_EDIT_VIEW;
		}
		VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
		vaisseauMapper.mapVaisseauCommandToEntity(command, entity);
		try {
			vaisseauService.enregistrerVaisseau(entity);
		}
		catch (BusinessException e) {
			BusinessMessageUtils.addBusinessException(model, e);
			model.addAttribute(IMMATRICULATION_MODEL, immatriculation);
			return VAISSEAU_EDIT_VIEW;
		}
		return VAISSEAU_REDIRECT + entity.getImmatriculation();
	}

	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String immatriculation, Model model) {
		try {
			VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
			vaisseauService.supprimerVaisseau(entity);
		}
		catch (BusinessException e) {
			// rechargement : cf .hangar.DetailControler#delete().
			BusinessMessageUtils.addBusinessException(model, e);
			VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
			model.addAttribute(VAISSEAU_MODEL, entity);
			return VAISSEAUX_VIEW;
		}
		return VAISSEAUX_REDIRECT;
	}
}
