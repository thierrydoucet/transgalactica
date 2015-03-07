package org.transgalactica.web.hangar.controller;

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
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.web.hangar.mapper.HangarMapper;
import org.transgalactica.web.hangar.model.HangarCommand;

@Controller
@RequestMapping(value = "/hangars")
public class HangarDetailController {

	private static final String HANGAR_MODEL = "hangar";
	private static final String NUMERO_MODEL = "numeroHangar";

	private static final String HANGAR_VIEW = "hangar/detail";
	private static final String HANGAR_EDIT_VIEW = "hangar/edit";

	private static final String HANGAR_REDIRECT = "redirect:/hangars/";
	private static final String HANGARS_REDIRECT = "redirect:/hangars/search";

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private HangarService hangarService;

	@Inject
	private HangarMapper hangarMapper;

	protected HangarDetailController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return HANGARS_REDIRECT;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		HangarCommand command = beanFactory.getBean(HangarCommand.class);
		model.addAttribute(HANGAR_MODEL, command);
		return HANGAR_EDIT_VIEW;
	}

	@RequestMapping(value = "/{numero}/update", method = RequestMethod.GET)
	public String getUpdate(@PathVariable Long numero, Model model) {
		HangarEntity entity = hangarService.chargerHangar(numero);
		HangarCommand command = hangarMapper.mapToHangarCommand(entity);
		model.addAttribute(HANGAR_MODEL, command);
		model.addAttribute(NUMERO_MODEL, numero);
		return HANGAR_EDIT_VIEW;
	}

	@RequestMapping(value = "/{numero}", method = RequestMethod.GET)
	public String getById(@PathVariable Long numero, Model model) {
		HangarEntity hangar = hangarService.chargerHangar(numero);
		model.addAttribute(HANGAR_MODEL, hangar);
		return HANGAR_VIEW;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("hangar") HangarCommand command, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return HANGAR_EDIT_VIEW;
		}
		HangarEntity entity = beanFactory.getBean(HangarEntity.class);
		hangarMapper.mapHangarCommandToEntity(command, entity);
		try {
			hangarService.enregistrerHangar(entity);
			return HANGAR_REDIRECT + entity.getNumero();
		}
		catch (BusinessException e) {
			BusinessMessageUtils.addBusinessException(model, e);
			return HANGAR_EDIT_VIEW;
		}
	}

	@RequestMapping(value = "/{numero}", method = RequestMethod.POST)
	public String save(@PathVariable Long numero, @Valid @ModelAttribute("hangar") HangarCommand command,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(NUMERO_MODEL, numero);
			return HANGAR_EDIT_VIEW;
		}
		HangarEntity entity = hangarService.chargerHangar(numero);
		hangarMapper.mapHangarCommandToEntity(command, entity);
		try {
			hangarService.enregistrerHangar(entity);
		}
		catch (BusinessException e) {
			BusinessMessageUtils.addBusinessException(model, e);
			model.addAttribute(NUMERO_MODEL, numero);
			return HANGAR_EDIT_VIEW;
		}
		return HANGAR_REDIRECT + numero;
	}

	@RequestMapping(value = "/{numero}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long numero, Model model) {
		try {
			HangarEntity entity = hangarService.chargerHangar(numero);
			hangarService.supprimerHangar(entity);
		}
		catch (BusinessException e) {
			// Ici nous rechargeons le Hangar, ne sachant dans quel etat,
			// l'exception l'a laissé. Ceci peut provoquer l'envoi d'une autre
			// exception
			BusinessMessageUtils.addBusinessException(model, e);
			HangarEntity hangar = hangarService.chargerHangar(numero);
			model.addAttribute(HANGAR_MODEL, hangar);
			return HANGAR_VIEW;
		}
		return HANGARS_REDIRECT;
	}
}
