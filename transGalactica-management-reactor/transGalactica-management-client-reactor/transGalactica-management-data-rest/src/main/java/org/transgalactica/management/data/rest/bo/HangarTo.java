package org.transgalactica.management.data.rest.bo;

import java.io.Serializable;
import java.util.List;

public interface HangarTo extends HangarSummaryTo {

	public interface VaisseauTo extends Serializable {

		String getImmatriculation();

		String getModele();

		short getNombreDePassagers();

		long getCapaciteDeFret();

		int getVitesse();

		int getAutonomie();

	}

	List<VaisseauTo> getVaisseaux();

	void setLocalisation(String localisation);

	void setNombreEmplacements(int nombreEmplacements);
}