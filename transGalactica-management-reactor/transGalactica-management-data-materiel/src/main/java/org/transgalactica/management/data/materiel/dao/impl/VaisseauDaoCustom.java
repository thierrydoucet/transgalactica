package org.transgalactica.management.data.materiel.dao.impl;

import java.util.List;

import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;

public interface VaisseauDaoCustom {

	List<VaisseauSummary> findByCriteria(VaisseauSearchCriteria criteres);
}
