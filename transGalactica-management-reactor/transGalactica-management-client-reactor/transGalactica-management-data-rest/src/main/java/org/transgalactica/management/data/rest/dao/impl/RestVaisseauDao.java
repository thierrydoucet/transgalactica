package org.transgalactica.management.data.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.data.rest.dao.VaisseauDao;
import org.transgalactica.management.data.rest.mapper.LogisticsMapper;
import org.transgalactica.management.flux.rest.VaisseauCommand;
import org.transgalactica.management.flux.rest.VaisseauDetailDto;
import org.transgalactica.management.flux.rest.VaisseauDtos;

@Repository
public class RestVaisseauDao implements VaisseauDao {

	private static final String VAISSEAUX = "/vaisseaux";

	private static final String SEARCH = VAISSEAUX + "/search?";

	private static final String BY_IMMATRICULATION = VAISSEAUX + "/{immatriculation}";

	@Value("${rest.url}")
	private String restServiceUrl;

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private LogisticsMapper mapper;

	protected RestVaisseauDao() {
	}

	@Override
	public List<VaisseauSummaryTo> searchByCriteria(VaisseauSearchCriteria criteres) {
		StringBuilder url = new StringBuilder(restServiceUrl);
		List<Object> params = new ArrayList<>(3);
		url.append(SEARCH);
		if (StringUtils.isNotBlank(criteres.getImmatriculation())) {
			url.append("immatriculation={immatriculation}&");
			params.add(criteres.getImmatriculation());
		}
		if (StringUtils.isNotBlank(criteres.getModele())) {
			url.append("modele={modele}&");
			params.add(criteres.getModele());
		}
		if (criteres.isIntergalactique()) {
			url.append("intergalactique={intergalactique}");
			params.add(criteres.isIntergalactique());
		}
		VaisseauDtos vaisseaux = restTemplate.getForObject(url.toString(), VaisseauDtos.class, params.toArray());
		return mapper.mapToVaisseauSummaryTo(vaisseaux.getVaisseau());
	}

	@Override
	public VaisseauTo getByImmatriculation(String immatriculation) {
		VaisseauDetailDto vaisseau = restTemplate.getForObject(restServiceUrl + BY_IMMATRICULATION,
				VaisseauDetailDto.class, immatriculation);
		return mapper.mapToVaisseauTo(vaisseau);
	}

	@Override
	public void persist(VaisseauTo vaisseau) {
		String immatriculation = vaisseau.getImmatriculation();
		VaisseauCommand command = mapper.mapToVaisseauCommand(vaisseau);
		try {
			restTemplate.headForHeaders(restServiceUrl + BY_IMMATRICULATION, immatriculation);
			restTemplate.put(restServiceUrl + BY_IMMATRICULATION, command, immatriculation);
		}
		catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				restTemplate.postForLocation(restServiceUrl + VAISSEAUX, command);
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public void remove(String immatriculation) {
		restTemplate.delete(restServiceUrl + BY_IMMATRICULATION, immatriculation);
	}
}
