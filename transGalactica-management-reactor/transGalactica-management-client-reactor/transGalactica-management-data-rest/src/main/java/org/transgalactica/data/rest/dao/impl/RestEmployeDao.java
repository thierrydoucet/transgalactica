package org.transgalactica.data.rest.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.data.rest.bo.EmployeSearchCriteria;
import org.transgalactica.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.data.rest.bo.EmployeTo;
import org.transgalactica.data.rest.dao.EmployeDao;
import org.transgalactica.data.rest.mapper.HrMapper;
import org.transgalactica.flux.rest.EmployeCommand;
import org.transgalactica.flux.rest.EmployeDto;
import org.transgalactica.flux.rest.EmployeDtos;
import org.transgalactica.flux.rest.MecanicienDetailDto;
import org.transgalactica.flux.rest.PiloteCommand;
import org.transgalactica.flux.rest.PiloteDetailDto;

@Repository
public class RestEmployeDao implements EmployeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestEmployeDao.class);

	private static final String EMPLOYES_URI = "/employes";

	private static final String BY_MATRICULE_URI = EMPLOYES_URI + "/{matricule}";

	private static final String SPECIALITE_URI = BY_MATRICULE_URI + "/specialites/{specialite}";

	private static final String VAISSEAU_URI = BY_MATRICULE_URI + "/vaisseaux/{immatriculation}";

	private static final String TRANSGALACTICA_CONTENT_TYPE = "TransGalactica-Content-Type";

	@Value("${rest.url}")
	private String restServiceUrl;

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private HrMapper mapper;

	protected RestEmployeDao() {
	}

	@Override
	public List<EmployeSummaryTo> searchByCriteria(EmployeSearchCriteria criteres) {
		EmployeDtos employes = restTemplate
				.getForObject(
						restServiceUrl
								+ "/employes/search?nomEmploye={nomEmploye}&dateEmbaucheEmployeDebut={dateEmbaucheEmployeDebut}&dateEmbaucheEmployeFin={dateEmbaucheEmployeFin}",
						EmployeDtos.class, criteres.getNomEmploye(), criteres.getDateEmbaucheEmployeDebut(),
						criteres.getDateEmbaucheEmployeFin());
		return mapper.mapToEmployeSummaryTo(employes.getEmploye());
	}

	@Override
	public EmployeTo getByMatricule(long matricule) {
		EmployeDto dto = restTemplate.execute(restServiceUrl + BY_MATRICULE_URI, HttpMethod.GET,
				new EmployeDtoHeaderRequestCallback(), new EmployeDtoResponseExtractor(), matricule);
		return mapper.mapToEmployeTo(dto);
	}

	@Override
	public void persist(EmployeTo employe) {
		Long id = employe.getMatricule();
		EmployeCommand command = mapper.mapToEmployeCommand(employe);

		HttpHeaders headers = new HttpHeaders();
		if (command instanceof PiloteCommand) {
			headers.add(TRANSGALACTICA_CONTENT_TYPE, "PiloteCommand");
		}
		else {
			headers.add(TRANSGALACTICA_CONTENT_TYPE, "EmployeCommand");
		}

		if (id == null) {
			restTemplate.postForLocation(restServiceUrl + EMPLOYES_URI, new HttpEntity<>(command, headers));
		}
		else {
			restTemplate.put(restServiceUrl + BY_MATRICULE_URI, new HttpEntity<>(command, headers), id);
		}
	}

	@Override
	public void remove(long matricule) {
		restTemplate.delete(restServiceUrl + BY_MATRICULE_URI, matricule);
	}

	@Override
	public void addVaisseau(long matriculeEmploye, String immatriculationVaisseau) {
		restTemplate.postForLocation(restServiceUrl + VAISSEAU_URI, null, matriculeEmploye, immatriculationVaisseau);
	}

	@Override
	public void removeVaisseau(long matriculeEmploye, String immatriculationVaisseau) {
		restTemplate.delete(restServiceUrl + VAISSEAU_URI, matriculeEmploye, immatriculationVaisseau);
	}

	@Override
	public void addSpecialite(long matriculeEmploye, String specialite) {
		restTemplate.postForLocation(restServiceUrl + SPECIALITE_URI, null, matriculeEmploye, specialite);
	}

	@Override
	public void removeSpecialite(long matriculeEmploye, String specialite) {
		restTemplate.delete(restServiceUrl + SPECIALITE_URI, matriculeEmploye, specialite);
	}

	/**
	 * Copie de Spring AcceptHeaderRequestCallback
	 */
	private final class EmployeDtoHeaderRequestCallback implements RequestCallback {

		private EmployeDtoHeaderRequestCallback() {
		}

		public void doWithRequest(ClientHttpRequest request) throws IOException {
			List<MediaType> allSupportedMediaTypes = new ArrayList<MediaType>();
			for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
				if (converter.canRead(EmployeDto.class, null)) {
					allSupportedMediaTypes.addAll(getSupportedMediaTypes(converter));
				}
			}
			if (!allSupportedMediaTypes.isEmpty()) {
				MediaType.sortBySpecificity(allSupportedMediaTypes);
				LOGGER.debug("Setting request Accept header to {}", allSupportedMediaTypes);
				request.getHeaders().setAccept(allSupportedMediaTypes);
			}
		}

		private List<MediaType> getSupportedMediaTypes(HttpMessageConverter<?> messageConverter) {
			List<MediaType> supportedMediaTypes = messageConverter.getSupportedMediaTypes();
			List<MediaType> result = new ArrayList<MediaType>(supportedMediaTypes.size());
			for (MediaType supportedMediaType : supportedMediaTypes) {
				if (supportedMediaType.getCharSet() != null) {
					supportedMediaType = new MediaType(supportedMediaType.getType(), supportedMediaType.getSubtype());
				}
				result.add(supportedMediaType);
			}
			return result;
		}
	}

	private final class EmployeDtoResponseExtractor implements ResponseExtractor<EmployeDto> {

		private final HttpMessageConverterExtractor<PiloteDetailDto> piloteResponseExtractor;

		private final HttpMessageConverterExtractor<MecanicienDetailDto> mecanicienResponseExtractor;

		private EmployeDtoResponseExtractor() {
			this.piloteResponseExtractor = new HttpMessageConverterExtractor<>(PiloteDetailDto.class,
					restTemplate.getMessageConverters());
			this.mecanicienResponseExtractor = new HttpMessageConverterExtractor<>(MecanicienDetailDto.class,
					restTemplate.getMessageConverters());
		}

		@Override
		public EmployeDto extractData(ClientHttpResponse response) throws IOException {
			List<String> tct = response.getHeaders().get(TRANSGALACTICA_CONTENT_TYPE);
			if (tct != null && tct.contains("PiloteDetail")) {
				return piloteResponseExtractor.extractData(response);
			}
			return mecanicienResponseExtractor.extractData(response);
		}
	}
}
