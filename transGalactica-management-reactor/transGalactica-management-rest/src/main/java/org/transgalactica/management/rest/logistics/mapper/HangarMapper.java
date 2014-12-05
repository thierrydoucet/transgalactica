package org.transgalactica.management.rest.logistics.mapper;

import java.util.List;

import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;

public interface HangarMapper {

	void mapHangarCommandToEntity(HangarCommand command, HangarEntity entity);

	HangarDtos mapToHangarDtos(List<HangarSummary> projections);

	HangarDetailDto mapToHangarDetailDto(HangarEntity entity);

	HangarSearchCriteria mapToRechercheHangarCriteres(String location);
}
