package org.transgalactica.management.data.rest.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.data.rest.dao.HrReferentielDao;
import org.transgalactica.management.flux.rest.EmployeTypes;
import org.transgalactica.management.flux.rest.MecanicienSpecialiteDtos;

@Repository
public class RestHrReferentielDao implements HrReferentielDao {

	private static final String EMPLOYETYPES_URI = "/hrreferentiel/employetypes";

	private static final String MECANICIENSPECIALITES_URI = "/hrreferentiel/mecanicienspecialites";

	@Value("${rest.url}")
	private String restServiceUrl;

	@Inject
	private RestTemplate restTemplate;

	protected RestHrReferentielDao() {
	}

	@Override
	public List<String> getAllEmployeType() {
		EmployeTypes types = restTemplate.getForObject(restServiceUrl + EMPLOYETYPES_URI, EmployeTypes.class);
		return types.getEmployeType();
	}

	@Override
	public List<String> getAllMecanicienspecialite() {
		MecanicienSpecialiteDtos specialites = restTemplate.getForObject(restServiceUrl + MECANICIENSPECIALITES_URI,
				MecanicienSpecialiteDtos.class);
		return specialites.getMecanicienSpecialite();
	}
}