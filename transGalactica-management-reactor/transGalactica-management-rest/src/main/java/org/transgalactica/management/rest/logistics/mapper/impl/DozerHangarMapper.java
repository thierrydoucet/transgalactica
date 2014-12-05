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

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.HangarMapper.mapper.IHangarMapper#mapHangarCommandToEntity(org.transgalactica.management.rest.materiel.data.impl.HangarCommand,
	 * org.transgalactica.management.data.materiel.bo.HangarEntity)
	 */
	@Override
	public void mapHangarCommandToEntity(HangarCommand command, HangarEntity entity) {
		mapper.map(command, entity);
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.HangarMapper.mapper.IHangarMapper#mapToHangarDtos(java.util.List)
	 */
	@Override
	public HangarDtos mapToHangarDtos(List<HangarSummary> hangars) {
		/*
		 * JaxbHangarDtos dtos = new JaxbHangarDtos(); for (HangarSummary
		 * projection : projections) { JaxbHangarDto dto =
		 * mapper.map(projection, JaxbHangarDto.class); dtos.add(dto); } return
		 * dtos;
		 */
		return mapper.map(hangars, HangarDtos.class);
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.HangarMapper.mapper.IHangarMapper#mapToHangarDetailDto(org.transgalactica.management.data.materiel.bo.HangarEntity)
	 */
	@Override
	public HangarDetailDto mapToHangarDetailDto(HangarEntity entity) {
		return mapper.map(entity, HangarDetailDto.class);
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.HangarMapper#mapToRechercheHangarCriteres(java.lang.String)
	 */
	@Override
	public HangarSearchCriteria mapToRechercheHangarCriteres(String localisation) {
		return mapper.map(localisation, HangarSearchCriteria.class);
	}
}
