package org.transgalactica.data.rest.mapper;

import java.util.List;

import org.transgalactica.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.data.rest.bo.EmployeTo;
import org.transgalactica.flux.rest.EmployeCommand;
import org.transgalactica.flux.rest.EmployeDto;

public interface HrMapper {

	EmployeCommand mapToEmployeCommand(EmployeTo employeTo);

	EmployeTo mapToEmployeTo(EmployeDto employe);

	List<EmployeSummaryTo> mapToEmployeSummaryTo(List<EmployeDto> employes);
}