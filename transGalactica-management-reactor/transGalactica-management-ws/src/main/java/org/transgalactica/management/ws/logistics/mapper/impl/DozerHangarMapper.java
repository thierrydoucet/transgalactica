package org.transgalactica.management.ws.logistics.mapper.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactory;
import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;
import org.transgalactica.management.ws.logistics.mapper.HangarMapper;

@Mapper
public class DozerHangarMapper implements HangarMapper {

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerHangarMapper() {
	}

	@Override
	public HangarSearchCriteria mapToRechercheHangarCriteres(String localisation) {
		HangarSearchCriteria criteres = beanFactory.getBean(HangarSearchCriteria.class);
		criteres.setLocalisationHangar(localisation);
		return criteres;
	}

	@Override
	public BasicHangarDto[] mapToHangars(List<HangarSummary> hangars) {
		BasicHangarDto[] hangarsTransport = new BasicHangarDto[hangars.size()];
		int i = 0;
		for (HangarSummary hangar : hangars) {
			BasicHangarDto hangarTransport = mapper.map(hangar, BasicHangarDto.class);
			hangarsTransport[i] = hangarTransport;
			i++;
		}
		return hangarsTransport;
	}
}
