package org.transgalactica.management.data.rest.dao;

import java.util.List;

import org.transgalactica.management.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;

public interface VaisseauDao {

	List<VaisseauSummaryTo> searchByCriteria(VaisseauSearchCriteria criteres);

	VaisseauTo getByImmatriculation(String immatriculation);

	void persist(VaisseauTo vaisseau);

	void remove(String immatriculation);
}
