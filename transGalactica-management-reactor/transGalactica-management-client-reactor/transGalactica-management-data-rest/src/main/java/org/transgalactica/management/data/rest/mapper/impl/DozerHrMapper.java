package org.transgalactica.management.data.rest.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.management.data.rest.bo.EmployeTo;
import org.transgalactica.management.data.rest.mapper.HrMapper;
import org.transgalactica.management.flux.rest.EmployeCommand;
import org.transgalactica.management.flux.rest.EmployeDto;
import org.transgalactica.management.flux.rest.PiloteCommand;

/**
 * Implementation <code>Dozer</code> de {@link HrMapper}
 * 
 * @author Thierry
 */
@Mapper
public class DozerHrMapper implements HrMapper {

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerHrMapper() {
	}

	@Override
	public EmployeCommand mapToEmployeCommand(EmployeTo employeTo) {
		if ("PILOTE".equals(employeTo.getTypeEmploye())) {
			return mapper.map(employeTo, PiloteCommand.class);
		}
		return mapper.map(employeTo, EmployeCommand.class);
	}

	@Override
	public EmployeTo mapToEmployeTo(EmployeDto employe) {
		return mapper.map(employe, EmployeTo.class);
	}

	@Override
	public List<EmployeSummaryTo> mapToEmployeSummaryTo(List<EmployeDto> employes) {
		List<EmployeSummaryTo> employeTos = new ArrayList<>(employes.size());
		for (EmployeDto employe : employes) {
			EmployeSummaryTo employeTo = mapper.map(employe, EmployeSummaryTo.class);
			employeTos.add(employeTo);
		}
		return employeTos;
	}
}
