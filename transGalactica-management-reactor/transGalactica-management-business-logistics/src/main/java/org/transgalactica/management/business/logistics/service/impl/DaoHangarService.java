package org.transgalactica.management.business.logistics.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class DaoHangarService implements HangarService {

	@Inject
	private HangarDao hangarDao;

	@Inject
	private VaisseauDao vaisseauDao;

	protected DaoHangarService() {
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public HangarEntity chargerHangar(@NotNull Long numero) {
		HangarEntity hangar = hangarDao.findByNumero(numero);
		if (hangar == null) {
			throw new HangarInexistantException(numero);
		}
		return hangar;
	}

	@Override
	@Secured("ROLE_SUPER_GESTIONNAIRE")
	@Transactional
	public void enregistrerHangar(@NotNull @Valid HangarEntity hangar) {
		hangarDao.save(hangar);
	}

	@Override
	@Secured({ "ROLE_ANONYMOUS", "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public List<HangarSummary> rechercherHangars() {
		return hangarDao.findAllOrderByNumero();
	}

	@Override
	@Secured({ "ROLE_ANONYMOUS", "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	public List<HangarSummary> rechercherHangars(@NotNull @Valid HangarSearchCriteria criteresRechercheHangar) {
		return hangarDao.findByLocalisationOrderByNumero(criteresRechercheHangar.getLocalisationHangar());
	}

	@Override
	@Secured("ROLE_SUPER_GESTIONNAIRE")
	@Transactional
	public void supprimerHangar(@NotNull HangarEntity hangar) {
		int nbVaisseaux = vaisseauDao.countByHangar(hangar);
		if (nbVaisseaux > 0) {
			throw new BusinessException("HANGAR_VALIDATION_2");
		}
		hangarDao.delete(hangar);
	}

	@Override
	@Secured({ "ROLE_GESTIONNAIRE", "ROLE_SUPER_GESTIONNAIRE" })
	@Transactional
	public void affecterVaisseauAuHangar(@NotNull VaisseauEntity vaisseau, @NotNull HangarEntity hangar) {
		// reste t'il des places dans le Hangar cible
		int nbVaisseaux = vaisseauDao.countByHangar(hangar);
		if (nbVaisseaux >= hangar.getNombreEmplacements()) {
			throw new BusinessException("HANGAR_VALIDATION_4");
		}
		// le vaisseau était'il parqué dans un hangar.
		HangarEntity fromHangar = vaisseau.getHangar();
		if (fromHangar != null && !fromHangar.equals(hangar)) {
			fromHangar.remove(vaisseau);
			hangarDao.save(fromHangar);
		}
		hangar.add(vaisseau);
		hangarDao.save(hangar);
	}
}
