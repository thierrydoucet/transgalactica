package org.transgalactica.swing.logistics.service;

import java.util.List;

import org.transgalactica.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.data.rest.bo.VaisseauTo;

public interface VaisseauService {

	VaisseauTo chargerVaisseau(String immatriculation);

	void enregistrerVaisseau(VaisseauTo vaisseau);

	List<VaisseauSummaryTo> rechercherVaisseaux(VaisseauSearchCriteria criteres);

	void supprimerVaisseau(VaisseauTo vaisseau);

}