package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;
import java.util.Set;

public interface HangarDetailDto extends HangarDto, Serializable {

	public interface VaisseauDto extends Serializable {

		String getImmatriculation();

		String getModele();

		short getNombreDePassagers();

		long getCapaciteDeFret();

		int getVitesse();

		int getAutonomie();
	}

	Set<HangarDetailDto.VaisseauDto> getVaisseaux();
}