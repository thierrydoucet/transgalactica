package org.transgalactica.data.rest.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.data.rest.bo.VaisseauTo;
import org.transgalactica.data.rest.mapper.LogisticsMapper;
import org.transgalactica.flux.rest.HangarCommand;
import org.transgalactica.flux.rest.HangarDetailDto;
import org.transgalactica.flux.rest.HangarDto;
import org.transgalactica.flux.rest.VaisseauCommand;
import org.transgalactica.flux.rest.VaisseauDetailDto;
import org.transgalactica.flux.rest.VaisseauDto;
import org.transgalactica.fwk.domain.stereotype.Mapper;

/**
 * Implementation <code>Dozer</code> de {@link LogisticsMapper}
 * 
 * @author Thierry
 */
@Mapper
public class DozerLogisticsMapper implements LogisticsMapper {

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerLogisticsMapper() {
	}

	/**
	 * @see org.transgalactica.data.rest.mapper.LogisticsMapper#mapToHangarCommand(org.transgalactica.data.rest.bo.HangarTo)
	 */
	@Override
	public HangarCommand mapToHangarCommand(HangarTo hangarTo) {
		return mapper.map(hangarTo, HangarCommand.class);
	}

	/**
	 * @see org.transgalactica.data.rest.mapper.LogisticsMapper#mapToHangarTo(org.transgalactica.flux.rest.HangarDetailDto)
	 */
	@Override
	public HangarTo mapToHangarTo(HangarDetailDto hangar) {
		return mapper.map(hangar, HangarTo.class);
	}

	/**
	 * @see org.transgalactica.data.rest.mapper.LogisticsMapper#mapToHangarSummaryTo(java.util.List)
	 */
	@Override
	public List<HangarSummaryTo> mapToHangarSummaryTo(List<HangarDto> hangars) {
		List<HangarSummaryTo> hangarTos = new ArrayList<>(hangars.size());
		for (HangarDto hangar : hangars) {
			HangarSummaryTo hangarTo = mapper.map(hangar, HangarSummaryTo.class);
			hangarTos.add(hangarTo);
		}
		return hangarTos;
	}

	/**
	 * @see org.transgalactica.data.rest.mapper.LogisticsMapper#mapToVaisseauCommand(org.transgalactica.data.rest.bo.VaisseauTo)
	 */
	@Override
	public VaisseauCommand mapToVaisseauCommand(VaisseauTo vaisseau) {
		return mapper.map(vaisseau, VaisseauCommand.class);
	}

	/**
	 * @see org.transgalactica.data.rest.mapper.LogisticsMapper#mapToVaisseauSummaryTo(java.util.List)
	 */
	@Override
	public List<VaisseauSummaryTo> mapToVaisseauSummaryTo(List<VaisseauDto> vaisseaux) {
		List<VaisseauSummaryTo> vaisseauTos = new ArrayList<>(vaisseaux.size());
		for (VaisseauDto vaisseau : vaisseaux) {
			VaisseauSummaryTo vaisseauTo = mapper.map(vaisseau, VaisseauSummaryTo.class);
			vaisseauTos.add(vaisseauTo);
		}
		return vaisseauTos;
	}

	/**
	 * @see org.transgalactica.data.rest.mapper.LogisticsMapper#mapToVaisseauTo(org.transgalactica.flux.rest.VaisseauDetailDto)
	 */
	@Override
	public VaisseauTo mapToVaisseauTo(VaisseauDetailDto vaisseau) {
		return mapper.map(vaisseau, VaisseauTo.class);
	}
}
