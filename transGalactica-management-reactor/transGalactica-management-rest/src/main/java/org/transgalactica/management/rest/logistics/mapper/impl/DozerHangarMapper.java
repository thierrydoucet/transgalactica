package org.transgalactica.management.rest.logistics.mapper.impl;

import java.util.List;

import javax.inject.Inject;

import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;
import org.transgalactica.management.rest.logistics.mapper.HangarMapper;

/**
 * Implementation par défaut de {@link HangarMapper}
 * 
 * @author Thierry
 */
@Mapper
public class DozerHangarMapper implements HangarMapper {

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerHangarMapper() {
	}

	@Override
	public void mapHangarCommandToEntity(HangarCommand command, HangarEntity entity) {
		mapper.map(command, entity);
	}

	@Override
	public HangarDtos mapToHangarDtos(List<HangarSummary> hangars) {
		return mapper.map(hangars, HangarDtos.class);
	}

	@Override
	public HangarDetailDto mapToHangarDetailDto(HangarEntity entity) {
		return mapper.map(entity, HangarDetailDto.class);
	}

	@Override
	public HangarSearchCriteria mapToRechercheHangarCriteres(String localisation) {
		return mapper.map(localisation, HangarSearchCriteria.class);
	}
}
