package org.transgalactica.management.data.people.dao;

import java.util.List;

import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;

public interface EmployeDao {

	EmployeEntity findByMatricule(Long matricule);

	void refresh(EmployeEntity employe);

	void persist(EmployeEntity employeEntity);

	void remove(EmployeEntity employeEntity);

	List<EmployeSummary> findEmployesByCriteria(EmployeSearchCriteria critereRechercheEmploye);

	List<EmployeSummary> findEmployesByModeleDeVaisseau(String modele);

	List<EmployeSummary> findEmployesOfVaisseauIntergalactique();
}
