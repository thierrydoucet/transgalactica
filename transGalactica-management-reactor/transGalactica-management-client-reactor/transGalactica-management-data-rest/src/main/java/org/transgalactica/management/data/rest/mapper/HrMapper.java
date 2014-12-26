package org.transgalactica.management.data.rest.mapper;

import java.util.List;

import org.transgalactica.management.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.management.data.rest.bo.EmployeTo;
import org.transgalactica.management.flux.rest.EmployeCommand;
import org.transgalactica.management.flux.rest.EmployeDto;

public interface HrMapper {

	EmployeCommand mapToEmployeCommand(EmployeTo employeTo);

	EmployeTo mapToEmployeTo(EmployeDto employe);

	List<EmployeSummaryTo> mapToEmployeSummaryTo(List<EmployeDto> employes);
}