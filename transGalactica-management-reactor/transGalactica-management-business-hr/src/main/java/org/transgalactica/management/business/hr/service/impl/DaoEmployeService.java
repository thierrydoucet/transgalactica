package org.transgalactica.management.business.hr.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.business.hr.exception.EmployeInexistantException;
import org.transgalactica.management.business.hr.exception.MecanicienSpecialiteInexistanteException;
import org.transgalactica.management.business.hr.service.EmployeService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.dao.EmployeDao;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.dao.MecanicienSpecialiteDao;

@Service
public class DaoEmployeService implements EmployeService {

	@Inject
	private EmployeDao employeDao;

	@Inject
	private MecanicienSpecialiteDao mecanicienSpecialiteDao;

	protected DaoEmployeService() {
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public EmployeEntity chargerEmploye(Long matricule) {
		EmployeEntity employe = employeDao.findByMatricule(matricule);
		if (employe == null) {
			throw new EmployeInexistantException(matricule);
		}
		return employe;
	}

	@Override
	@Secured("ROLE_SUPER_GESTIONNAIRE")
	@Transactional
	public void enregistrerEmploye(EmployeEntity employe) {
		employeDao.save(employe);
	}

	@Override
	@Secured({ "ROLE_ANONYMOUS", "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public List<EmployeSummary> rechercherEmployes(EmployeSearchCriteria criteresRechercheEmploye) {
		return employeDao.findEmployesByCriteria(criteresRechercheEmploye);
	}

	@Override
	@Secured("ROLE_SUPER_GESTIONNAIRE")
	@Transactional
	public void supprimerEmploye(EmployeEntity employe) {
		employeDao.delete(employe);
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	@Transactional
	public void affecterVaisseauAEmploye(VaisseauEntity vaisseau, EmployeEntity employe) {
		employe.addVaisseau(vaisseau);
		employeDao.save(employe);
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public MecanicienSpecialiteEntity chargerMecanicienSpecialite(String nomSpecialite) {
		MecanicienSpecialiteEntity mecanicienSpecialite = mecanicienSpecialiteDao.findByNomSpecialite(nomSpecialite);
		if (mecanicienSpecialite == null) {
			throw new MecanicienSpecialiteInexistanteException(nomSpecialite);
		}
		return mecanicienSpecialite;
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public List<MecanicienSpecialiteEntity> chargerMecanicienSpecialites() {
		return mecanicienSpecialiteDao.findAll(sortByNomSpecialite());
	}

	private Sort sortByNomSpecialite() {
		return new Sort(Sort.Direction.ASC, "nomSpecialite");
	}
}
