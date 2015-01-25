package org.transgalactica.management.business.logistics.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.business.logistics.exception.VaisseauInexistantException;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;

@Service
public class DaoVaisseauService implements VaisseauService {

	@Inject
	private VaisseauDao vaisseauDao;

	protected DaoVaisseauService() {
	}

	@Override
	public VaisseauEntity chargerVaisseau(String immatriculation) {
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation(immatriculation);
		if (vaisseau == null) {
			throw new VaisseauInexistantException(immatriculation);
		}
		return vaisseau;
	}

	@Override
	@Transactional
	public void enregistrerVaisseau(VaisseauEntity vaisseau) {
		vaisseauDao.save(vaisseau);
	}

	@Override
	public List<VaisseauSummary> rechercherVaisseaux(VaisseauSearchCriteria criteres) {
		return vaisseauDao.findByCriteria(criteres);
	}

	@Override
	public List<VaisseauSummary> rechercherVaisseauxEnTransit() {
		return vaisseauDao.findByHangarIsNullOrderByImmatriculation();
	}

	@Override
	@Transactional
	public void supprimerVaisseau(VaisseauEntity vaisseau) {
		vaisseauDao.delete(vaisseau);
	}
}
