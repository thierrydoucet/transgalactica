package org.transgalactica.management.rest.logistics.restservice;

import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;

public interface HangarRestService {

	HangarDtos getAll();

	HangarDetailDto getByNumero(long numero);

	void create(HangarCommand command);

	void save(long numero, HangarCommand command);

	void delete(long numero);

	HangarDtos search(String localisation);

	void addVaisseau(long numero, String immatriculation);

	void removeVaisseau(long numero, String immatriculation);
}
