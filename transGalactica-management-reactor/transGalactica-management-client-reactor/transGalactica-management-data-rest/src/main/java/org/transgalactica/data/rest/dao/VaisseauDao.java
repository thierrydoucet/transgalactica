package org.transgalactica.data.rest.dao;

import java.util.List;

import org.transgalactica.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.data.rest.bo.VaisseauTo;

public interface VaisseauDao {

	List<VaisseauSummaryTo> searchByCriteria(VaisseauSearchCriteria criteres);

	VaisseauTo getByImmatriculation(String immatriculation);

	void persist(VaisseauTo vaisseau);

	void remove(String immatriculation);
}
