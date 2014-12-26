package org.transgalactica.swing.logistics.service;

import java.util.List;

import org.transgalactica.management.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;

public interface VaisseauService {

	VaisseauTo chargerVaisseau(String immatriculation);

	void enregistrerVaisseau(VaisseauTo vaisseau);

	List<VaisseauSummaryTo> rechercherVaisseaux(VaisseauSearchCriteria criteres);

	void supprimerVaisseau(VaisseauTo vaisseau);

}