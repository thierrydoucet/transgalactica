package org.transgalactica.web.hangar.mapper.impl;

import javax.inject.Inject;

import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.web.hangar.mapper.HangarMapper;
import org.transgalactica.web.hangar.model.impl.HangarCommand;

@Mapper
public class DozerHangarMapper implements HangarMapper {

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerHangarMapper() {
	}

	/**
	 * @see org.transgalactica.web.hangar.mapper.impl.DozerHangarMapper#mapToHangarCommand(org.transgalactica.management.data.materiel.bo.HangarEntity)
	 */
	@Override
	public HangarCommand mapToHangarCommand(HangarEntity hangar) {
		return mapper.map(hangar, HangarCommand.class);
	}

	/**
	 * @see org.transgalactica.web.hangar.mapper.impl.DozerHangarMapper#mapHangarCommandToEntity(org.transgalactica.web.hangar.model.impl.HangarCommand,
	 * org.transgalactica.management.data.materiel.bo.HangarEntity)
	 */
	@Override
	public void mapHangarCommandToEntity(HangarCommand command, HangarEntity entity) {
		mapper.map(command, entity);
	}
}
