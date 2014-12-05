package org.transgalactica.management.rest.logistics.mapper;

import java.util.List;

import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;

public interface VaisseauMapper {

	void mapVaisseauCommandToEntity(VaisseauCommand command, VaisseauEntity entity);

	VaisseauDtos mapToVaisseauDtos(List<VaisseauSummary> projections);

	VaisseauDetailDto mapToVaisseauDetailDto(VaisseauEntity entity);

	VaisseauSearchCriteria mapToRechercheVaisseauCriteres(String immatriculation, String modele,
			boolean intergalactique);

}
