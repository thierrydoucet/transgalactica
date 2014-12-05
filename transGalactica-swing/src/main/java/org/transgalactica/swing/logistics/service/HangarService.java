package org.transgalactica.swing.logistics.service;

import java.util.List;

import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.data.rest.bo.HangarSearchCriteria;

public interface HangarService {

	HangarTo chargerHangar(Long numero);

	void enregistrerHangar(HangarTo hangar);

	List<HangarSummaryTo> rechercherHangars(HangarSearchCriteria criteres);

	void supprimerHangar(HangarTo hangar);
}