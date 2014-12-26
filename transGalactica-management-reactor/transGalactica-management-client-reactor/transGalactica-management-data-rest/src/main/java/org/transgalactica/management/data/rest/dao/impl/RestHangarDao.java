package org.transgalactica.management.data.rest.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.management.data.rest.dao.HangarDao;
import org.transgalactica.management.data.rest.mapper.LogisticsMapper;
import org.transgalactica.management.flux.rest.HangarCommand;
import org.transgalactica.management.flux.rest.HangarDetailDto;
import org.transgalactica.management.flux.rest.HangarDtos;

@Repository
public class RestHangarDao implements HangarDao {

	private static final String HANGARS_URI = "/hangars";

	private static final String SEARCH_URI = HANGARS_URI + "/search?localisation={localisation}";

	private static final String BY_NUMERO_URI = HANGARS_URI + "/{numero}";

	private static final String VAISSEAUX_URI = BY_NUMERO_URI + "/vaisseaux/{immatriculation}";

	@Value("${rest.url}")
	private String restServiceUrl;

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private LogisticsMapper mapper;

	protected RestHangarDao() {
	}

	@Override
	public List<HangarSummaryTo> searchByCriteria(HangarSearchCriteria criteres) {
		HangarDtos hangars = restTemplate.getForObject(restServiceUrl + SEARCH_URI, HangarDtos.class,
				criteres.getLocalisationHangar());
		return mapper.mapToHangarSummaryTo(hangars.getHangar());
	}

	@Override
	public HangarTo getByNumero(long numero) {
		HangarDetailDto hangar = restTemplate.getForObject(restServiceUrl + BY_NUMERO_URI, HangarDetailDto.class,
				numero);
		return mapper.mapToHangarTo(hangar);
	}

	@Override
	public void persist(HangarTo hangar) {
		Long id = hangar.getNumero();
		HangarCommand command = mapper.mapToHangarCommand(hangar);
		if (id == null) {
			restTemplate.postForLocation(restServiceUrl + HANGARS_URI, command);
		}
		else {
			restTemplate.put(restServiceUrl + BY_NUMERO_URI, command, id);
		}
	}

	@Override
	public void remove(long numero) {
		restTemplate.delete(restServiceUrl + BY_NUMERO_URI, numero);
	}

	@Override
	public void addVaisseau(long numeroHangar, String immatriculationVaisseau) {
		restTemplate.postForLocation(restServiceUrl + VAISSEAUX_URI, null, numeroHangar, immatriculationVaisseau);
	}

	@Override
	public void removeVaisseau(long numeroHangar, String immatriculationVaisseau) {
		restTemplate.delete(restServiceUrl + VAISSEAUX_URI, numeroHangar, immatriculationVaisseau);
	}
}
