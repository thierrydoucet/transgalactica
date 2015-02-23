package org.transgalactica.management.rest.hr.mapper;

import java.time.LocalDate;
import java.util.List;

import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.EmployeDetailDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;

public interface EmployeMapper {

	EmployeEntity mapEmployeCommandToEntity(EmployeCommand command);

	void mapEmployeCommandToEntity(EmployeCommand command, EmployeEntity entity);

	EmployeDtos mapToEmployeDtos(List<EmployeSummary> employes);

	EmployeDetailDto mapToEmployeDetailDto(EmployeEntity entity);

	EmployeSearchCriteria mapToRechercheEmployeCriteres(String nomEmploye, LocalDate dateEmbaucheEmployeDebut,
			LocalDate dateEmbaucheEmployeFin, String immatriculationVaisseau);
}
