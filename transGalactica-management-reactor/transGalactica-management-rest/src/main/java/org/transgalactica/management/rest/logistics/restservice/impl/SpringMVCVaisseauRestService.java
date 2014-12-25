package org.transgalactica.management.rest.logistics.restservice.impl;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
import org.transgalactica.management.business.logistics.exception.VaisseauInexistantException;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;
import org.transgalactica.management.rest.logistics.mapper.VaisseauMapper;
import org.transgalactica.management.rest.logistics.restservice.VaisseauRestService;

/**
 * Implementation par défaut de {@link SpringMVCVaisseauRestService}
 * 
 * @author Thierry
 */
/**
 * @author thierry
 *
 */
@Controller
@RequestMapping("/vaisseaux")
public class SpringMVCVaisseauRestService implements VaisseauRestService {

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private VaisseauService vaisseauService;

	@Inject
	private VaisseauMapper vaisseauMapper;

	protected SpringMVCVaisseauRestService() {
	}

	/**
	 * Permet de définir un code retour http 404 en cas d'absence du vaisseau
	 */
	@ExceptionHandler(VaisseauInexistantException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "org.transgalactica.management.business.logistics.exception.VaisseauInexistantException")
	public void vaisseauNotFound() {
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void validationException(HttpServletResponse response, ConstraintViolationException exception)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
			sb.append(violation.getMessage()).append('\n');
		}
		response.sendError(HttpStatus.BAD_REQUEST.value(), sb.toString());
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.restservice.VaisseauRestService#getAll()
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "transit" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public VaisseauDtos getEnTransit() {
		List<VaisseauSummary> projections = vaisseauService.rechercherVaisseauxEnTransit();
		return vaisseauMapper.mapToVaisseauDtos(projections);
	}

	/**
	 * @see org.transgalactica.management.rest.SpringMVCVaisseauRestService.restservice.impl.IVaisseauRestService#search(org.transgalactica.management.rest.materiel.data.impl.RechercheVaisseauCommand)
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "!transit" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public VaisseauDtos search(@RequestParam(required = false) String immatriculation,
			@RequestParam(required = false) String modele, @RequestParam(required = false) boolean intergalactique) {
		VaisseauSearchCriteria criteres = vaisseauMapper.mapToRechercheVaisseauCriteres(immatriculation, modele,
				intergalactique);
		List<VaisseauSummary> projections = vaisseauService.rechercherVaisseaux(criteres);
		return vaisseauMapper.mapToVaisseauDtos(projections);
	}

	/**
	 * @see org.transgalactica.management.rest.SpringMVCVaisseauRestService.restservice.impl.IVaisseauRestService#getByImmatriculation(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.HEAD)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void exists(@PathVariable String immatriculation) {
		// TODO : lors de la migration a spring data : utiliser la méthode
		// exists, et gerer le header.
		vaisseauService.chargerVaisseau(immatriculation);
	}

	/**
	 * @see org.transgalactica.management.rest.SpringMVCVaisseauRestService.restservice.impl.IVaisseauRestService#getByImmatriculation(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public VaisseauDetailDto getByImmatriculation(@PathVariable String immatriculation) {
		VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
		return vaisseauMapper.mapToVaisseauDetailDto(entity);
	}

	/**
	 * @see org.transgalactica.management.rest.SpringMVCVaisseauRestService.restservice.impl.IVaisseauRestService#create(org.transgalactica.management.rest.BasicVaisseauCommand.data.impl.VaisseauCommand)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody VaisseauCommand command) {
		VaisseauEntity entity;
		if (command.getMultiplicateurHyperdrive() != null) {
			entity = beanFactory.getBean(VaisseauIntergalactiqueEntity.class);
		}
		else {
			entity = beanFactory.getBean(VaisseauEntity.class);
		}
		vaisseauMapper.mapVaisseauCommandToEntity(command, entity);
		vaisseauService.enregistrerVaisseau(entity);
	}

	/**
	 * @see org.transgalactica.management.rest.SpringMVCVaisseauRestService.restservice.impl.IVaisseauRestService#save(java.lang.String,
	 * org.transgalactica.management.rest.BasicVaisseauCommand.data.impl.VaisseauCommand)
	 */
	@Override
	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void save(@PathVariable String immatriculation, @Valid @RequestBody VaisseauCommand command) {
		VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
		vaisseauMapper.mapVaisseauCommandToEntity(command, entity);
		vaisseauService.enregistrerVaisseau(entity);
	}

	/**
	 * @see org.transgalactica.management.rest.SpringMVCVaisseauRestService.restservice.impl.IVaisseauRestService#delete(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{immatriculation}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String immatriculation) {
		VaisseauEntity entity = vaisseauService.chargerVaisseau(immatriculation);
		vaisseauService.supprimerVaisseau(entity);
	}
}
