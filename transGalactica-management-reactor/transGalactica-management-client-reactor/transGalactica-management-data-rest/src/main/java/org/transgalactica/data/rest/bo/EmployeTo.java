package org.transgalactica.data.rest.bo;

import java.io.Serializable;
import java.util.Date;
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

	void setDateEmbauche(Date value);
}
