package org.transgalactica.data.rest.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.data.rest.dao.HrReferentielDao;
import org.transgalactica.flux.rest.EmployeTypeDtos;
import org.transgalactica.flux.rest.MecanicienSpecialiteDtos;

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
		EmployeTypeDtos types = restTemplate.getForObject(restServiceUrl + EMPLOYETYPES_URI, EmployeTypeDtos.class);
		return types.getEmployeTypes();
	}

	@Override
	public List<String> getAllMecanicienspecialite() {
		MecanicienSpecialiteDtos specialites = restTemplate.getForObject(restServiceUrl + MECANICIENSPECIALITES_URI,
				MecanicienSpecialiteDtos.class);
		return specialites.getMecanicienSpecialites();
	}
}