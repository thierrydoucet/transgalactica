package org.transgalactica.management.rest.hr.restservice.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

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
import org.transgalactica.management.business.hr.exception.EmployeInexistantException;
import org.transgalactica.management.business.hr.service.EmployeService;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.EmployeDetailDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;
import org.transgalactica.management.rest.hr.mapper.EmployeMapper;
import org.transgalactica.management.rest.hr.restservice.EmployeRestService;

/**
 * Implementation par défaut de {@link EmployeRestService}
 * 
 * @author Thierry
 */
@Controller
@RequestMapping(value = "/employes")
public class SpringMVCEmployeRestService implements EmployeRestService {

	@Inject
	private EmployeService employeService;

	@Inject
	private VaisseauService vaisseauService;

	@Inject
	private EmployeMapper employeMapper;

	protected SpringMVCEmployeRestService() {
	}

	/**
	 * Permet de définir un code retour http 404 en cas d'absence du employe
	 */
	@ExceptionHandler(EmployeInexistantException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "org.transgalactica.management.business.hr.exception.EmployeInexistantException")
	public void employeNotFound() {
		// NOP
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void parameterNotValid() {
		// NOP
	}

	@Override
	@RequestMapping(value = "/{matricule}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EmployeDetailDto getByMatricule(@PathVariable long matricule) {
		EmployeEntity entity = employeService.chargerEmploye(matricule);
		return employeMapper.mapToEmployeDetailDto(entity);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody EmployeCommand command) {
		EmployeEntity entity = employeMapper.mapEmployeCommandToEntity(command);
		employeService.enregistrerEmploye(entity);
	}

	@Override
	@RequestMapping(value = "/{matricule}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void save(@PathVariable long matricule, @Valid @RequestBody EmployeCommand command) {
		EmployeEntity entity = employeService.chargerEmploye(matricule);
		employeMapper.mapEmployeCommandToEntity(command, entity);
		employeService.enregistrerEmploye(entity);
	}

	@Override
	@RequestMapping(value = "/{matricule}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable long matricule) {
		EmployeEntity entity = employeService.chargerEmploye(matricule);
		employeService.supprimerEmploye(entity);
	}

	@Override
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EmployeDtos search(@RequestParam(required = false) String nomEmploye,
			@RequestParam(required = false) Date dateEmbaucheEmployeDebut,
			@RequestParam(required = false) Date dateEmbaucheEmployeFin,
			@RequestParam(required = false) String immatriculationVaisseau) {
		EmployeSearchCriteria criteres = employeMapper.mapToRechercheEmployeCriteres(nomEmploye,
				dateEmbaucheEmployeDebut, dateEmbaucheEmployeFin, immatriculationVaisseau);
		List<EmployeSummary> employes = employeService.rechercherEmployes(criteres);
		return employeMapper.mapToEmployeDtos(employes);
	}

	@Override
	@RequestMapping(value = "/{matricule}/vaisseaux/{immatriculation}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addVaisseau(@PathVariable long matricule, @PathVariable String immatriculation) {
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau(immatriculation);
		EmployeEntity employe = employeService.chargerEmploye(matricule);
		employe.addVaisseau(vaisseau);
		employeService.enregistrerEmploye(employe);
	}

	@Override
	@RequestMapping(value = "/{matricule}/vaisseaux/{immatriculation}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void removeVaisseau(@PathVariable long matricule, @PathVariable String immatriculation) {
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau(immatriculation);
		EmployeEntity employe = employeService.chargerEmploye(matricule);
		employe.removeVaisseau(vaisseau);
		employeService.enregistrerEmploye(employe);
	}

	@Override
	@RequestMapping(value = "/{matricule}/specialites/{specialite}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addMecanicienSpecialite(@PathVariable long matricule, @PathVariable String specialite) {
		MecanicienSpecialiteEntity specialiteEntity = employeService.chargerMecanicienSpecialite(specialite);
		EmployeEntity employe = employeService.chargerEmploye(matricule);
		if (employe.getType() != EmployeType.MECANICIEN) {
			throw new IllegalArgumentException("Employe with matricule '" + matricule + "' is not a mecanicien ("
					+ employe.getType() + ")");
		}
		MecanicienEntity mecanicien = (MecanicienEntity) employe;
		mecanicien.addSpecialite(specialiteEntity);
		employeService.enregistrerEmploye(mecanicien);
	}

	@Override
	@RequestMapping(value = "/{matricule}/specialites/{specialite}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void removeMecanicienSpecialite(@PathVariable
	long matricule, @PathVariable
	String specialite) {
		MecanicienSpecialiteEntity specialiteEntity = employeService.chargerMecanicienSpecialite(specialite);
		EmployeEntity employe = employeService.chargerEmploye(matricule);
		if (employe.getType() != EmployeType.MECANICIEN) {
			throw new IllegalArgumentException("Employe with matricule '" + matricule + "' is not a mecanicien ("
					+ employe.getType() + ")");
		}
		MecanicienEntity mecanicien = (MecanicienEntity) employe;
		mecanicien.removeSpecialite(specialiteEntity);
		employeService.enregistrerEmploye(mecanicien);
	}
}
