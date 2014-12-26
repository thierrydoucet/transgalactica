package org.transgalactica.management.data.rest.mapper;

import java.util.List;

import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.flux.rest.HangarCommand;
import org.transgalactica.management.flux.rest.HangarDetailDto;
import org.transgalactica.management.flux.rest.HangarDto;
import org.transgalactica.management.flux.rest.VaisseauCommand;
import org.transgalactica.management.flux.rest.VaisseauDetailDto;
import org.transgalactica.management.flux.rest.VaisseauDto;

public interface LogisticsMapper {

	HangarCommand mapToHangarCommand(HangarTo hangarTo);

	HangarTo mapToHangarTo(HangarDetailDto hangar);

	List<HangarSummaryTo> mapToHangarSummaryTo(List<HangarDto> hangars);

	VaisseauCommand mapToVaisseauCommand(VaisseauTo vaisseau);

	VaisseauTo mapToVaisseauTo(VaisseauDetailDto vaisseau);

	List<VaisseauSummaryTo> mapToVaisseauSummaryTo(List<VaisseauDto> vaisseaux);
}