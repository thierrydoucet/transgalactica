package org.transgalactica.data.rest.mapper;

import java.util.List;

import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.data.rest.bo.VaisseauTo;
import org.transgalactica.flux.rest.HangarCommand;
import org.transgalactica.flux.rest.HangarDetailDto;
import org.transgalactica.flux.rest.HangarDto;
import org.transgalactica.flux.rest.VaisseauCommand;
import org.transgalactica.flux.rest.VaisseauDetailDto;
import org.transgalactica.flux.rest.VaisseauDto;

public interface LogisticsMapper {

	HangarCommand mapToHangarCommand(HangarTo hangarTo);

	HangarTo mapToHangarTo(HangarDetailDto hangar);

	List<HangarSummaryTo> mapToHangarSummaryTo(List<HangarDto> hangars);

	VaisseauCommand mapToVaisseauCommand(VaisseauTo vaisseau);

	VaisseauTo mapToVaisseauTo(VaisseauDetailDto vaisseau);

	List<VaisseauSummaryTo> mapToVaisseauSummaryTo(List<VaisseauDto> vaisseaux);
}