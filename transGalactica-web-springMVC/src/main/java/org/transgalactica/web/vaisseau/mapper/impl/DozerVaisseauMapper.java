package org.transgalactica.web.vaisseau.mapper.impl;

import javax.inject.Inject;

import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.web.vaisseau.mapper.VaisseauMapper;
import org.transgalactica.web.vaisseau.model.VaisseauCommand;

@Mapper
public class DozerVaisseauMapper implements VaisseauMapper {

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerVaisseauMapper() {
	}

	/**
	 * @see org.transgalactica.web.vaisseau.mapper.impl.DozerVaisseauMapper#mapToVaisseauCommand(org.transgalactica.management.data.materiel.bo.VaisseauEntity)
	 */
	@Override
	public VaisseauCommand mapToVaisseauCommand(VaisseauEntity vaisseau) {
		return mapper.map(vaisseau, VaisseauCommand.class);
	}

	/**
	 * @see org.transgalactica.web.vaisseau.mapper.impl.DozerVaisseauMapper#mapVaisseauCommandToEntity(org.transgalactica.web.vaisseau.model.VaisseauCommand,
	 * org.transgalactica.management.data.materiel.bo.VaisseauEntity)
	 */
	@Override
	public void mapVaisseauCommandToEntity(VaisseauCommand command, VaisseauEntity entity) {
		mapper.map(command, entity);
	}
}
