package org.transgalactica.management.ws.logistics.mapper;

import java.util.List;

import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;

public interface HangarMapper {

	HangarSearchCriteria mapToRechercheHangarCriteres(String localisation);

	BasicHangarDto[] mapToHangars(List<HangarSummary> hangars);
}
