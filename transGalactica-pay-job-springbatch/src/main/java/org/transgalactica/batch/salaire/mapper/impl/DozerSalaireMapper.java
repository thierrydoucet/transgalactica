package org.transgalactica.batch.salaire.mapper.impl;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.util.Assert;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.mapper.SalaireMapper;
import org.transgalactica.management.data.people.bo.EmployeEntity;

@org.transgalactica.fwk.domain.stereotype.Mapper
public class DozerSalaireMapper implements SalaireMapper {

	@Inject
	private Mapper mapper;

	protected DozerSalaireMapper() {
	}

	@Override
	public SalaireTo mapEmployeInformation(EmployeEntity employe) {
		Assert.notNull(employe);
		return mapper.map(employe, SalaireTo.class);
	}
}
