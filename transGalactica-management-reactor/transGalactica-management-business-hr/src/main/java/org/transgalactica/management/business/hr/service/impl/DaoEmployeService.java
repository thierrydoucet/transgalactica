package org.transgalactica.management.business.hr.service.impl;

import java.util.List;

import javax.inject.Inject;

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
	public EmployeEntity chargerEmploye(Long matricule) {
		EmployeEntity employe = employeDao.findByMatricule(matricule);
		if (employe == null) {
			throw new EmployeInexistantException(matricule);
		}
		return employe;
	}

	@Override
	@Transactional
	public void enregistrerEmploye(EmployeEntity employe) {
		employeDao.persist(employe);
	}

	@Override
	public List<EmployeSummary> rechercherEmployes(EmployeSearchCriteria criteresRechercheEmploye) {
		return employeDao.findEmployesByCriteria(criteresRechercheEmploye);
	}

	@Override
	@Transactional
	public void supprimerEmploye(EmployeEntity employe) {
		employeDao.remove(employe);
	}

	@Override
	@Transactional
	public void affecterVaisseauAEmploye(VaisseauEntity vaisseau, EmployeEntity employe) {
		employeDao.refresh(employe);
		employe.addVaisseau(vaisseau);
		employeDao.persist(employe);
	}

	@Override
	public MecanicienSpecialiteEntity chargerMecanicienSpecialite(String nomSpecialite) {
		MecanicienSpecialiteEntity mecanicienSpecialite = mecanicienSpecialiteDao.findByNomSpecialite(nomSpecialite);
		if (mecanicienSpecialite == null) {
			throw new MecanicienSpecialiteInexistanteException(nomSpecialite);
		}
		return mecanicienSpecialite;
	}

	@Override
	public List<MecanicienSpecialiteEntity> chargerMecanicienSpecialites() {
		return mecanicienSpecialiteDao.findAll();
	}
}
