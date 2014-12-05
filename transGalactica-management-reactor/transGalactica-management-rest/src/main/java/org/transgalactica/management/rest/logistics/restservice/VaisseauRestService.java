package org.transgalactica.management.rest.logistics.restservice;

import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;

public interface VaisseauRestService {

	VaisseauDetailDto getByImmatriculation(String immatriculation);

	void create(VaisseauCommand command);

	void save(String immatriculation, VaisseauCommand command);

	void delete(String immatriculation);

	VaisseauDtos search(String immatriculation, String modele, boolean intergalactique);
}
