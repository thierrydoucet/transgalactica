package org.transgalactica.management.business.logistics.service.mock;

import java.util.List;

import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;

/**
 * Mock de VaisseauService ne réalisant aucune opération.
 */
public class NoOperationVaisseauService implements VaisseauService {

	@Override
	public VaisseauEntity chargerVaisseau(String immatriculation) {
		return null;
	}

	@Override
	public void enregistrerVaisseau(VaisseauEntity vaisseau) {
	}

	@Override
	public List<VaisseauSummary> rechercherVaisseaux(VaisseauSearchCriteria criteresRechercheVaisseau) {
		return null;
	}

	@Override
	public void supprimerVaisseau(VaisseauEntity vaisseau) {
	}

	@Override
	public List<VaisseauSummary> rechercherVaisseauxEnTransit() {
		return null;
	}
}
