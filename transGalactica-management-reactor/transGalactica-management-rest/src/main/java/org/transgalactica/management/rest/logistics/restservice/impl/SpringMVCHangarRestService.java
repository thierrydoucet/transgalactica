package org.transgalactica.management.rest.logistics.restservice.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.transgalactica.management.business.logistics.exception.HangarInexistantException;
import org.transgalactica.management.business.logistics.exception.VaisseauInexistantException;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;
import org.transgalactica.management.rest.logistics.mapper.HangarMapper;
import org.transgalactica.management.rest.logistics.restservice.HangarRestService;

/**
 * Implementation par défaut de {@link SpringMVCHangarRestService}
 * 
 * @author Thierry
 */
@Controller
@RequestMapping(value = "/hangars")
public class SpringMVCHangarRestService implements HangarRestService {

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private HangarService hangarService;

	@Inject
	private VaisseauService vaisseauService;

	@Inject
	private HangarMapper hangarMapper;

	protected SpringMVCHangarRestService() {
	}

	/**
	 * Permet de définir un code retour http 404 en cas d'absence du hangar
	 */
	@ExceptionHandler(HangarInexistantException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "org.transgalactica.management.business.logistics.exception.HangarInexistantException")
	public void hangarNotFound(HttpServletResponse request) {
		// NOP
	}

	@ExceptionHandler(VaisseauInexistantException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "org.transgalactica.management.business.logistics.exception.VaisseauInexistantException")
	public void vaisseauNotFound(HttpServletResponse request) {
		// NOP
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "org.transgalactica.management.business.logistics.exception.HangarInexistantException")
	public void parameterNotValid(HttpServletResponse request) {
		// NOP
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HangarDtos getAll() {
		List<HangarSummary> projections = hangarService.rechercherHangars();
		return hangarMapper.mapToHangarDtos(projections);
	}

	@Override
	@RequestMapping(value = "/{numero}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HangarDetailDto getByNumero(@PathVariable long numero) {
		HangarEntity entity = hangarService.chargerHangar(numero);
		return hangarMapper.mapToHangarDetailDto(entity);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody HangarCommand command) {
		HangarEntity entity = beanFactory.getBean(HangarEntity.class);
		hangarMapper.mapHangarCommandToEntity(command, entity);
		hangarService.enregistrerHangar(entity);
	}

	@Override
	@RequestMapping(value = "/{numero}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void save(@PathVariable long numero, @Valid @RequestBody HangarCommand command) {
		HangarEntity entity = hangarService.chargerHangar(numero);
		hangarMapper.mapHangarCommandToEntity(command, entity);
		hangarService.enregistrerHangar(entity);
	}

	@Override
	@RequestMapping(value = "/{numero}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable long numero) {
		HangarEntity entity = hangarService.chargerHangar(numero);
		hangarService.supprimerHangar(entity);
	}

	@Override
	@RequestMapping(params = "localisation", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HangarDtos search(@RequestParam(required = true) String localisation) {
		HangarSearchCriteria criteres = hangarMapper.mapToRechercheHangarCriteres(localisation);
		List<HangarSummary> projections = hangarService.rechercherHangars(criteres);
		return hangarMapper.mapToHangarDtos(projections);
	}

	@Override
	@RequestMapping(value = "/{numero}/vaisseaux/{immatriculation}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addVaisseau(@PathVariable long numero, @PathVariable String immatriculation) {
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau(immatriculation);
		HangarEntity hangar = hangarService.chargerHangar(numero);
		hangar.add(vaisseau);
		hangarService.enregistrerHangar(hangar);
	}

	@Override
	@RequestMapping(value = "/{numero}/vaisseaux/{immatriculation}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void removeVaisseau(@PathVariable long numero, @PathVariable String immatriculation) {
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau(immatriculation);
		HangarEntity hangar = hangarService.chargerHangar(numero);
		hangar.remove(vaisseau);
		hangarService.enregistrerHangar(hangar);
	}
}
