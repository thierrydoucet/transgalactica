package org.transgalactica.swing.logistics.service;

import java.util.List;

import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;

public interface HangarService {

	HangarTo chargerHangar(Long numero);

	void enregistrerHangar(HangarTo hangar);

	List<HangarSummaryTo> rechercherHangars(HangarSearchCriteria criteres);

	void supprimerHangar(HangarTo hangar);
}