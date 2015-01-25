package org.transgalactica.management.data.people.dao.impl;

import java.util.List;

import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;

public interface EmployeDaoCustom {

	List<EmployeSummary> findEmployesByCriteria(EmployeSearchCriteria critereRechercheEmploye);
}
