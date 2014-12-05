package org.transgalactica.management.business.logistics.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.management.business.logistics.exception.HangarInexistantException;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.dao.HangarDao;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;

@Service
public class DaoHangarService implements HangarService {

	@Inject
	private HangarDao hangarDao;

	@Inject
	private VaisseauDao vaisseauDao;

	protected DaoHangarService() {
	}

	@Override
	public HangarEntity chargerHangar(Long numero) {
		HangarEntity hangar = hangarDao.findByNumero(numero);
		if (hangar == null) {
			throw new HangarInexistantException(numero);
		}
		return hangar;
	}

	@Override
	@Transactional
	public void enregistrerHangar(HangarEntity hangar) {
		hangarDao.persist(hangar);
	}

	@Override
	public List<HangarSummary> rechercherHangars() {
		return hangarDao.findAllHangars();
	}

	@Override
	public List<HangarSummary> rechercherHangars(HangarSearchCriteria criteresRechercheHangar) {
		return hangarDao.findHangarsByLocalisation(criteresRechercheHangar.getLocalisationHangar());
	}

	@Override
	@Transactional
	public void supprimerHangar(HangarEntity hangar) {
		int nbVaisseaux = vaisseauDao.countVaisseauOfHangar(hangar);
		if (nbVaisseaux > 0) {
			throw new BusinessException("HANGAR_VALIDATION_2");
		}
		hangarDao.remove(hangar);
	}

	@Override
	@Transactional
	public void affecterVaisseauAuHangar(VaisseauEntity vaisseau, HangarEntity hangar) {
		// reste t'il des places dans le Hangar cible
		int nbVaisseaux = vaisseauDao.countVaisseauOfHangar(hangar);
		if (nbVaisseaux >= hangar.getNombreEmplacements()) {
			throw new BusinessException("HANGAR_VALIDATION_4");
		}
		// rechargement entités
		hangarDao.refresh(hangar);
		vaisseauDao.refresh(vaisseau);
		// le vaisseau était'il parqué dans un hangar.
		HangarEntity fromHangar = vaisseau.getHangar();
		if (fromHangar != null && !fromHangar.equals(hangar)) {
			fromHangar.remove(vaisseau);
			hangarDao.persist(fromHangar);
		}
		hangar.add(vaisseau);
		hangarDao.persist(hangar);
	}
}
