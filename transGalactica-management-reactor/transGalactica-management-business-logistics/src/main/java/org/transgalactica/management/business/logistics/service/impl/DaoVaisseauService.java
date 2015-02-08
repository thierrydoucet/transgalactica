package org.transgalactica.management.business.logistics.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.transgalactica.management.business.logistics.exception.VaisseauInexistantException;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;

@Service
@Validated
public class DaoVaisseauService implements VaisseauService {

	@Inject
	private VaisseauDao vaisseauDao;

	protected DaoVaisseauService() {
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public VaisseauEntity chargerVaisseau(@NotNull String immatriculation) {
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation(immatriculation);
		if (vaisseau == null) {
			throw new VaisseauInexistantException(immatriculation);
		}
		return vaisseau;
	}

	@Override
	@Secured("ROLE_SUPER_GESTIONNAIRE")
	@Transactional
	public void enregistrerVaisseau(@NotNull @Valid VaisseauEntity vaisseau) {
		vaisseauDao.save(vaisseau);
	}

	@Override
	@Secured({ "ROLE_ANONYMOUS", "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public List<VaisseauSummary> rechercherVaisseaux(@NotNull @Valid VaisseauSearchCriteria criteres) {
		return vaisseauDao.findByCriteria(criteres);
	}

	@Override
	@Secured({ "ROLE_ANONYMOUS", "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public List<VaisseauSummary> rechercherVaisseauxEnTransit() {
		return vaisseauDao.findByHangarIsNullOrderByImmatriculation();
	}

	@Override
	@Transactional
	@Secured("ROLE_SUPER_GESTIONNAIRE")
	public void supprimerVaisseau(@NotNull VaisseauEntity vaisseau) {
		vaisseauDao.delete(vaisseau);
	}
}
