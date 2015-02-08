package org.transgalactica.management.business.logistics.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;

public interface VaisseauService {

	List<VaisseauSummary> rechercherVaisseaux(@NotNull @Valid VaisseauSearchCriteria criteresRechercheVaisseau);

	VaisseauEntity chargerVaisseau(@NotNull String immatriculation);

	/**
	 * Persister un vaisseau (nouveau ou deja existant).
	 */
	void enregistrerVaisseau(@NotNull @Valid VaisseauEntity vaisseau);

	void supprimerVaisseau(@NotNull VaisseauEntity vaisseau);

	List<VaisseauSummary> rechercherVaisseauxEnTransit();
}
