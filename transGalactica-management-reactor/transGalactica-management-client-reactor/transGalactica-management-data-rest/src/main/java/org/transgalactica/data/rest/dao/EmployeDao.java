package org.transgalactica.data.rest.dao;

import java.util.List;

import org.transgalactica.data.rest.bo.EmployeSearchCriteria;
import org.transgalactica.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.data.rest.bo.EmployeTo;

public interface EmployeDao {

	List<EmployeSummaryTo> searchByCriteria(EmployeSearchCriteria criteres);

	EmployeTo getByMatricule(long matricule);

	void persist(EmployeTo employe);

	void remove(long matriculeEmploye);

	void addVaisseau(long matriculeEmploye, String immatriculationVaisseau);

	void removeVaisseau(long matriculeEmploye, String immatriculationVaisseau);

	void addSpecialite(long matriculeEmploye, String specialite);

	void removeSpecialite(long matriculeEmploye, String specialite);
}
