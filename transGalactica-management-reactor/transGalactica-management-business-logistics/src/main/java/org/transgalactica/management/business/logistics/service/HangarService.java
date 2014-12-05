package org.transgalactica.management.business.logistics.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

@Validated
public interface HangarService {

	List<HangarSummary> rechercherHangars();

	List<HangarSummary> rechercherHangars(@NotNull @Valid HangarSearchCriteria criteresRechercheHangar);

	HangarEntity chargerHangar(Long numero);

	/**
	 * Persister un HangarEntity (nouveau ou deja existant).
	 */
	void enregistrerHangar(@NotNull @Valid HangarEntity hangar);

	void supprimerHangar(@NotNull HangarEntity hangar);

	void affecterVaisseauAuHangar(@NotNull VaisseauEntity vaisseau, @NotNull HangarEntity hangar);
}
