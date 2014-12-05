package org.transgalactica.management.business.logistics.service.mock;

import java.util.List;

import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

/**
 * Mock de HangarService ne réalisant aucune opération.
 */
public class NoOperationHangarService implements HangarService {

	@Override
	public HangarEntity chargerHangar(Long identifiant) {
		return null;
	}

	@Override
	public void enregistrerHangar(HangarEntity hangar) {
	}

	@Override
	public List<HangarSummary> rechercherHangars() {
		return null;
	}

	@Override
	public List<HangarSummary> rechercherHangars(HangarSearchCriteria criteresRechercheHangar) {
		return null;
	}

	@Override
	public void supprimerHangar(HangarEntity hangar) {
	}

	@Override
	public void affecterVaisseauAuHangar(VaisseauEntity vaisseau, HangarEntity hangar) {
	}
}
