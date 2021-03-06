package org.transgalactica.management.data.rest.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface EmployeTo extends EmployeSummaryTo {

	public interface VaisseauTo extends Serializable {

		String getImmatriculation();

		String getModele();

		short getNombreDePassagers();

		long getCapaciteDeFret();

		int getVitesse();

		int getAutonomie();
	}

	List<EmployeTo.VaisseauTo> getVaisseaux();

	void setNom(String value);

	void setDateEmbauche(LocalDate value);
}
