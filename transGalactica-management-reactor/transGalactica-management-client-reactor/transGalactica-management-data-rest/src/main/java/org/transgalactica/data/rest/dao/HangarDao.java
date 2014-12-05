package org.transgalactica.data.rest.dao;

import java.util.List;

import org.transgalactica.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;

public interface HangarDao {

	List<HangarSummaryTo> searchByCriteria(HangarSearchCriteria criteres);

	HangarTo getByNumero(long numero);

	void persist(HangarTo hangar);

	void remove(long numero);

	void addVaisseau(long numeroHangar, String immatriculationVaisseau);

	void removeVaisseau(long numeroHangar, String immatriculationVaisseau);
}
